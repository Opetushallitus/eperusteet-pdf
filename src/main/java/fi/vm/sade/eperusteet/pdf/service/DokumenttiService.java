package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.pdf.domain.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

public interface DokumenttiService {
//    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void setStarted(@P("dto") Dokumentti dto);

//    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void generateWithDto(@P("dto") Dokumentti dto) throws DokumenttiException, JsonProcessingException;

    void updateTila(Dokumentti dto, DokumenttiTila tila);

    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void generateWithDtoSynchronous(@P("dto") DokumenttiDto dto) throws DokumenttiException;

//    @PreAuthorize("hasPermission(#id, 'peruste', 'LUKU')")
    @Transactional
    Dokumentti createDtoFor(long id, Kieli kieli, Integer revision) throws JsonProcessingException;

    @PreAuthorize("permitAll()")
    byte[] get(Long id);

    @PreAuthorize("permitAll()")
    Long getDokumenttiId(Long perusteId, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion generatorVersion);

    @PreAuthorize("permitAll()")
    DokumenttiDto query(Long id);

    @PreAuthorize("isAuthenticated()")
    DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi);

    @PreAuthorize("isAuthenticated()")
    DokumenttiDto findLatest(Long id, Kieli kieli, Suoritustapakoodi suoritustapakoodi, GeneratorVersion version);

    void paivitaDokumentit();

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
}
