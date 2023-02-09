package fi.vm.sade.eperusteet.pdf.dto.eperusteet.digi;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("osaamiskokonaisuus_paa_alue")
public class OsaamiskokonaisuusPaaAlueDto extends PerusteenOsaDto.Laaja{
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;
    private List<OsaamiskokonaisuusOsaAlueDto> osaAlueet;

    @Override
    public String getOsanTyyppi() {
        return "osaamiskokonaisuus_paa_alue";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.osaamiskokonaisuus_paa_alue;
    }
}
