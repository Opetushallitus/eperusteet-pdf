package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.TilaUpdateStatus;
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
