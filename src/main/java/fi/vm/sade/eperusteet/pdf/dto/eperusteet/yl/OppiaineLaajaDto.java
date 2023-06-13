package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OppiaineLaajaDto extends OppiaineBaseDto {
    private Optional<TekstiOsaDto> tehtava;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OppiaineDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;
    private String koodiUri;
    private String koodiArvo;

    public Optional<OppiaineenVuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuus(Long id) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> v.getVuosiluokkaKokonaisuus().get().getIdLong().equals(id))
                .findAny();
    }
}
