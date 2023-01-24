package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;

public interface EperusteetService {
    PerusteKaikkiDto getKaikkiDto(Long id, Integer revision) throws JsonProcessingException;
}
