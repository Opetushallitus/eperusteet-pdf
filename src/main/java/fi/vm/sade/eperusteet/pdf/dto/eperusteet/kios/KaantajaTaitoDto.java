package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
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
@JsonTypeName("kaantajataito")
public class KaantajaTaitoDto extends PerusteenOsaDto.Laaja {

    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto valiotsikko;
    private List<KaantajaTaitoKohdealueDto> kohdealueet = new ArrayList<>();

    @Override
    public String getOsanTyyppi() {
        return "kaantajataito";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.kaantajataito;
    }
}

