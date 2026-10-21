package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
}
