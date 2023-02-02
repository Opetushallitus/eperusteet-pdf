package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TekstiOsaDto {
    private Long id;
    private PerusteenLokalisoituTekstiDto otsikko;
    private PerusteenLokalisoituTekstiDto teksti;
}
