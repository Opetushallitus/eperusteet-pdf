package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;

public interface DokumenttiUtilService {
    void buildImages(DokumenttiBase docBase, Long sisaltoId, DokumenttiTyyppi tyyppi);

    void buildKuva(DokumenttiBase docBase, Kuvatyyppi kuvatyyppi, DokumenttiTyyppi dokumenttiTyyppi, Long ktId);
}
