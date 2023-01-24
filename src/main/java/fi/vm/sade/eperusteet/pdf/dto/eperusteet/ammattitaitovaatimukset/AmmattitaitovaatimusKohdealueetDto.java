package fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmmattitaitovaatimusKohdealueetDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private List<AmmattitaitovaatimusKohdeDto> vaatimuksenKohteet;
}
