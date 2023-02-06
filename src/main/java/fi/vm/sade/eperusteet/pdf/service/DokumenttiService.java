package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DokumenttiService {

    Dokumentti generate(Dokumentti dokumentti, Long ktId) throws DokumenttiException;

    Dokumentti getDto(Long id, Kieli fi, Integer revision, DokumenttiTyyppi peruste);

    Dokumentti findLatest(Long id, Integer revision, Kieli kieli);

    void generateWithDto(Dokumentti dokumentti, Long ktId) throws DokumenttiException;

    void setStarted(Dokumentti dto);

    @PreAuthorize("permitAll()")
    byte[] get(Long id, Integer revision, Kieli kieli);
}
