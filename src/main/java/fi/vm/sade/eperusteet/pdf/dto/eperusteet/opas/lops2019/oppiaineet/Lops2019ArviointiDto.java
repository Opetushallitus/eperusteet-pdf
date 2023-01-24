package fi.vm.sade.eperusteet.pdf.dto.eperusteet.opas.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lops2019ArviointiDto {
    private LokalisoituTekstiDto kuvaus;
}
