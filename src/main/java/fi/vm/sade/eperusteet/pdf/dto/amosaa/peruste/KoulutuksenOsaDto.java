package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusOsanKoulutustyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusOsanTyyppi;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoulutuksenOsaDto extends PerusteenOsaDto.Laaja {
    private KoodiDto nimiKoodi;
    private Integer laajuusMinimi;
    private Integer laajuusMaksimi;
    private KoulutusOsanKoulutustyyppi koulutusOsanKoulutustyyppi;
    private KoulutusOsanTyyppi koulutusOsanTyyppi;
    private LokalisoituTekstiDto kuvaus;
    private List<LokalisoituTekstiDto> tavoitteet;
    private LokalisoituTekstiDto keskeinenSisalto;
    private LokalisoituTekstiDto laajaAlaisenOsaamisenKuvaus;
    private LokalisoituTekstiDto arvioinninKuvaus;

    public String getOsanTyyppi() {
        return "koulutuksenosa";
    }

    public String getNimiKoodi() {
        if (nimiKoodi != null) {
            return nimiKoodi.getUri();
        }

        return null;
    }

}
