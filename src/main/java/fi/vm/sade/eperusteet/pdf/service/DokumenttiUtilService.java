package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;

public interface DokumenttiUtilService {
    void buildImages(DokumenttiBase docBase, Long sisaltoId, DokumenttiTyyppi tyyppi);

    void buildKuva(DokumenttiBase docBase, Kuvatyyppi kuvatyyppi, DokumenttiTyyppi dokumenttiTyyppi, Long ktId);

    TermiDto getTermiFromExternalService(Long id, String avain, DokumenttiTyyppi tyyppi);
}
