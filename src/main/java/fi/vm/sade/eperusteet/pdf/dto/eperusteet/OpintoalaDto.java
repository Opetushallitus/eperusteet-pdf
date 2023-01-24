package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
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
