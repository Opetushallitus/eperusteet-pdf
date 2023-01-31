package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TekstiKappaleJulkinenDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private UUID tunniste;
}
