package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PerusteInfoDto {
    private Long id;
    private PerusteVersionDto globalVersion;
    private LokalisoituTekstiDto nimi;
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private Date voimassaoloLoppuu;
    private Date muokattu;
    private String tila;
    private KoulutusTyyppi koulutustyyppi;
}
