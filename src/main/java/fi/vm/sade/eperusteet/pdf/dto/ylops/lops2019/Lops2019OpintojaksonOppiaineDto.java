package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = { "koodi" })
public class Lops2019OpintojaksonOppiaineDto {
    private String koodi;
    private Long laajuus;
    private Integer jarjestys;
}
