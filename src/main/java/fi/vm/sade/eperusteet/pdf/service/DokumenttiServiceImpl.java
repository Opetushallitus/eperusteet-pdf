package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.KVLiiteBuilderService;
import fi.vm.sade.eperusteet.pdf.service.external.CommonExternalService;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import fi.vm.sade.eperusteet.pdf.service.ylops.YlopsDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.ByteArrayOutputStream;
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
    private CommonExternalService commonExternalService;

    @Autowired
    private YlopsService ylopsService;

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForEperusteet(Long dokumenttiId, Kieli kieli, String perusteJson) {
        try {
            PerusteKaikkiDto peruste = objectMapper.readValue(perusteJson, PerusteKaikkiDto.class);
            GeneratorData generatorData = GeneratorData.of(peruste.getId(), dokumenttiId, kieli, DokumenttiTyyppi.PERUSTE, null);

            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = eperusteetDokumenttiBuilderService.generateXML(peruste, generatorData);
            handleConversionAndSending(doc, generateMetaData(generatorData, peruste.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex, dokumenttiId, DokumenttiTyyppi.PERUSTE);
        }
    }

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForEperusteetKvLiite(Long dokumenttiId, Kieli kieli, String perusteJson) {
        try {
            PerusteKaikkiDto peruste = objectMapper.readValue(perusteJson, PerusteKaikkiDto.class);
            GeneratorData generatorData = GeneratorData.of(peruste.getId(), dokumenttiId, kieli, DokumenttiTyyppi.KVLIITE, null);

            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = kvLiiteBuilderService.generateXML(peruste, generatorData.getKieli());
            handleConversionAndSending(doc, generateMetaData(generatorData, peruste.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex, dokumenttiId, DokumenttiTyyppi.KVLIITE);
        }
    }

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForAmosaa(Long dokumenttiId, Kieli kieli, Long ktId, String opsJson) {
        try {
            OpetussuunnitelmaKaikkiDto ops = objectMapper.readValue(opsJson, OpetussuunnitelmaKaikkiDto.class);
            GeneratorData generatorData = GeneratorData.of(ops.getId(), dokumenttiId, kieli, DokumenttiTyyppi.AMOSAA, ktId);

            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = amosaaDokumenttiBuilderService.generateXML(ops, generatorData);
            handleConversionAndSending(doc, generateMetaData(generatorData, ops.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex, dokumenttiId, DokumenttiTyyppi.AMOSAA);
        }
    }

    @Override
    @Async(value = "docTaskExecutor")
    public void generateForYlops(Long dokumenttiId, Kieli kieli, String opsJson) {
        try {
            OpetussuunnitelmaExportDto ops = objectMapper.readValue(opsJson, OpetussuunnitelmaExportDto.class);
            GeneratorData generatorData = GeneratorData.of(ops.getId(), dokumenttiId, kieli, DokumenttiTyyppi.YLOPS, null);
            PerusteKaikkiDto perusteKaikkiDto = ylopsService.getOpetussuunnitelmaPeruste(ops.getPerusteenId(), ops.getPeruste().getGlobalVersion().getAikaleima());

            log.info("Luodaan PDF-dokumenttia (docId={}, {}, {})", dokumenttiId, generatorData.getTyyppi(), kieli);
            Document doc = ylopsDokumenttiBuilderService.generateXML(ops, perusteKaikkiDto, generatorData);
            handleConversionAndSending(doc, generateMetaData(generatorData, ops.getNimi()), generatorData);
        } catch (Exception ex) {
            handleError(ex, dokumenttiId, DokumenttiTyyppi.YLOPS);
        }
    }

    private void handleConversionAndSending(Document document, DokumenttiMetaDto metaData, GeneratorData generatorData) throws DokumenttiException, IOException, TransformerException, SAXException {
        ByteArrayOutputStream xmlStream = pdfService.convertOps2XML(document);
        byte[] pdfData = pdfService.xhtml2pdf(document, xmlStream, metaData, generatorData.getTyyppi());
        log.info("PDF-dokumentti luotu. Lähetetään kutsuvalle servicelle...");
        commonExternalService.postPdfData(pdfData, xmlStream.toByteArray(), generatorData.getDokumenttiId(), generatorData.getTyyppi());
    }

    private void handleError(Exception ex, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        log.error("PDF-dokumentin luonti epäonnistui ({})", Throwables.getStackTraceAsString(ex));
        commonExternalService.updateDokumenttiTila(DokumenttiTila.EPAONNISTUI, dokumenttiId, tyyppi);
    }

    private DokumenttiMetaDto generateMetaData(GeneratorData generatorData, LokalisoituTekstiDto nimi) {
        return DokumenttiMetaDto.builder()
                .title(DokumenttiUtils.getTextString(generatorData.getKieli(), nimi))
                .subject(messages.translate(DokumenttiUtils.selectSubjectTranslationKey(generatorData.getTyyppi()), generatorData.getKieli()))
                .build();
    }
}
