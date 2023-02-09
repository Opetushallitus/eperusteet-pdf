package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;

public interface EperusteetService {

    PerusteKaikkiDto getPerusteKaikkiDto(Long id, Integer revision);
    KVLiiteJulkinenDto getKvLiite(Long id) throws JsonProcessingException;

    ArviointiAsteikkoDto getArviointiasteikko(Long id);
}
