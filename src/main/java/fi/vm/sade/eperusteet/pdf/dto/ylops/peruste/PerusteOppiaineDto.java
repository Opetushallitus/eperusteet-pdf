package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteOppiaineDto implements ReferenceableDto {

    private Long id;
    private UUID tunniste;
    private String koodiUri;
    private String koodiArvo;
    private Boolean koosteinen;
    private Boolean abstrakti;
    private LokalisoituTekstiDto nimi;
    private PerusteTekstiOsaDto tehtava;
    private Set<PerusteOppiaineDto> oppimaarat;
    private Set<PerusteOpetuksenkohdealueDto> kohdealueet;
    private List<PerusteOppiaineenVuosiluokkakokonaisuusDto> vuosiluokkakokonaisuudet;

    public Optional<PerusteOppiaineenVuosiluokkakokonaisuusDto> getVuosiluokkakokonaisuus(Reference tunniste) {
        return getVuosiluokkakokonaisuus(UUID.fromString(tunniste.toString()));
    }

    public Optional<PerusteOppiaineenVuosiluokkakokonaisuusDto> getVuosiluokkakokonaisuus(UUID tunniste) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> v.getVuosiluokkaKokonaisuus().getTunniste().equals(tunniste))
                .findAny();
    }

    public Optional<PerusteOppiaineenVuosiluokkakokonaisuusDto> getVuosiluokkakokonaisuus(String tunniste) {
        return getVuosiluokkakokonaisuus(UUID.fromString(tunniste));
    }
}
