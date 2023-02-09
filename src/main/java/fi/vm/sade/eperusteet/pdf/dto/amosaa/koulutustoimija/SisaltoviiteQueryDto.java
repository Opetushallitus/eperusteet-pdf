package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.QueryDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.OpsTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
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
