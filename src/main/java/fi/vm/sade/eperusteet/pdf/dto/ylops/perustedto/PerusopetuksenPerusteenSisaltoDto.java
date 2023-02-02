package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class PerusopetuksenPerusteenSisaltoDto implements Serializable {
    private TekstiKappaleViiteDto sisalto;
    private Set<LaajaalainenOsaaminenDto> laajaalaisetosaamiset;
    private Set<VuosiluokkakokonaisuusDto> vuosiluokkakokonaisuudet;
    private Set<OppiaineDto> oppiaineet;
}
