package fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019OppiaineBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019.Lops2019SortableOppiaineDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Lops2019OppiaineKaikkiDto extends Lops2019OppiaineBaseDto implements Lops2019SortableOppiaineDto {
    private LokalisoituTekstiDto pakollisetModuulitKuvaus;
    private LokalisoituTekstiDto valinnaisetModuulitKuvaus;
    private List<Lops2019ModuuliDto> moduulit = new ArrayList<>();
    private List<Lops2019OppiaineKaikkiDto> oppimaarat = new ArrayList<>();

}
