package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TekstiKappaleNimiDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
}
