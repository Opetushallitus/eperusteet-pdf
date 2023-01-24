package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.vst;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
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
@JsonTypeName("koto_laajaalainenosaaminen")
public class KotoLaajaAlainenOsaaminenDto extends PerusteenOsaDto.Laaja {

    private LokalisoituTekstiDto yleiskuvaus;
    private List<KotoLaajaAlaisenOsaamisenAlueDto> osaamisAlueet = new ArrayList<>();

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.koto_laajaalainenosaaminen;
    }

    @Override
    public String getOsanTyyppi() {
        return "koto_laajaalainenosaaminen";
    }
}
