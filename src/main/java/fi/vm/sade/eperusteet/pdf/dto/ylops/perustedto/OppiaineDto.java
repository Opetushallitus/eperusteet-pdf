package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class OppiaineDto {
    private Long id;
    private UUID tunniste;
    private Boolean koosteinen;
    private Boolean abstrakti;
    private PerusteenLokalisoituTekstiDto nimi;
    private TekstiOsaDto tehtava;
    private Set<OppiaineDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;
    private String koodiUri;
    private String koodiArvo;
}
