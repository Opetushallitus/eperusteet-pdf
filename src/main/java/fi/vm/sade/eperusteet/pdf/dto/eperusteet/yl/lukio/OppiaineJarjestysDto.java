package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineJarjestysDto {
    private Long id;
    private Long oppiaineId; // parent
    private Long jarjestys;
}
