package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("kaantajatodistusmalli")
public class KaantajaTodistusmalliDto extends PerusteenOsaDto.Laaja {

    private LokalisoituTekstiDto kuvaus;
    private KaantajaTodistusmalliTaitotasokuvausDto ylintaso;
    private KaantajaTodistusmalliTaitotasokuvausDto keskitaso;
    private KaantajaTodistusmalliTaitotasokuvausDto perustaso;
    private Boolean liite;

    @Override
    public String getOsanTyyppi() {
        return "kaantajatodistusmalli";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.kaantajatodistusmalli;
    }

}

