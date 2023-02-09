package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SuorituspolkuRiviJulkinenDto {
    private Long id;
    private LokalisoituTekstiDto kuvaus;
    private Set<String> koodit;
}
