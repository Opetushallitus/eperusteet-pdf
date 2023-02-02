package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.osaviitteet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("rakenne")
@NoArgsConstructor
public class LukioOpetussuunnitelmaRakenneLaajaDto extends PerusteenOsaDto.Laaja {

    public LukioOpetussuunnitelmaRakenneLaajaDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    @Override
    public String getOsanTyyppi() {
        return "rakenne";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.lukiorakenne;
    }
}
