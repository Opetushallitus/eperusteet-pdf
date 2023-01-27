package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TermiDto;

public interface EperusteetService {
    PerusteKaikkiDto getPerusteKaikkiDto(Long id, Integer revision) throws JsonProcessingException;

    PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id, Integer revision) throws JsonProcessingException;

    KVLiiteJulkinenDto getKvLiite(Long id) throws JsonProcessingException;

    TermiDto getTermi(Long id, String avain);
}
