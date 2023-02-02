package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VuosiluokkakokonaisuudenLaajaalainenOsaaminenDto implements ReferenceableDto {
    private Long id;
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("_laajaalainenOsaaminen")
    private LaajaalainenOsaaminenDto laajaalainenOsaaminen;
    private PerusteenLokalisoituTekstiDto kuvaus;
}
