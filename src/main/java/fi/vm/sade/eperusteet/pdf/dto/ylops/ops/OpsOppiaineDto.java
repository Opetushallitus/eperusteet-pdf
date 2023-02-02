package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpsOppiaineDto {
    private boolean oma;
    private OppiaineDto oppiaine;
    private Integer jnro;
}
