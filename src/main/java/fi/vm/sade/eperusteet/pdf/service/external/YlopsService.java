package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;

import java.util.List;


public interface YlopsService {
    OpetussuunnitelmaDto getOpetussuunnitelma(Long opsId);

    byte[] getDokumenttiLiite(Long opsId, Kuvatyyppi tiedostonimi);

    OpetussuunnitelmaDto getOpetussuunnitelmaTemp(Long opsId);

    List<TekstiKappaleViiteDto.Matala> getTekstiKappaleViiteOriginals(Long opsId, Long viiteId);

    TekstiKappaleViiteDto getLops2019PerusteTekstikappale(Long opsId, Long tekstikappaleId);

    TermiDto getTermi(Long opsId, String avain);

    byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi tyyppi, Kieli kieli);
}
