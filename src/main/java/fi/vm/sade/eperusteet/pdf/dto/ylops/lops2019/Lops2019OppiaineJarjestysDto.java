package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019OppiaineJarjestysDto {
    private Long id;
    private String koodi;
    private Integer jarjestys;
}
