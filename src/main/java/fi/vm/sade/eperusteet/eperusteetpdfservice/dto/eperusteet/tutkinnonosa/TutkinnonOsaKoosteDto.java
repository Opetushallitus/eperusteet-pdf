package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonosa;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutkinnonOsaKoosteDto {
    private LokalisoituTekstiDto nimi;
    private String koodiArvo;
}
