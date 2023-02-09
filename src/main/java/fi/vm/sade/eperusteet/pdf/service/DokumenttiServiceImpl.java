package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.TemplateTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
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
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

@Slf4j
@Service
@Profile("default")
public class DokumenttiServiceImpl implements DokumenttiService {

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
    public byte[] generate(Long id, Integer revision, Kieli kieli, DokumenttiTyyppi tyyppi, Long ktId) throws DokumenttiException {
        GeneratorData generatorData = createGeneratorData(id, revision, kieli, tyyppi, ktId);

        log.info("Luodaan PDF-dokumenttia (id={}, {}, {})", id, tyyppi, kieli);
        byte[] pdfData;
        try {
            if (DokumenttiTyyppi.PERUSTE.equals(tyyppi)) {
                pdfData = createEperusteetPdfData(generatorData);
            } else if (DokumenttiTyyppi.OPS.equals(tyyppi)) {
                pdfData = createAmoseePdfData(generatorData);
            } else if (DokumenttiTyyppi.TOTEUTUSSUUNNITELMA.equals(tyyppi)) {
                pdfData = createYlopsPdfData(generatorData);
            } else if (DokumenttiTyyppi.KVLIITE.equals(tyyppi)){
                pdfData = createEperusteetKVLiitePdfData(generatorData);
            } else {
                throw new ServiceException("Tuntematon dokumenttityyppi");
            }
            log.info("PDF-dokumentti luotu.");
            return pdfData;
        } catch (Exception ex) {
            log.error("PDF-dokumentin luonti epäonnistui ({})", ex.getMessage());
            throw new DokumenttiException(ex.getMessage(), ex);
        }
    }

    private byte[] createEperusteetPdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getPerusteKaikkiDto()
        PerusteKaikkiDto perusteData = eperusteetService.getPerusteKaikkiDtoTemp(generatorData.getId(), generatorData.getRevision());
        Document doc = eperusteetDokumenttiBuilderService.generateXML(generatorData, perusteData);
        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, perusteData.getNimi()), TemplateTyyppi.PERUSTE);
    }

    private byte[] createAmoseePdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
        OpetussuunnitelmaKaikkiDto ops = amosaaService.getOpetussuunnitelmaTemp(generatorData.getKtId(), generatorData.getId());
        Document doc = amosaaDokumenttiBuilderService.generateXML(generatorData, ops);
        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, ops.getNimi()), TemplateTyyppi.AMOSAA);
    }

    private byte[] createYlopsPdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
        OpetussuunnitelmaExportDto ops = ylopsService.getOpetussuunnitelmaTemp(generatorData.getId());
        Document doc = ylopsDokumenttiBuilderService.generateXML(generatorData, ops);
        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, ops.getNimi()), TemplateTyyppi.YLOPS);
    }

    private byte[] createEperusteetKVLiitePdfData(GeneratorData generatorData) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
        PerusteKaikkiDto perusteData = eperusteetService.getPerusteKaikkiDtoTemp(generatorData.getId(), generatorData.getRevision());
        Document doc = kvLiiteBuilderService.generateXML(perusteData, generatorData.getKieli());
        return pdfService.xhtml2pdf(doc, generateMetaData(generatorData, perusteData.getNimi()), TemplateTyyppi.KVLIITE);
    }

    private DokumenttiMetaDto generateMetaData(GeneratorData generatorData, LokalisoituTekstiDto nimi) {
        return DokumenttiMetaDto.builder()
                .title(DokumenttiUtils.getTextString(generatorData.getKieli(), nimi))
                .subject(messages.translate(DokumenttiUtils.selectSubjectTranslationKey(generatorData.getTyyppi()), generatorData.getKieli()))
                .build();
    }

    private GeneratorData createGeneratorData(Long id, Integer revision, Kieli kieli, DokumenttiTyyppi tyyppi, Long ktId) {
        GeneratorData generatorData = new GeneratorData();
        generatorData.setId(id);
        generatorData.setRevision(revision);
        generatorData.setKieli(kieli);
        generatorData.setTyyppi(tyyppi);
        generatorData.setKtId(ktId);
        return generatorData;
    }
}
