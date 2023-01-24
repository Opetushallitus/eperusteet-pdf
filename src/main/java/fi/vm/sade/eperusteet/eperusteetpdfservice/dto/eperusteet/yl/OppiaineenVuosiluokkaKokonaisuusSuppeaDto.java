package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineenVuosiluokkaKokonaisuusSuppeaDto {
    private Long id;
    private Reference vuosiluokkaKokonaisuus;
}
