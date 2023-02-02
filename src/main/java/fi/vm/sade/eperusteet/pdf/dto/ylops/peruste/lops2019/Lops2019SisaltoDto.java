package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.laajaalainenosaaminen.Lops2019LaajaAlainenOsaaminenKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet.Lops2019OppiaineKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lops2019SisaltoDto {
    private Lops2019LaajaAlainenOsaaminenKokonaisuusDto laajaAlainenOsaaminen;
    private List<Lops2019OppiaineKaikkiDto> oppiaineet = new ArrayList<>();
    private TekstiKappaleViiteDto sisalto;
}

