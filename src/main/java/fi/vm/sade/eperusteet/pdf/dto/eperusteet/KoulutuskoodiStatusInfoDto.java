package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteDto;
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
