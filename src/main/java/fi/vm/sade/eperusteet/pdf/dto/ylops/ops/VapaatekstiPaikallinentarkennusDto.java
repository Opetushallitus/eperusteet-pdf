package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VapaatekstiPaikallinentarkennusDto {
    private Long perusteenVapaaTekstiId;
    private LokalisoituTekstiDto paikallinenTarkennus;
}
