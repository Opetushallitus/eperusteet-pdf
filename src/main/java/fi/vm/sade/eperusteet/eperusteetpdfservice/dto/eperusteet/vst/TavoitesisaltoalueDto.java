package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.vst;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
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
@JsonTypeName("tavoitesisaltoalue")
public class TavoitesisaltoalueDto extends PerusteenOsaDto.Laaja {

    private KoodiDto nimiKoodi;
    private LokalisoituTekstiDto teksti;
    private List<TavoiteAlueDto> tavoitealueet = new ArrayList<>();

    @Override
    public String getOsanTyyppi() {
        return "tavoitesisaltoalue";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.tavoitesisaltoalue;
    }
}
