package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KotoLaajaAlainenOsaaminenDto {
    private Long id;
    private LokalisoituTekstiDto teksti;
}
