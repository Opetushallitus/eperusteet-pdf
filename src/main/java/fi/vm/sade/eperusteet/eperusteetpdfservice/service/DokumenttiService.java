package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.GeneratorVersion;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.service.exception.DokumenttiException;
import fi.vm.sade.eperusteet.eperusteetpdfservice.service.util.DokumenttiDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

public interface DokumenttiService {
    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void setStarted(@P("dto") DokumenttiDto dto);

    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void generateWithDto(@P("dto") DokumenttiDto dto) throws DokumenttiException;

    @PreAuthorize("hasPermission(#dto.perusteId, 'peruste', 'LUKU')")
    void generateWithDtoSynchronous(@P("dto") DokumenttiDto dto) throws DokumenttiException;

    @PreAuthorize("hasPermission(#id, 'peruste', 'LUKU')")
    DokumenttiDto createDtoFor(
            @P("id") final long id,
            Kieli kieli,
            Suoritustapakoodi suoritustapakoodi,
            GeneratorVersion version
    );

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
}
