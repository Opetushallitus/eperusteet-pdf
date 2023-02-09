package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.dto.enums.Tila;
import fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto.PerusteenLokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteTekstiKappaleDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private PerusteenLokalisoituTekstiDto nimi;
    private PerusteenLokalisoituTekstiDto teksti;
    private Tila tila;
    private String tunniste;
    private String osanTyyppi;
    private Boolean liite;
}
