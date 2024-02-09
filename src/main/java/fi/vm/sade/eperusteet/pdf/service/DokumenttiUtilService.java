package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;

public interface DokumenttiUtilService {
    void buildImages(DokumenttiBase docBase, GeneratorData generatorData);

    void buildKuva(DokumenttiBase docBase, Kuvatyyppi kuvatyyppi, GeneratorData generatorData);

    TermiDto getTermiFromExternalService(Long id, String avain, DokumenttiTyyppi tyyppi);
}
