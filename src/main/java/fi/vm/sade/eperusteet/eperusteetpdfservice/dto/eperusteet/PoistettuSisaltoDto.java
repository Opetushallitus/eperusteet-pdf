package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.PoistetunTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoistettuSisaltoDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private String luoja;
    private Date luotu;
    private String muokkaaja;
    private Date muokattu;
    private PoistetunTyyppi tyyppi;
    private Long poistettuId;
}
