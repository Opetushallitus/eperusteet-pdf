package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VapaaTekstiDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
}
