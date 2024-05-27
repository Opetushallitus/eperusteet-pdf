package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuosiluokkaKokonaisuusDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private Set<Vuosiluokka> vuosiluokat;
    private LokalisoituTekstiDto nimi;
    private TekstiOsaDto siirtymaEdellisesta;
    private TekstiOsaDto tehtava;
    private TekstiOsaDto siirtymaSeuraavaan;
    private TekstiOsaDto laajaalainenOsaaminen;

    @JsonProperty("laajaalaisetOsaamiset")
    private Set<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> laajaalaisetOsaamiset;

    // ylopsin perusteen rakenteeseen laaja-alaiset osaamiset on typotettu
    @JsonProperty("laajaalaisetosaamiset")
    private Set<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> laajaalaisetosaamiset;
    private TekstiOsaDto paikallisestiPaatettavatAsiat;
    private List<KevytTekstiKappaleDto> vapaatTekstit;

    public Set<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> getLaajaalaisetOsaamiset() {
        if (laajaalaisetOsaamiset != null) {
            return laajaalaisetOsaamiset;
        }

        return laajaalaisetosaamiset;
    }

}
