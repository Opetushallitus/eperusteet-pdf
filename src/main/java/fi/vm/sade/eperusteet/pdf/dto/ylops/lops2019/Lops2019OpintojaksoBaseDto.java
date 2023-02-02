package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Lops2019OpintojaksoBaseDto {
    private boolean tuotu = false;
    private Long id;
    private String koodi;
    private Long laajuus;
    private LokalisoituTekstiDto nimi;
}
