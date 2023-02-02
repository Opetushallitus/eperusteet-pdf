package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;

public interface DokumenttiStateService {

//    @PreAuthorize("isAuthenticated()")
    Dokumentti save(Dokumentti dokumentti);
}
