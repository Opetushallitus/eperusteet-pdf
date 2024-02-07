package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;

public interface AmosaaService {
    PerusteKaikkiDto getPerusteKaikkiDto(Long cachedPerusteId);

    ArviointiAsteikkoDto getArviointiasteikko(Long arviointiAsteikkoId);
}
