package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.PoistetunTyyppi;
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
