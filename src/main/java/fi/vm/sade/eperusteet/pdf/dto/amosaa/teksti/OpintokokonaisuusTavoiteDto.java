package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpintokokonaisuusTavoiteDto {
    private Long id;
    private Boolean perusteesta;
    private String tavoiteKoodi;
    private LokalisoituTekstiDto tavoite;
}
