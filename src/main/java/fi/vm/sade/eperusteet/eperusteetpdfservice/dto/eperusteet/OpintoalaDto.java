package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpintoalaDto {
    private String koodi;
    private LokalisoituTekstiDto nimi;
}
