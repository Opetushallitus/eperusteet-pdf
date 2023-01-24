package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.Dokumentti;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DokumenttiStateService {

    @PreAuthorize("isAuthenticated()")
    Dokumentti save(Dokumentti dokumentti);
}
