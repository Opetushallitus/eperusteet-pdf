package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.utils.Nulls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusopetuksenPerusteenSisaltoDto implements PerusteenSisaltoDto {
    private PerusteenOsaViiteDto.Laaja sisalto;
    private Set<LaajaalainenOsaaminenDto> laajaalaisetosaamiset;
    private List<OppiaineLaajaDto> oppiaineet;
    private Set<VuosiluokkaKokonaisuusDto> vuosiluokkakokonaisuudet;

    public Optional<OppiaineLaajaDto> getOppiaine(UUID tunniste) {
        return oppiaineet.stream()
                .filter(oa -> Objects.equals(oa.getTunniste(), tunniste))
                .findAny();
    }

    public Optional<OppiaineDto> getOppimaara(UUID tunniste) {
        return oppiaineet.stream()
                .map(oa -> Nulls.nullToEmpty(oa.getOppimaarat()))
                .flatMap(Collection::stream)
                .filter(oa -> Objects.equals(oa.getTunniste(), tunniste))
                .findAny();
    }

    public Optional<VuosiluokkaKokonaisuusDto> getVuosiluokkakokonaisuudet(UUID tunniste) {
        return vuosiluokkakokonaisuudet.stream()
                .filter(vlk -> Objects.equals(vlk.getTunniste(), tunniste))
                .findAny();
    }

}
