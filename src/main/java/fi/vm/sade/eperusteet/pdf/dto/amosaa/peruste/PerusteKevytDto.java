package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteKevytDto implements Serializable {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private String diaarinumero;
    private Date voimassaoloAlkaa;
    private KoulutusTyyppi koulutustyyppi;
}
