package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;

import java.io.InputStream;
import java.util.UUID;

public interface CommonExternalService {
    InputStream getLiitetiedosto(Long sisaltoId, UUID fileName, DokumenttiTyyppi tyyppi);

    byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi kuvatyyppi, Kieli kieli, DokumenttiTyyppi dokumenttityyppi, Long ktId);

    TermiDto getTermi(Long id, String avain, DokumenttiTyyppi dokumenttityyppi);
}
