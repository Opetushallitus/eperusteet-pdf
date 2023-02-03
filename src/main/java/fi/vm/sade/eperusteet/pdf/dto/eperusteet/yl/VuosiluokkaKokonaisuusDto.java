package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuosiluokkaKokonaisuusDto implements ReferenceableDto {
    private Long id;
    private UUID tunniste;
    private Set<Vuosiluokka> vuosiluokat;
    private Optional<LokalisoituTekstiDto> nimi;
    private Optional<TekstiOsaDto> siirtymaEdellisesta;
    private Optional<TekstiOsaDto> tehtava;
    private Optional<TekstiOsaDto> siirtymaSeuraavaan;
    private Optional<TekstiOsaDto> laajaalainenOsaaminen;
    private Set<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> laajaalaisetOsaamiset;
    private Optional<TekstiOsaDto> paikallisestiPaatettavatAsiat;
}
