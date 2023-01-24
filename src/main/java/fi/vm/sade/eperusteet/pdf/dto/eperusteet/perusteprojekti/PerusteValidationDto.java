package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.TilaUpdateStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteValidationDto  {
    TilaUpdateStatus validation;
    PerusteprojektiInfoDto perusteprojekti;
}
