package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineDto extends OppiaineBaseUpdateDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OppiaineSuppeaDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;
}
