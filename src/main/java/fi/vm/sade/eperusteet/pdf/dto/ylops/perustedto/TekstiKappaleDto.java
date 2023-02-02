package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TekstiKappaleDto {
    private Long id;
    private Date luotu;
    private Date muokattu;
    private String muokkaaja;
    private String muokkaajanNimi;
    private PerusteenLokalisoituTekstiDto nimi;
    private PerusteenLokalisoituTekstiDto teksti;
    private String tila;
    private String tunniste;
    private String osanTyyppi;
}
