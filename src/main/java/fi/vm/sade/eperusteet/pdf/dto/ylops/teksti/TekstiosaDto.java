package fi.vm.sade.eperusteet.pdf.dto.ylops.teksti;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TekstiosaDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto teksti;

    public TekstiosaDto(LokalisoituTekstiDto otsikko, LokalisoituTekstiDto teksti) {
        this.otsikko = otsikko;
        this.teksti = teksti;
    }
}
