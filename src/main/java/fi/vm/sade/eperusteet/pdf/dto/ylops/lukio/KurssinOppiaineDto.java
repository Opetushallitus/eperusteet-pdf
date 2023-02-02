package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KurssinOppiaineDto implements Serializable {
    private Long oppiaineId;
    private Integer jarjestys;
}
