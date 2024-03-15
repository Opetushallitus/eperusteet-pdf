package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.osaamismerkki.OsaamismerkkiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;

import java.util.List;
import java.util.Set;

public interface EperusteetService {
    KVLiiteJulkinenDto getKvLiite(Long id) throws JsonProcessingException;

    ArviointiAsteikkoDto getArviointiasteikko(Long id);

    List<OsaamismerkkiDto> getOsaamismerkit(Set<Long> koodit);
}
