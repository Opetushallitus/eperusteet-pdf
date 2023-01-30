package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.KooditettuDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuvaLaajaAlainenOsaaminenDto implements KooditettuDto {

    private String nimiKoodi;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private Boolean liite;

    @Override
    public void setKooditettu(LokalisoituTekstiDto kooditettu) {
        this.nimi = kooditettu;
    }
}
