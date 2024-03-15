package fi.vm.sade.eperusteet.pdf.dto.amosaa.osaamismerkki;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.KooditettuDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OsaamismerkkiKoodiDto implements KooditettuDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private String koodi;

    @Override
    public void setKooditettu(LokalisoituTekstiDto kooditettu) {
        this.nimi = kooditettu;
    }
}

