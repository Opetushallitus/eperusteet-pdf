package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.KVLiiteBuilderService;
import fi.vm.sade.eperusteet.pdf.service.external.AmosaaService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import fi.vm.sade.eperusteet.pdf.service.ylops.YlopsDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

@Slf4j
@Service
@Profile("default")
public class DokumenttiServiceImpl implements DokumenttiService {
    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Autowired
    private EperusteetDokumenttiBuilderService eperusteetDokumenttiBuilderService;

    @Autowired
    private AmosaaDokumenttiBuilderService amosaaDokumenttiBuilderService;

    @Autowired
    private YlopsDokumenttiBuilderService ylopsDokumenttiBuilderService;

    @Autowired
    private KVLiiteBuilderService kvLiiteBuilderService;

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private AmosaaService amosaaService;

    @Autowired
    private EperusteetService eperusteetService;

    @Autowired
    private YlopsService ylopsService;

    @Value("classpath:docgen/fop.xconf")
    private Resource fopConfig;

    // FIXME: Tämä service pitää mockata. Onko enää ajankohtainen 2023?
    @Value("${spring.profiles.active:normal}")
    private String activeProfile;

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForEperusteet(Long dokumenttiId, Kieli kieli, GeneratorVersion versio, String perusteJson) throws JsonProcessingException, DokumenttiException {
        PerusteKaikkiDto peruste = objectMapper.readValue(perusteJson, PerusteKaikkiDto.class);
        GeneratorData generatorData = createGeneratorData(peruste.getId(), kieli, DokumenttiTyyppi.PERUSTE, versio);
        try {
            Document doc;
            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            if (DokumenttiTyyppi.PERUSTE.equals(generatorData.getTyyppi())) {
                doc = eperusteetDokumenttiBuilderService.generateXML(peruste, generatorData);
            } else {
                doc = kvLiiteBuilderService.generateXML(peruste, generatorData.getKieli());
            }
            byte[] pdfData = pdfService.xhtml2pdf(doc, generateMetaData(generatorData, peruste.getNimi()), generatorData.getTyyppi());

            log.info("PDF-dokumentti luotu.");
            eperusteetService.postPdfData(pdfData, dokumenttiId);
        } catch (Exception ex) {
            log.error("PDF-dokumentin luonti epäonnistui ({})", ex.getMessage());
            eperusteetService.updateDokumenttiTila(DokumenttiTila.EPAONNISTUI, dokumenttiId);
            throw new DokumenttiException(ex.getMessage(), ex);
        }
    }

//    @Override
//    @Async(value = "docTaskExecutor")
//    public void generate(Long dokumenttiId, Kieli kieli, DokumenttiTyyppi tyyppi, String perusteJson) throws DokumenttiException, JsonProcessingException {
//
//        byte[] pdfData = null;
//        try {
//            if (DokumenttiTyyppi.PERUSTE.equals(tyyppi)) {
//
//            } else if (DokumenttiTyyppi.OPS.equals(tyyppi)) {
////                pdfData = createAmoseePdfData(generatorData);
//            } else if (DokumenttiTyyppi.TOTEUTUSSUUNNITELMA.equals(tyyppi)) {
////                pdfData = createYlopsPdfData(generatorData);
//            } else if (DokumenttiTyyppi.KVLIITE.equals(tyyppi)){
////                pdfData = createEperusteetKVLiitePdfData(generatorData);
//            } else {
//                throw new ServiceException("Tuntematon dokumenttityyppi");
//            }
//            log.info("PDF-dokumentti luotu.");
//            eperusteetService.postPdfData(pdfData, dokumenttiId);
//        } catch (Exception ex) {
//            handleException(ex);
//        }
//    }

//    private byte[] createEperusteetPdfData(PerusteKaikkiDto peruste, GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
//        Document doc = eperusteetDokumenttiBuilderService.generateXML(peruste, generatorData);
//        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, peruste.getNimi()), TemplateTyyppi.PERUSTE);
//    }

//    private byte[] createAmoseePdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
//        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
//        OpetussuunnitelmaKaikkiDto ops = amosaaService.getOpetussuunnitelma(generatorData.getKtId(), generatorData.getId());
//        Document doc = amosaaDokumenttiBuilderService.generateXML(generatorData, ops);
//        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, ops.getNimi()), TemplateTyyppi.AMOSAA);
//    }
//
//    private byte[] createYlopsPdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
//        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
//        OpetussuunnitelmaExportDto ops = ylopsService.getOpetussuunnitelmaTemp(generatorData.getId());
//        Document doc = ylopsDokumenttiBuilderService.generateXML(generatorData, ops);
//        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, ops.getNimi()), TemplateTyyppi.YLOPS);
//    }
//
//    private byte[] createEperusteetKVLiitePdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
//        PerusteKaikkiDto perusteData = eperusteetService.getPerusteKaikkiDtoTemp(generatorData.getId(), generatorData.getRevision());
//        Document doc = kvLiiteBuilderService.generateXML(perusteData, generatorData.getKieli());
//        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, perusteData.getNimi()), TemplateTyyppi.KVLIITE);
//    }

    private DokumenttiMetaDto generateMetaData(GeneratorData generatorData, LokalisoituTekstiDto nimi) {
        return DokumenttiMetaDto.builder()
                .title(DokumenttiUtils.getTextString(generatorData.getKieli(), nimi))
                .subject(messages.translate(DokumenttiUtils.selectSubjectTranslationKey(generatorData.getTyyppi()), generatorData.getKieli()))
                .build();
    }

    private GeneratorData createGeneratorData(Long perusteId, Kieli kieli, DokumenttiTyyppi tyyppi, GeneratorVersion versio) {
        GeneratorData generatorData = new GeneratorData();
        generatorData.setId(perusteId);
        generatorData.setKieli(kieli);
        generatorData.setTyyppi(tyyppi);
        if (GeneratorVersion.KVLIITE.equals(versio)) {
            generatorData.setTyyppi(DokumenttiTyyppi.KVLIITE);
        }
        return generatorData;
    }
}
