package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioPerusteenOsaDto {

    private Long id;
    private Date luotu;
    private Date muokattu;
    private String muokkaaja;
    private String muokkaajanNimi;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto teksti;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto yleiskuvaus;
    private String tila;
    private String tunniste;
    private String osanTyyppi;

}
