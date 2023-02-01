package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;

public interface AmosaaService {
    OpetussuunnitelmaDto getOpetussuunnitelma(Long ktId, Long opsId);

    PerusteKaikkiDto getPerusteKaikkiDto(Long id) throws JsonProcessingException;

    TermiDto getTermi(Long ktId, String avain);

    // TODO: remove temp funktiot
    OpetussuunnitelmaDto getOpetussuunnitelmaTemp(Long ktId, Long opsId);
    PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id) throws JsonProcessingException;
}
