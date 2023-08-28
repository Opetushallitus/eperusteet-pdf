package fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019OppiaineBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019SortableOppiaineDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019OppiaineKaikkiDto extends Lops2019OppiaineBaseDto implements Lops2019SortableOppiaineDto, Comparable<Lops2019OppiaineKaikkiDto> {
    private LokalisoituTekstiDto pakollisetModuulitKuvaus;
    private LokalisoituTekstiDto valinnaisetModuulitKuvaus;
    private List<Lops2019ModuuliDto> moduulit = new ArrayList<>();
    private List<Lops2019OppiaineKaikkiDto> oppimaarat = new ArrayList<>();

    @Override
    public int compareTo(Lops2019OppiaineKaikkiDto o) {
        return Optional.ofNullable(getKoodi()).map(KoodiDto::getArvo).orElse("")
                .compareTo(Optional.ofNullable(o.getKoodi()).map(KoodiDto::getArvo).orElse(""));
    }
}
