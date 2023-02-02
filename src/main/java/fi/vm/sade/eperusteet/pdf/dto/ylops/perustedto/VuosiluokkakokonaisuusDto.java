package fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class VuosiluokkakokonaisuusDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private Set<Vuosiluokka> vuosiluokat;
    private PerusteenLokalisoituTekstiDto nimi;
    private TekstiOsaDto siirtymaEdellisesta;
    private TekstiOsaDto tehtava;
    private TekstiOsaDto siirtymaSeuraavaan;
    private TekstiOsaDto laajaalainenOsaaminen;
    private Set<VuosiluokkakokonaisuudenLaajaalainenOsaaminenDto> laajaalaisetOsaamiset;
    private TekstiOsaDto paikallisestiPaatettavatAsiat;
}
