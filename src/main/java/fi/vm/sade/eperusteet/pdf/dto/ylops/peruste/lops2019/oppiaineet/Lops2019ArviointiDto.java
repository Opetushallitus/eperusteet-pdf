package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lops2019ArviointiDto {
    private LokalisoituTekstiDto kuvaus;
}
