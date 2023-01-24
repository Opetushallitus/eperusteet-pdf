package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KurssinOppiaineDto implements Serializable {
    private Long oppiaineId;
    private Integer jarjestys;
}
