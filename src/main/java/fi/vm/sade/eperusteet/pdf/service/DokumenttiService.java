package fi.vm.sade.eperusteet.pdf.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.util.DokumenttiTyyppi;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

public interface DokumenttiService {
//    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void setStarted(@P("dto") Dokumentti dto);

//    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void generateWithDto(@P("dto") Dokumentti dto, Long ktId) throws DokumenttiException, JsonProcessingException;

    void updateTila(Dokumentti dto, DokumenttiTila tila);

    Dokumentti findLatest(Long id, Integer revision, Kieli kieli);

//    @PreAuthorize("hasPermission(#id, 'peruste', 'LUKU')")
    @Transactional
    Dokumentti createDtoFor(long id, Kieli kieli, Integer revision, DokumenttiTyyppi tyyppi) throws JsonProcessingException;

    @PreAuthorize("permitAll()")
    byte[] get(Long id, Integer revision, Kieli kieli);
}
