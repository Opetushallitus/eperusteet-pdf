package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
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
public class OppiaineDto extends OppiaineBaseUpdateDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<OppiaineSuppeaDto> oppimaarat;
    private Set<OpetuksenKohdealueDto> kohdealueet;
    private Set<OppiaineenVuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;
    private KoodiDto koodi;
    private List<KevytTekstiKappaleDto> vapaatTekstit;

    public Optional<LokalisoituTekstiDto> getNimi() {
        if (super.getNimi().isPresent()) {
            return super.getNimi();
        }

        return Optional.ofNullable(koodi).map(KoodiDto::getNimi);
    }

    public Optional<OppiaineenVuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuus(String tunniste) {
        return getVuosiluokkakokonaisuus(UUID.fromString(tunniste));
    }

    public Optional<OppiaineenVuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuus(UUID tunniste) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(v -> v.getVuosiluokkaKokonaisuus().toString().equals(tunniste.toString()))
                .findAny();
    }
}
