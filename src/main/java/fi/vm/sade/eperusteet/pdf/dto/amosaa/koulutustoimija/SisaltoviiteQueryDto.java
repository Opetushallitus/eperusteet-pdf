package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.OpsTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.QueryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SisaltoviiteQueryDto extends QueryDto {
    private Long opetussuunnitelmaId;
    private SisaltoTyyppi tyyppi;
    private OpsTyyppi opsTyyppi;
    private boolean sortDesc = false;
}
