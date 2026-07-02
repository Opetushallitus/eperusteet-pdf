package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaiteenosaDto {
    private Long id;
    private Long perusteenTaiteenosanId;
    private LokalisoituTekstiDto paikallinenTarkennus;
}
