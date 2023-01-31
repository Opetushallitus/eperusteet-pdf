package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KevytTekstiKappaleDto {
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
}
