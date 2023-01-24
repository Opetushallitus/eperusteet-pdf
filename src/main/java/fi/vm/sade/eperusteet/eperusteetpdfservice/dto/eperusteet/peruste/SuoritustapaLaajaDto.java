package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne.RakenneModuuliDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteSuppeaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class SuoritustapaLaajaDto implements PerusteenSisaltoDto {
    private Suoritustapakoodi suoritustapakoodi;
    private LaajuusYksikko laajuusYksikko;
    private RakenneModuuliDto rakenne;
    @JsonProperty("tutkinnonOsaViitteet")
    private Set<TutkinnonOsaViiteSuppeaDto> tutkinnonOsat;
    private PerusteenOsaViiteDto.Laaja sisalto;
}
