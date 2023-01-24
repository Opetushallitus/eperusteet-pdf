package fi.vm.sade.eperusteet.eperusteetpdfservice.service;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Dokumentti;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DokumenttiStateService {

    @PreAuthorize("isAuthenticated()")
    Dokumentti save(Dokumentti dokumentti);
}
