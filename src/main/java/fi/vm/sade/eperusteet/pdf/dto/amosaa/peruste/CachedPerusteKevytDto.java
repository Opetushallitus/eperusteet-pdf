package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CachedPerusteKevytDto {
    private String diaarinumero;
    private Long perusteId;
    private LokalisoituTekstiDto nimi;
    private KoulutusTyyppi koulutustyyppi;
}
