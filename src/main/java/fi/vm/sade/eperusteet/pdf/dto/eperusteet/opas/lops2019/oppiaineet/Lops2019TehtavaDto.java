package fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019TehtavaDto {
    private LokalisoituTekstiDto kuvaus;
}
