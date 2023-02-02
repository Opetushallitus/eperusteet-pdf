package fi.vm.sade.eperusteet.pdf.dto.ylops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoistettuDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private Boolean palautettu;
    private String luoja;
    private Date luotu;
    private String muokkaaja;
    private Date muokattu;
}
