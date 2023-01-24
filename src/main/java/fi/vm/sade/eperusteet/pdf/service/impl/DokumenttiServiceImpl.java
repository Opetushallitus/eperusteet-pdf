package fi.vm.sade.eperusteet.pdf.service.impl;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.repository.DokumenttiRepository;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiService;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiStateService;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiDto;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Profile("default")
public class DokumenttiServiceImpl implements DokumenttiService {

    @Autowired
    private DokumenttiRepository dokumenttiRepository;

    @Autowired
    private DokumenttiStateService dokumenttiStateService;
//
//    @Autowired
//    private PerusteRepository perusteRepository;
//
//    @Autowired
//    private DokumenttiNewBuilderService newBuilder;
//
//    @Autowired
//    private PdfGenerationService pdfGenerationService;
//
//    @Autowired
//    private KVLiiteBuilderService kvLiiteBuilderService;
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
    @Override
    @Transactional
    public Dokumentti createDtoFor(long id, Kieli kieli, Integer revision) {
        String name = SecurityUtil.getAuthenticatedPrincipal().getName();
        Dokumentti dokumentti = new Dokumentti();
        dokumentti.setSisaltoId(id);
        dokumentti.setTyyppi(DokumenttiTyyppi.PERUSTE);
        dokumentti.setTila(DokumenttiTila.EI_OLE);
        dokumentti.setKieli(kieli);
        dokumentti.setRevision(revision);
        dokumentti.setAloitusaika(new Date());

        Dokumentti saved = dokumenttiRepository.save(dokumentti);
        return saved;
    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi) {
//        return findLatest(id, kieli, suoritustapakoodi, null);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion version) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "valmistumisaika");
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
//            dto.setSisaltoId(id);
//            dto.setKieli(kieli);
//            dto.setTila(DokumenttiTila.EI_OLE);
//            return dto;
//        }
//    }
//
//    @Override
//    @Transactional(noRollbackFor = DokumenttiException.class)
//    @Async(value = "docTaskExecutor")
//    public void generateWithDto(DokumenttiDto dto) throws DokumenttiException {
//        dto.setTila(DokumenttiTila.LUODAAN);
//        dokumenttiStateService.save(dto);
//
//        Optional<Dokumentti> dokumentti = dokumenttiRepository.findById(dto.getId());
//        if (dokumentti.isPresent()) {
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
//    public void generateWithDtoSynchronous(DokumenttiDto dto) throws DokumenttiException {
//        dto.setTila(DokumenttiTila.LUODAAN);
//        dokumenttiStateService.save(dto);
//
//        Optional<Dokumentti> dokumentti = dokumenttiRepository.findById(dto.getId());
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
//    public byte[] get(Long id) {
//        Optional<Dokumentti> dokumentti = dokumenttiRepository.findById(id);
//
//        if (dokumentti.isPresent()) {
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
//    public Long getDokumenttiId(Long perusteId, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion generatorVersion) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "valmistumisaika");
//
//        PerustePerusteDto peruste = perusteRepository.findOne(perusteId);
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
//    public void setStarted(DokumenttiDto dto) {
//        dto.setAloitusaika(new Date());
//        dto.setLuoja(SecurityUtil.getAuthenticatedPrincipal().getName());
//        dto.setTila(DokumenttiTila.JONOSSA);
//        dokumenttiStateService.save(dto);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public DokumenttiDto query(Long id) {
//        Optional<Dokumentti> dokumentti = dokumenttiRepository.findById(id);
//        if (dokumentti.isPresent()) {
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
////            case VANHA:
////                throw new BusinessRuleViolationException("vanha-generointi-poistettu-kaytosta");
//            case UUSI:
//                Document doc = newBuilder.generateXML(peruste, dokumentti);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.peruste", kieli));
//                toReturn = pdfGenerationService.xhtml2pdf(doc, meta);
//
//                break;
//            case KVLIITE:
//                doc = kvLiiteBuilderService.generateXML(peruste, kieli);
//
//                meta.setSubject(messages.translate("docgen.meta.subject.kvliite", kieli));
//                toReturn = pdfGenerationService.xhtml2pdf(doc, version, meta);
//                break;
//            default:
//                break;
//        }
//        return toReturn;
//    }
//
//    @Override
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
}
