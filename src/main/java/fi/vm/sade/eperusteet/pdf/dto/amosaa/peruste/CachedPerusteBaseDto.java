package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CachedPerusteBaseDto {
    private Long id;
    private String diaarinumero;
    private Long perusteId;
    private LokalisoituTekstiDto nimi;
    private KoulutusTyyppi koulutustyyppi;
    private Set<KoulutusDto> koulutukset;
}
