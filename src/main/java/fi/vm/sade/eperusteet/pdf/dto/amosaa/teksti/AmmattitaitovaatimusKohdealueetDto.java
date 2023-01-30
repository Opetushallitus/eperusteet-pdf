package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmmattitaitovaatimusKohdealueetDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private List<AmmattitaitovaatimusKohdeDto> vaatimuksenKohteet;
}
