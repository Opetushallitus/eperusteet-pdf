package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.repository.DokumenttiRepository;
import fi.vm.sade.eperusteet.pdf.service.amosaa.AmosaaDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetDokumenttiBuilderService;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.EperusteetPdfService;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiTyyppi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("default")
public class DokumenttiServiceImpl implements DokumenttiService {

    @Autowired
    private DokumenttiRepository dokumenttiRepository;

    @Autowired
    private DokumenttiStateService dokumenttiStateService;

    @Autowired
    EperusteetService eperusteetService;

    @Autowired
    EperusteetPdfService pdfGenerationService;

    @Autowired
    EperusteetDokumenttiBuilderService eperusteetDokumenttiBuilderService;

    @Autowired
    AmosaaDokumenttiBuilderService amosaaDokumenttiBuilderService;

    @Value("classpath:docgen/fop.xconf")
    private Resource fopConfig;

    // FIXME: Tämä service pitää mockata
    @Value("${spring.profiles.active:normal}")
    private String activeProfile;

    @Override
    @Transactional
    public Dokumentti createDtoFor(long id, Kieli kieli, Integer revision, DokumenttiTyyppi tyyppi) {
        Dokumentti dokumentti = new Dokumentti();
        dokumentti.setSisaltoId(id);
        dokumentti.setTila(DokumenttiTila.EI_OLE);
        dokumentti.setTyyppi(tyyppi);
        dokumentti.setKieli(kieli);
        dokumentti.setRevision(revision);
        dokumentti.setAloitusaika(new Date());
        return dokumenttiRepository.save(dokumentti);
    }

    @Override
    @Transactional(noRollbackFor = DokumenttiException.class)
    @Async(value = "docTaskExecutor")
    public void generateWithDto(Dokumentti dokumentti) throws DokumenttiException {
        updateTila(dokumentti, DokumenttiTila.LUODAAN);

        try {
            if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.PERUSTE)) {
                dokumentti.setData(eperusteetDokumenttiBuilderService.generatePdf(dokumentti));
            } else if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.OPS)) {
                dokumentti.setData(amosaaDokumenttiBuilderService.generatePdf(dokumentti));
            } else if (dokumentti.getTyyppi().equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA) ){

            } else {
                // TODO: poikkeus
            }
            dokumentti.setTila(DokumenttiTila.VALMIS);
            dokumentti.setValmistumisaika(new Date());
            dokumenttiRepository.save(dokumentti);
        } catch (Exception ex) {
            dokumentti.setTila(DokumenttiTila.EPAONNISTUI);
            dokumentti.setValmistumisaika(new Date());
            dokumenttiStateService.save(dokumentti);
            throw new DokumenttiException(ex.getMessage(), ex);
        }
    }

    @Override
    @Transactional
    public void setStarted(Dokumentti dto) {
        dto.setAloitusaika(new Date());
        dto.setTila(DokumenttiTila.JONOSSA);
        dokumenttiStateService.save(dto);
    }

    @Override
    @Transactional
    public void updateTila(Dokumentti dto, DokumenttiTila tila) {
        dto.setTila(tila);
        dokumenttiStateService.save(dto);
    }

//    private byte[] generateFor(Dokumentti dokumentti) throws ParserConfigurationException, IOException, TransformerException, SAXException {
//        Kieli kieli = dokumentti.getKieli();
//        byte[] toReturn = null;
//        ValidationResult result;
//
//        DokumenttiMetaDto meta = DokumenttiMetaDto.builder()
//                .title(DokumenttiUtils.getTextString(dokumentti.getKieli(), perusteData.getNimi()))
//                .build();
//
//        log.info("Luodaan dokumenttia (" + dokumentti.getSisaltoId() + ", " + dokumentti.getTyyppi() + ", " + kieli + ") perusteelle.");
//        Document doc = newBuilder.generateXML(dokumentti);
//
////        meta.setSubject(messages.translate("docgen.meta.subject.peruste", kieli));
//        toReturn = pdfGenerationService.xhtml2pdf(doc, meta);
//
//        switch (version) {
//            case UUSI:
//                Document doc = newBuilder.generateXML(perusteData, dokumentti);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.peruste", kieli));
//                toReturn = pdfGenerationService.xhtml2pdf(doc, meta);
//
//                break;
//            case KVLIITE:
//                doc = kvLiiteBuilderService.generateXML(perusteData, kieli);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.kvliite", kieli));
//                toReturn = pdfGenerationService.xhtml2pdf(doc, version, meta);
//                break;
//            default:
//                break;
//        }
//        return toReturn;
//    }

    @Override
    @Transactional(readOnly = true)
    public byte[] get(Long perusteId, Integer revision, Kieli kieli) {
        List<Dokumentti> documents = dokumenttiRepository.findBySisaltoIdAndRevisionAndKieli(perusteId, revision, kieli, Sort.by(Sort.Direction.DESC, "revision"));


        Optional<Dokumentti> dokumentti = dokumenttiRepository.findById(documents.get(0).getId());

        //            Peruste peruste = perusteRepository.findOne(dokumentti.getPerusteId());
        //            if (peruste == null) {
        //                return null;
        //            }
        //
        //            String name = SecurityUtil.getAuthenticatedPrincipal().getName();
        //            if (name.equals("anonymousUser") && !peruste.getTila().equals(PerusteTila.VALMIS) && julkaisutRepository.findAllByPeruste(peruste).isEmpty()) {
        //                return null;
        //            }
        return dokumentti.map(Dokumentti::getData).orElse(null);

    }

//        @Override
//        @Transactional(readOnly = true)
//        public DokumenttiDto findLatest(Long id, Integer revision, Kieli kieli) {
//            return findLatest(id, revision, kieli);
//        }

        @Override
        @Transactional(readOnly = true)
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

            if (documents.size() > 0) {
                return documents.get(0);
            } else {
//                DokumenttiDto dto = new DokumenttiDto();
//                dto.setSisaltoId(id);
//                dto.setKieli(kieli);
//                dto.setTila(DokumenttiTila.EI_OLE);
                return new Dokumentti();
            }
        }
}
