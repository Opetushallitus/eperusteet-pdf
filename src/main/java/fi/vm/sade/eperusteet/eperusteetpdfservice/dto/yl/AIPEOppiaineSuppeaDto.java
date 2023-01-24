package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class AIPEOppiaineSuppeaDto extends AIPEOppiaineBaseDto {
    private Optional<Boolean> koosteinen;
    private Optional<Boolean> abstrakti;
    private Optional<Date> muokattu;
}
