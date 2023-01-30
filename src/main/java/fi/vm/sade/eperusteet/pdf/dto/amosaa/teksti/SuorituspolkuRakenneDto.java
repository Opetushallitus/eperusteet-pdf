package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.SuorituspolkuRiviJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.RakenneModuuliDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SuorituspolkuRakenneDto extends RakenneModuuliDto {
    private SuorituspolkuRiviJulkinenDto paikallinenKuvaus;
    private Long sisaltoviiteId;
}
