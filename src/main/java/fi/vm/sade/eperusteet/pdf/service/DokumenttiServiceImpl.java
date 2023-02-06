package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.TemplateTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.repository.DokumenttiRepository;
import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.KVLiiteBuilderService;
import fi.vm.sade.eperusteet.pdf.service.external.AmosaaService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import fi.vm.sade.eperusteet.pdf.service.ylops.YlopsDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import fi.vm.sade.eperusteet.pdf.utils.LocalizedMessagesService;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.isTimePass;

@Slf4j
@Service
@Profile("default")
public class DokumenttiServiceImpl implements DokumenttiService {

    @Autowired
    private DokumenttiRepository dokumenttiRepository;

    @Autowired
    private DokumenttiStateService dokumenttiStateService;

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
    public Dokumentti generate(Dokumentti dokumentti, Long ktId) throws DokumenttiException {
        if (isTimePass(dokumentti) || dokumentti.getTila() != DokumenttiTila.LUODAAN) {
            setStarted(dokumentti);
            generateWithDto(dokumentti, ktId);
        } else {
            throw new BusinessRuleViolationException("PDF-dokumentin luonti on jo käynissä");
        }
        return dokumentti;
    }

    @Transactional
    @Override
    public Dokumentti getDto(Long id, Kieli kieli, Integer revision, DokumenttiTyyppi tyyppi) {
        Dokumentti dokumentti = findLatest(id, revision, kieli);

        if (dokumentti != null) {
            // Jos aloitusajasta on kulunut liian kauan, on luonti epäonnistunut
            if (dokumentti.getTila() != DokumenttiTila.VALMIS && dokumentti.getTila() != DokumenttiTila.EI_OLE) {
                if (isTimePass(dokumentti)) {
                    dokumentti.setTila(DokumenttiTila.EPAONNISTUI);
                    dokumentti = dokumenttiRepository.save(dokumentti);
                }
            }
            return dokumentti;
        } else {
            return createDtoFor(id, kieli, revision, tyyppi);
        }
    }

    @Transactional
    public Dokumentti createDtoFor(Long id, Kieli kieli, Integer revision, DokumenttiTyyppi tyyppi) {
        Dokumentti dokumentti = new Dokumentti();
        dokumentti.setSisaltoId(id);
        dokumentti.setTila(DokumenttiTila.EI_OLE);
        dokumentti.setTyyppi(tyyppi);
        dokumentti.setKieli(kieli);
        dokumentti.setRevision(revision);
        dokumentti.setAloitusaika(new Date());
        return dokumenttiRepository.save(dokumentti);
    }

    @Transactional
    @Override
    public void generateWithDto(Dokumentti dokumentti, Long ktId) throws DokumenttiException {
        log.info("Luodaan PDF-dokumenttia (id={}, {}, {})...", dokumentti.getSisaltoId(), dokumentti.getTyyppi(), dokumentti.getKieli());

        try {
            if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.PERUSTE)) {
                dokumentti.setData(createEperusteetPdfData(dokumentti));
            } else if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.OPS)) {
                dokumentti.setData(createAmoseePdfData(dokumentti, ktId));
            } else if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
                dokumentti.setData(createYlopsPdfData(dokumentti));
            } else {
                dokumentti.setData(createEperusteetKVLiitePdfData(dokumentti));
            }

            log.info("PDF-dokumentti luotu.");
            setFinished(dokumentti, DokumenttiTila.VALMIS);
        } catch (Exception ex) {
            log.error("PDF-dokumentin luonti epäonnistui.");
            setFinished(dokumentti, DokumenttiTila.EPAONNISTUI);
            throw new DokumenttiException(ex.getMessage(), ex);
        }
    }

    private byte[] createEperusteetPdfData(Dokumentti dokumentti) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getPerusteKaikkiDto()
        PerusteKaikkiDto perusteData = eperusteetService.getPerusteKaikkiDtoTemp(dokumentti.getSisaltoId(), dokumentti.getRevision());
        Document doc = eperusteetDokumenttiBuilderService.generateXML(dokumentti, perusteData);
        return pdfService.xhtml2pdf(doc, getMetaData(dokumentti, perusteData.getNimi()), TemplateTyyppi.PERUSTE);
    }

    private byte[] createAmoseePdfData(Dokumentti dokumentti, Long ktId) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
        OpetussuunnitelmaDto ops = amosaaService.getOpetussuunnitelmaTemp(ktId, dokumentti.getSisaltoId());
        Document doc = amosaaDokumenttiBuilderService.generateXML(dokumentti, ktId, ops);
        return pdfService.xhtml2pdf(doc, getMetaData(dokumentti, ops.getNimi()), TemplateTyyppi.AMOSAA);
    }

    private byte[] createYlopsPdfData(Dokumentti dokumentti) throws IOException, TransformerException, SAXException, ParserConfigurationException, DokumenttiException {
        //TODO: haetaan opintopolusta toistaiseksi testidataa, korvataan -> getOpetussuunnitelma()
        fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto ops = ylopsService.getOpetussuunnitelmaTemp(dokumentti.getSisaltoId());
        Document doc = ylopsDokumenttiBuilderService.generateXML(dokumentti, ops);
        return pdfService.xhtml2pdf(doc, getMetaData(dokumentti, ops.getNimi()), TemplateTyyppi.YLOPS);
    }

    private byte[] createEperusteetKVLiitePdfData(Dokumentti dokumentti) throws IOException, TransformerException, SAXException, ParserConfigurationException {
        PerusteKaikkiDto perusteData = eperusteetService.getPerusteKaikkiDtoTemp(dokumentti.getSisaltoId(), dokumentti.getRevision());
        Document doc = kvLiiteBuilderService.generateXML(perusteData, dokumentti.getKieli());
        return pdfService.xhtml2pdf(doc, getMetaData(dokumentti, perusteData.getNimi()), TemplateTyyppi.KVLIITE);
    }

    @Override
    public void setStarted(Dokumentti dto) {
        dto.setAloitusaika(new Date());
        dto.setValmistumisaika(null);
        dto.setTila(DokumenttiTila.LUODAAN);
        dokumenttiStateService.save(dto);
    }

    private void setFinished(Dokumentti dto, DokumenttiTila tila) {
        dto.setValmistumisaika(new Date());
        dto.setTila(tila);
        dokumenttiStateService.save(dto);
    }

    private void updateTila(Dokumentti dto, DokumenttiTila tila) {
        dto.setTila(tila);
        dokumenttiStateService.save(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] get(Long perusteId, Integer revision, Kieli kieli) {
        List<Dokumentti> documents = dokumenttiRepository.findBySisaltoIdAndRevisionAndKieli(perusteId, revision, kieli, Sort.by(Sort.Direction.DESC, "revision"));
        Optional<Dokumentti> dokumentti;
        if (!documents.isEmpty()) {
            dokumentti = dokumenttiRepository.findById(documents.get(0).getId());
            return dokumentti.map(Dokumentti::getData).orElse(null);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Dokumentti findLatest(Long id, Integer revision, Kieli kieli) {
        Sort sort = Sort.by(Sort.Direction.DESC, "valmistumisaika");
        List<Dokumentti> documents = dokumenttiRepository.findBySisaltoIdAndRevisionAndKieli(id, revision, kieli, sort);

        // Kvliite ei riipu suoritustavasta
//            if (GeneratorVersion.KVLIITE.equals(version)) {
//                documents = dokumenttiRepository.findBySisaltoIdAndRevisionAndKieli(
//                        id, revision, kieli, sort);
//            } else {
//                documents = dokumenttiRepository.findBySisaltoIdAndRevisionAndKieli(
//                        id, kieli, DokumenttiTila.VALMIS, suoritustapakoodi,
//                        version != null ? version : GeneratorVersion.UUSI, sort);
//            }

        if (documents.isEmpty()) {
            return null;
        } else {
            return documents.get(0);
        }
    }

    private DokumenttiMetaDto getMetaData(Dokumentti dokumentti, LokalisoituTekstiDto nimi) {
        return DokumenttiMetaDto.builder()
                .title(DokumenttiUtils.getTextString(dokumentti.getKieli(), nimi))
                .subject(messages.translate(DokumenttiUtils.selectSubjectTranslationKey(dokumentti.getTyyppi()), dokumentti.getKieli()))
                .build();
    }
}
