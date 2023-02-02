package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio.osaviitteet;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("opetuksenyleisettavoitteet")
public class OpetuksenYleisetTavoitteetLaajaDto extends PerusteenOsaDto.Laaja {
    private UUID uuidTunniste;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto kuvaus;

    public OpetuksenYleisetTavoitteetLaajaDto(LokalisoituTekstiDto nimi, PerusteTila tila, PerusteenOsaTunniste tunniste) {
        super(nimi, tila, tunniste);
    }

    @Override
    public String getOsanTyyppi() {
        return "opetuksenyleisettavoitteet";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.opetuksenyleisettavoitteet;
    }
}
