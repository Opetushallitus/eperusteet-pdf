package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;

public interface DokumenttiService {
    byte[] generate(Long id, Integer revision, Kieli kieli, DokumenttiTyyppi tyyppi, Long ktId) throws DokumenttiException;
}
