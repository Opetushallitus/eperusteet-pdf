package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.KVLiiteBuilderService;
import fi.vm.sade.eperusteet.pdf.service.external.CommonExternalService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.ylops.YlopsDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.IOException;

@Slf4j
@Service
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
    private DokumenttiUtilService dokumenttiUtilService;

    @Autowired
    private CommonExternalService commonExternalService;

    @Autowired
    private EperusteetService eperusteetService;

    @Value("classpath:docgen/fop.xconf")
    private Resource fopConfig;

    // FIXME: Tämä service pitää mockata. Onko enää ajankohtainen 2023?
    @Value("${spring.profiles.active:normal}")
    private String activeProfile;

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForEperusteet(Long dokumenttiId, Kieli kieli, GeneratorVersion versio, String perusteJson) throws JsonProcessingException, DokumenttiException {
        PerusteKaikkiDto peruste = objectMapper.readValue(perusteJson, PerusteKaikkiDto.class);
        GeneratorData generatorData = dokumenttiUtilService.createGeneratorData(peruste.getId(), dokumenttiId, kieli, DokumenttiTyyppi.PERUSTE, versio, null);

        Document doc;
        try {
            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            if (DokumenttiTyyppi.PERUSTE.equals(generatorData.getTyyppi())) {
                doc = eperusteetDokumenttiBuilderService.generateXML(peruste, generatorData);
            } else {
                doc = kvLiiteBuilderService.generateXML(peruste, generatorData.getKieli());
            }
            handleConversionAndSending(doc, generateMetaData(generatorData, peruste.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex,dokumenttiId, generatorData.getTyyppi());
        }
    }

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForAmosaa(Long dokumenttiId, Kieli kieli, Long ktId, String opsJson) throws JsonProcessingException, DokumenttiException {
        OpetussuunnitelmaKaikkiDto ops = objectMapper.readValue(opsJson, OpetussuunnitelmaKaikkiDto.class);
        GeneratorData generatorData = dokumenttiUtilService.createGeneratorData(ops.getId(), dokumenttiId, kieli, DokumenttiTyyppi.OPS, null, ktId);

        try {
            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = amosaaDokumenttiBuilderService.generateXML(ops, generatorData);
            handleConversionAndSending(doc, generateMetaData(generatorData, ops.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex,dokumenttiId, generatorData.getTyyppi());
        }
    }

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForYlops(Long dokumenttiId, Kieli kieli, String opsJson) throws JsonProcessingException, DokumenttiException {
        OpetussuunnitelmaExportDto ops = objectMapper.readValue(opsJson, OpetussuunnitelmaExportDto.class);
        PerusteKaikkiDto perusteKaikkiDto = eperusteetService.getPerusteKaikkiDto(ops.getPerusteenId(), null);
        GeneratorData generatorData = dokumenttiUtilService.createGeneratorData(ops.getId(), dokumenttiId, kieli, DokumenttiTyyppi.TOTEUTUSSUUNNITELMA, null, null);

        try {
            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = ylopsDokumenttiBuilderService.generateXML(ops, perusteKaikkiDto, generatorData);
            handleConversionAndSending(doc, generateMetaData(generatorData, ops.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex,dokumenttiId, generatorData.getTyyppi());
        }
    }

    private void handleConversionAndSending(Document doc, DokumenttiMetaDto metaData, GeneratorData generatorData) throws DokumenttiException, IOException, TransformerException, SAXException {
        byte[] pdfData = pdfService.xhtml2pdf(doc, metaData, generatorData.getTyyppi());
        log.info("PDF-dokumentti luotu. Lähetetään kutsuvalle servicelle...");
        commonExternalService.postPdfData(pdfData, generatorData.getDokumenttiId(), generatorData.getTyyppi());
    }

    private void handleError(Exception ex, Long dokumenttiId, DokumenttiTyyppi tyyppi) throws DokumenttiException {
        log.error("PDF-dokumentin luonti epäonnistui ({})", ex.getMessage());
        commonExternalService.updateDokumenttiTila(DokumenttiTila.EPAONNISTUI, dokumenttiId, tyyppi);
        throw new DokumenttiException(ex.getMessage(), ex);
    }

    private DokumenttiMetaDto generateMetaData(GeneratorData generatorData, LokalisoituTekstiDto nimi) {
        return DokumenttiMetaDto.builder()
                .title(DokumenttiUtils.getTextString(generatorData.getKieli(), nimi))
                .subject(messages.translate(DokumenttiUtils.selectSubjectTranslationKey(generatorData.getTyyppi()), generatorData.getKieli()))
                .build();
    }
}
