package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
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
@JsonTypeName("kaantajaaihealue")
public class KaantajaAihealueDto extends PerusteenOsaDto.Laaja {

    private LokalisoituTekstiDto kuvaus;
    private List<KaantajaAihealueKategoriaDto> kategoriat = new ArrayList<>();
    private Boolean liite;

    @Override
    public String getOsanTyyppi() {
        return "kaantajaaihealue";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.kaantajaaihealue;
    }

}

