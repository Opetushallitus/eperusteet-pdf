package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.pdf.dto.enums.KiinnitettyKoodiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OppaanKiinnitettyKoodiDto {
    private Long id;
    private KiinnitettyKoodiTyyppi kiinnitettyKoodiTyyppi;
    private KoodiDto koodi;
}
