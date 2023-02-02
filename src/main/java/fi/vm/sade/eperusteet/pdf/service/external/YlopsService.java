package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;


public interface YlopsService {
    OpetussuunnitelmaDto getOpetussuunnitelma(Long opsId);

    OpetussuunnitelmaDto getOpetussuunnitelmaTemp(Long opsId);

    byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi tyyppi, Kieli kieli);
}
