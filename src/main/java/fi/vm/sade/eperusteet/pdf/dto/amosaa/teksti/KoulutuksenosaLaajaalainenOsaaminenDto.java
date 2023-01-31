package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.KooditettuDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KoulutuksenosaLaajaalainenOsaaminenDto implements KooditettuDto {

    private Long id;
    private String koodiUri;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto laajaAlaisenOsaamisenKuvaus;

    @Override
    public void setKooditettu(LokalisoituTekstiDto kooditettu) {
        this.nimi = kooditettu;
    }
}
