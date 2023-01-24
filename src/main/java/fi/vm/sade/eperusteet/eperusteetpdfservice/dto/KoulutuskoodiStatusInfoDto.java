package fi.vm.sade.eperusteet.eperusteetpdfservice.dto;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.TutkinnonOsaViiteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutuskoodiStatusInfoDto {
    private Long id;
    private Suoritustapakoodi suoritustapa;
    private TutkinnonOsaViiteDto viite;
}
