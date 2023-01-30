package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpintokokonaisuusArviointiDto {
    private Long id;
    private Boolean perusteesta;
    private LokalisoituTekstiDto arviointi;
}
