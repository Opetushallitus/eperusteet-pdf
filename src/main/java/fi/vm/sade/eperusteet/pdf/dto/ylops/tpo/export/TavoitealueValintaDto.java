package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TavoitealueValintaDto {
    private Long tavoitealueId;
    private String koodiUri;
}
