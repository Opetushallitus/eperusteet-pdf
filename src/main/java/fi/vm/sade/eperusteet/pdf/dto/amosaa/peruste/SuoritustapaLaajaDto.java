package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneModuuliDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuoritustapaLaajaDto {
    private Suoritustapakoodi suoritustapakoodi;
    private LaajuusYksikko laajuusYksikko;
    private RakenneModuuliDto rakenne;
    @JsonProperty("tutkinnonOsaViitteet")
    private Set<TutkinnonOsaViiteSuppeaDto> tutkinnonOsat;
}
