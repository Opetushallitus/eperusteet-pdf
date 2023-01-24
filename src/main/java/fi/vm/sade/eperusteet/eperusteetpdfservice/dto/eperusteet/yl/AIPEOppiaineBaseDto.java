package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIPEOppiaineBaseDto implements AIPEHasId {
    private Long id;
    private UUID tunniste;
    private Optional<LokalisoituTekstiDto> nimi;
    private Reference oppiaine;
}
