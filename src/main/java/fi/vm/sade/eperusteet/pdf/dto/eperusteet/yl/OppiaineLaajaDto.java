package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineLaajaDto extends OppiaineBaseDto {
    private Optional<TekstiOsaDto> tehtava;
    private List<KevytTekstiKappaleDto> vapaatTekstit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OppiaineDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;
    private String koodiUri;
    private String koodiArvo;

    public Optional<OppiaineenVuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuus(Long id) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> Optional.ofNullable(v.getVuosiluokkaKokonaisuus()).orElse(Reference.of(0L)).getIdLong().equals(id))
                .findAny();
    }

    public Optional<OppiaineenVuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuus(UUID tunniste) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> v.getVuosiluokkaKokonaisuus().toString().equals(tunniste.toString()))
                .findAny();
    }
}
