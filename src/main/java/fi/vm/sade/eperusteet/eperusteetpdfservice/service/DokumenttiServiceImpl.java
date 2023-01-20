package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.DokumenttiException;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.GeneratorVersion;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Suoritustapakoodi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.DokumenttiDto;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import org.apache.pdfbox.preflight.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class DokumenttiServiceImpl implements DokumenttiService{
    @Override
    public void setStarted(DokumenttiDto dto) {

    }

    @Override
    public void generateWithDto(DokumenttiDto dto) throws DokumenttiException {

    }

    @Override
    public void generateWithDtoSynchronous(DokumenttiDto dto) throws DokumenttiException {

    }

    @Override
    public DokumenttiDto createDtoFor(long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion version) {
        return null;
    }

    @Override
    public byte[] get(Long id) {
        return new byte[0];
    }

    @Override
    public Long getDokumenttiId(Long perusteId, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion generatorVersion) {
        return null;
    }

    @Override
    public DokumenttiDto query(Long id) {
        return null;
    }

    @Override
    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi) {
        return null;
    }

    @Override
    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion version) {
        return null;
    }

    @Override
    public void paivitaDokumentit() {

    }
//    @Autowired
//    private DokumenttiRepository dokumenttiRepository;
//
//    @Dto
//    @Autowired
//    private DtoMapper mapper;
//
//    @Autowired
//    private PerusteRepository perusteRepository;
//
//    @Autowired
//    private DokumenttiBuilderService builder;
//
//    @Autowired
//    private DokumenttiNewBuilderService newBuilder;
//
//    @Autowired
//    private PdfService pdfService;
//
//    @Autowired
//    private KVLiiteBuilderService kvLiiteBuilderService;
//
//    @Autowired
//    private DokumenttiStateService dokumenttiStateService;
//
//    @Autowired
//    private PerusteprojektiRepository perusteprojektiRepository;
//
//    @Autowired
//    private LocalizedMessagesService messages;
//
//    @Autowired
//    private PlatformTransactionManager tm;
//
//    @Autowired
//    private JulkaisutRepository julkaisutRepository;
//
//    @Value("classpath:docgen/fop.xconf")
//    private Resource fopConfig;
//
//    // FIXME: Tämä service pitää mockata
//    @Value("${spring.profiles.active:normal}")
//    private String activeProfile;
//
//    @Override
//    @Transactional
//    @IgnorePerusteUpdateCheck
//    public DokumenttiDto createDtoFor(
//            long id,
//            Kieli kieli,
//            Suoritustapakoodi suoritustapakoodi,
//            GeneratorVersion version
//    ) {
//        String name = SecurityUtil.getAuthenticatedPrincipal().getName();
//        Dokumentti dokumentti = new Dokumentti();
//        dokumentti.setTila(DokumenttiTila.EI_OLE);
//        dokumentti.setKieli(kieli);
//        dokumentti.setAloitusaika(new Date());
//        dokumentti.setLuoja(name);
//        dokumentti.setPerusteId(id);
//        dokumentti.setSuoritustapakoodi(suoritustapakoodi);
//        dokumentti.setGeneratorVersion(version);
//
//        Peruste peruste = perusteRepository.findOne(id);
//        if (peruste != null) {
//            Dokumentti saved = dokumenttiRepository.save(dokumentti);
//            return mapper.map(saved, DokumenttiDto.class);
//        } else {
//            dokumentti.setTila(DokumenttiTila.EPAONNISTUI);
//            dokumentti.setVirhekoodi(DokumenttiVirhe.PERUSTETTA_EI_LOYTYNYT);
//            return mapper.map(dokumentti, DokumenttiDto.class);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    @IgnorePerusteUpdateCheck
//    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi) {
//        return findLatest(id, kieli, suoritustapakoodi, null);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    @IgnorePerusteUpdateCheck
//    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion version) {
//        Sort sort = new Sort(Sort.Direction.DESC, "valmistumisaika");
//
//        List<Dokumentti> documents;
//
//        // Kvliite ei riipu suoritustavasta
//        if (GeneratorVersion.KVLIITE.equals(version)) {
//            documents = dokumenttiRepository.findByPerusteIdAndKieliAndTilaAndGeneratorVersion(
//                    id, kieli, DokumenttiTila.VALMIS, version, sort);
//        } else {
//            documents = dokumenttiRepository.findByPerusteIdAndKieliAndTilaAndSuoritustapakoodiAndGeneratorVersion(
//                    id, kieli, DokumenttiTila.VALMIS, suoritustapakoodi,
//                    version != null ? version : GeneratorVersion.UUSI, sort);
//        }
//
//        if (documents.size() > 0) {
//            return mapper.map(documents.get(0), DokumenttiDto.class);
//        } else {
//            DokumenttiDto dto = new DokumenttiDto();
//            dto.setPerusteId(id);
//            dto.setKieli(kieli);
//            dto.setTila(DokumenttiTila.EI_OLE);
//            return dto;
//        }
//    }
//
//    @Override
//    @Transactional(noRollbackFor = DokumenttiException.class)
//    @IgnorePerusteUpdateCheck
//    @Async(value = "docTaskExecutor")
//    public void generateWithDto(DokumenttiDto dto) throws DokumenttiException {
//        dto.setTila(DokumenttiTila.LUODAAN);
//        dokumenttiStateService.save(dto);
//
//        Dokumentti dokumentti = dokumenttiRepository.findById(dto.getId());
//        if (dokumentti == null) {
//            dokumentti = mapper.map(dto, Dokumentti.class);
//        }
//
//        try {
//            dokumentti.setData(generateFor(dto));
//            dokumentti.setTila(DokumenttiTila.VALMIS);
//            dokumentti.setValmistumisaika(new Date());
//            dokumenttiRepository.save(dokumentti);
//        } catch (Exception ex) {
//            dto.setTila(DokumenttiTila.EPAONNISTUI);
//            dto.setVirhekoodi(DokumenttiVirhe.TUNTEMATON);
//            dto.setValmistumisaika(new Date());
//            dokumenttiStateService.save(dto);
//
//            throw new DokumenttiException(ex.getMessage(), ex);
//        }
//    }
//
//    @Override
//    @Transactional(noRollbackFor = DokumenttiException.class)
//    @IgnorePerusteUpdateCheck
//    public void generateWithDtoSynchronous(DokumenttiDto dto) throws DokumenttiException {
//        dto.setTila(DokumenttiTila.LUODAAN);
//        dokumenttiStateService.save(dto);
//
//        Dokumentti dokumentti = dokumenttiRepository.findById(dto.getId());
//        if (dokumentti == null) {
//            dokumentti = mapper.map(dto, Dokumentti.class);
//        }
//
//        try {
//            dokumentti.setData(generateFor(dto));
//            dokumentti.setTila(DokumenttiTila.VALMIS);
//            dokumentti.setValmistumisaika(new Date());
//            dokumenttiRepository.save(dokumentti);
//        } catch (Exception ex) {
//            dto.setTila(DokumenttiTila.EPAONNISTUI);
//            dto.setVirhekoodi(DokumenttiVirhe.TUNTEMATON);
//            dto.setValmistumisaika(new Date());
//            dokumenttiStateService.save(dto);
//
//            throw new DokumenttiException(ex.getMessage(), ex);
//        }
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    @IgnorePerusteUpdateCheck
//    public byte[] get(Long id) {
//        Dokumentti dokumentti = dokumenttiRepository.findById(id);
//
//        if (dokumentti != null) {
//            Peruste peruste = perusteRepository.findOne(dokumentti.getPerusteId());
//            if (peruste == null) {
//                return null;
//            }
//
//            String name = SecurityUtil.getAuthenticatedPrincipal().getName();
//            if (name.equals("anonymousUser") && !peruste.getTila().equals(PerusteTila.VALMIS) && julkaisutRepository.findAllByPeruste(peruste).isEmpty()) {
//                return null;
//            }
//
//            return dokumentti.getData();
//        }
//
//        return null;
//    }
//
//    @Override
//    @Transactional
//    @IgnorePerusteUpdateCheck
//    public Long getDokumenttiId(Long perusteId, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion generatorVersion) {
//        Sort sort = new Sort(Sort.Direction.DESC, "valmistumisaika");
//
//        Peruste peruste = perusteRepository.findOne(perusteId);
//        if (peruste == null) {
//            return null;
//        }
//
//        Set<Suoritustapa> suoritustavat = peruste.getSuoritustavat();
//        List<Dokumentti> documents;
//        if (suoritustavat.isEmpty()) {
//            documents = dokumenttiRepository
//                    .findByPerusteIdAndKieliAndTilaAndGeneratorVersion(
//                            perusteId, kieli, DokumenttiTila.VALMIS, generatorVersion, sort);
//        } else {
//            documents = dokumenttiRepository
//                    .findByPerusteIdAndKieliAndTilaAndSuoritustapakoodiAndGeneratorVersion(
//                            perusteId, kieli, DokumenttiTila.VALMIS, suoritustapakoodi, generatorVersion, sort);
//        }
//
//        if (!documents.isEmpty()) {
//            return documents.get(0).getId();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    @Transactional
//    @IgnorePerusteUpdateCheck
//    public void setStarted(DokumenttiDto dto) {
//        dto.setAloitusaika(new Date());
//        dto.setLuoja(SecurityUtil.getAuthenticatedPrincipal().getName());
//        dto.setTila(DokumenttiTila.JONOSSA);
//        dokumenttiStateService.save(dto);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    @IgnorePerusteUpdateCheck
//    public DokumenttiDto query(Long id) {
//        Dokumentti dokumentti = dokumenttiRepository.findById(id);
//        if (dokumentti != null) {
//            Peruste peruste = perusteRepository.findOne(dokumentti.getPerusteId());
//            String name = SecurityUtil.getAuthenticatedPrincipal().getName();
//            if (name.equals("anonymousUser") && !peruste.getTila().equals(PerusteTila.VALMIS) && julkaisutRepository.findAllByPeruste(peruste).isEmpty()) {
//                return null;
//            }
//        }
//        return mapper.map(dokumentti, DokumenttiDto.class);
//    }
//
//    private byte[] generateFor(DokumenttiDto dto)
//            throws IOException, TransformerException, ParserConfigurationException, SAXException {
//
//        Peruste peruste = perusteRepository.findOne(dto.getPerusteId());
//        Kieli kieli = dto.getKieli();
//        Dokumentti dokumentti = mapper.map(dto, Dokumentti.class);
//        byte[] toReturn = null;
//        ValidationResult result;
//        GeneratorVersion version = dto.getGeneratorVersion();
//
//        DokumenttiMetaDto meta = DokumenttiMetaDto.builder()
//                .title(DokumenttiUtils.getTextString(dokumentti.getKieli(), peruste.getNimi()))
//                .build();
//
//        log.info("Luodaan dokumenttia (" + dto.getPerusteId() + ", " + dto.getSuoritustapakoodi() + ", "
//                + kieli + ", " + version + ") perusteelle.");
//        switch (version) {
//            case VANHA:
//                throw new BusinessRuleViolationException("vanha-generointi-poistettu-kaytosta");
//            case UUSI:
//                Document doc = newBuilder.generateXML(peruste, dokumentti);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.peruste", kieli));
//                toReturn = pdfService.xhtml2pdf(doc, meta);
//
//                /*
//                // Validoidaan dokumnetti
//                result = DokumenttiUtils.validatePdf(toReturn);
//                if (result.isValid()) {
//                    log.debug("Dokumentti (" + dto.getPerusteId() + ", "
//                            + dto.getSuoritustapakoodi() + ", " + kieli + ") on PDF/A-1b mukainen.");
//                } else {
//                    log.debug("Dokumentti (" + dto.getPerusteId() + ", " + dto.getSuoritustapakoodi() + ", "
//                            + kieli + ") ei ole PDF/A-1b mukainen. Dokumentti sisältää virheen/virheet:");
//                    result.getErrorsList().forEach(error -> log
//                            .debug("  - " + error.getDetails() + " (" + error.getErrorCode() + ")"));
//                }
//                */
//
//                break;
//            case KVLIITE:
//                doc = kvLiiteBuilderService.generateXML(peruste, kieli);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.kvliite", kieli));
//                toReturn = pdfService.xhtml2pdf(doc, version, meta);
//
//                /*
//                // Validoi kvliite
//                result = DokumenttiUtils.validatePdf(toReturn);
//                if (result.isValid()) {
//                    log.debug("Dokumentti (" + dto.getPerusteId() + ", " + kieli + ") on PDF/A-1b mukainen.");
//                } else {
//                    log.debug("Dokumentti (" + dto.getId() + ", " + kieli
//                            + ") ei ole PDF/A-1b mukainen. Dokumentti sisältää virheen/virheet:");
//                    result.getErrorsList().forEach(error -> log
//                            .debug("  - " + error.getDetails() + " (" + error.getErrorCode() + ")"));
//                }
//                */
//
//                break;
//            default:
//                break;
//        }
//        return toReturn;
//    }
//
//    @Override
//    @IgnorePerusteUpdateCheck
//    @Transactional(propagation = Propagation.NEVER)
//    public void paivitaDokumentit() {
//
//        // Haetaan päivitettävät dokumentit
//        TransactionTemplate template = new TransactionTemplate(tm);
//        List<PerusteprojektiDokumenttiDto> tarkistettavat = template.execute(status -> mapper.mapAsList(
//                perusteprojektiRepository.findAllByTilaAndPerusteTyyppi(ProjektiTila.JULKAISTU, PerusteTyyppi.NORMAALI),
//                PerusteprojektiDokumenttiDto.class));
//
//        List<DokumenttiDto> paivitettavat = getPaivitettavat(tarkistettavat);
//
//        log.debug("Päivitetään " + paivitettavat.size() + " dokumenttia");
//
//        int counter = 1;
//        for (DokumenttiDto d : paivitettavat) {
//            try {
//                paivitaDokumentti(d, counter);
//            } catch (RuntimeException e) {
//                log.error(e.getLocalizedMessage(), e);
//            }
//            counter++;
//        }
//    }
//
//    @Transactional(propagation = Propagation.NEVER)
//    private List<DokumenttiDto> getPaivitettavat(List<PerusteprojektiDokumenttiDto> tarkistettavat) {
//
//        TransactionTemplate template = new TransactionTemplate(tm);
//
//        List<DokumenttiDto> paivitettavat = new ArrayList<>();
//        for (PerusteprojektiDokumenttiDto pp : tarkistettavat) {
//            PerusteDokumenttiDto p = pp.getPeruste();
//
//            for (Kieli kieli : p.getKielet()) {
//                for (SuoritustapaDto st : p.getSuoritustavat()) {
//                    template.execute(status -> {
//                        DokumenttiDto latest = findLatest(p.getId(), kieli, st.getSuoritustapakoodi(), GeneratorVersion.UUSI);
//                        if (latest != null
//                                && latest.getAloitusaika() != null
//                                && latest.getAloitusaika().before(p.getViimeisinJulkaisuAika().orElse(p.getGlobalVersion().getAikaleima()))) {
//                            paivitettavat.add(latest);
//                        }
//
//                        DokumenttiDto latestKvliite = findLatest(p.getId(), kieli, st.getSuoritustapakoodi(), GeneratorVersion.KVLIITE);
//                        if (latestKvliite != null
//                                && !DokumenttiTila.EI_OLE.equals(latestKvliite.getTila())
//                                && (latestKvliite.getAloitusaika() == null || latestKvliite.getAloitusaika()
//                                .before(p.getViimeisinJulkaisuAika().orElse(p.getGlobalVersion().getAikaleima())))) {
//                            paivitettavat.add(latestKvliite);
//                        }
//                        return true;
//                    });
//                }
//            }
//        }
//
//        return paivitettavat;
//    }
//
//    @Transactional(propagation = Propagation.NEVER)
//    private void paivitaDokumentti(DokumenttiDto latest, int counter) {
//
//        TransactionTemplate template = new TransactionTemplate(tm);
//
//        template.execute(status -> {
//
//            log.debug(String.format("%04d", counter)
//                    + " Aloitetaan perusteelle (" + latest.getPerusteId() + ", " + latest.getSuoritustapakoodi()
//                    + ", " + latest.getKieli() + ", " + latest.getGeneratorVersion() + ") uuden dokumentin luonti.");
//            try {
//                DokumenttiDto createDtoFor = createDtoFor(
//                        latest.getPerusteId(),
//                        latest.getKieli(),
//                        latest.getSuoritustapakoodi(),
//                        GeneratorVersion.UUSI
//                );
//                setStarted(createDtoFor);
//                generateWithDtoSynchronous(createDtoFor);
//
//                return true;
//
//            } catch (DokumenttiException e) {
//                log.error(e.getLocalizedMessage(), e);
//            }
//
//            return false;
//
//        });
//    }

}
