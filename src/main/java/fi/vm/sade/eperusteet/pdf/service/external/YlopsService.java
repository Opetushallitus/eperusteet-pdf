package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;

public interface YlopsService {
    OrganisaatioDto getOrganisaatio(String oid);

    PerusteKaikkiDto getPerusteKaikkiDto(Long perusteId);
}
