package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TekstiosaDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto teksti;

    public TekstiosaDto(LokalisoituTekstiDto otsikko, LokalisoituTekstiDto teksti) {
        this.otsikko = otsikko;
        this.teksti = teksti;
    }
}
