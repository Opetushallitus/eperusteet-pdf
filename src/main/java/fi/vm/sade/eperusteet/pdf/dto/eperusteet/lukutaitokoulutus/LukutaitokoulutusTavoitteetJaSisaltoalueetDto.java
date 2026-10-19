package fi.vm.sade.eperusteet.pdf.dto.eperusteet.lukutaitokoulutus;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
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
@JsonTypeName("lukutaitokoulutus_tavoitteetjasisaltoalueet")
public class LukutaitokoulutusTavoitteetJaSisaltoalueetDto extends PerusteenOsaDto.Laaja {

    private List<LukutaitokoulutusTavoitealueDto> tavoitealueet = new ArrayList<>();

    @Override
    public String getOsanTyyppi() {
        return "lukutaitokoulutus_tavoitteetjasisaltoalueet";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.lukutaitokoulutus_tavoitteetjasisaltoalueet;
    }
}
