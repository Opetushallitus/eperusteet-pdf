package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tuva;

import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.KoulutusOsanKoulutustyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.KoulutusOsanTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.NavigationType;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("koulutuksenosa")
public class KoulutuksenOsaDto extends PerusteenOsaDto.Laaja {

    private KoodiDto nimiKoodi;
    private Integer laajuusMinimi;
    private Integer laajuusMaksimi;
    private KoulutusOsanKoulutustyyppi koulutusOsanKoulutustyyppi;
    private KoulutusOsanTyyppi koulutusOsanTyyppi;
    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto tavoitteenKuvaus;
    private List<LokalisoituTekstiDto> tavoitteet;
    private LokalisoituTekstiDto keskeinenSisalto;
    private LokalisoituTekstiDto laajaAlaisenOsaamisenKuvaus;
    private LokalisoituTekstiDto arvioinninKuvaus;

    @Override
    public String getOsanTyyppi() {
        return "koulutuksenosa";
    }

    @Override
    public NavigationType getNavigationType() {
        return NavigationType.koulutuksenosa;
    }
}
