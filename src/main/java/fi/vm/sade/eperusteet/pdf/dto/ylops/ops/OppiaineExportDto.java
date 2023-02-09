package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OppiaineExportDto extends OppiaineBaseDto {
    private TekstiosaDto tehtava;
    private Set<OppiaineExportDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkakokonaisuusDto> vuosiluokkakokonaisuudet;
    private String koodiUri;
    private String koodiArvo;
    private OppiaineExportDto pohjanOppiaine;

    public Optional<OppiaineenVuosiluokkakokonaisuusDto> getVuosiluokkakokonaisuus(String tunniste) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> v.getVuosiluokkakokonaisuus().getId().equals(tunniste))
                .findAny();
    }
}
