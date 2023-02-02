package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OppiaineenVuosiluokkaKokonaisuusDto implements ReferenceableDto {
    private Long id;
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("_vuosiluokkaKokonaisuus")
    private VuosiluokkakokonaisuusDto vuosiluokkaKokonaisuus;
    private TekstiOsaDto tehtava;
    private TekstiOsaDto tyotavat;
    private TekstiOsaDto ohjaus;
    private TekstiOsaDto arviointi;
    private TekstiOsaDto tavoitteistaJohdetutOppimisenTavoitteet;
    private TekstiOsaDto sisaltoalueinfo;
    private PerusteenLokalisoituTekstiDto opetuksenTavoitteetOtsikko;
    private PerusteenLokalisoituTekstiDto vapaaTeksti;
    private List<OpetuksenTavoiteDto> tavoitteet;
    private List<KeskeinenSisaltoalueDto> sisaltoalueet;
}
