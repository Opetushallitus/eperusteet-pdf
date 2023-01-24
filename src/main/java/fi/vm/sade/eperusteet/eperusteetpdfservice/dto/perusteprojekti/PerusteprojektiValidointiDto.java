package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteValidointiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektiValidointiDto {
    private Long id;
    private PerusteValidointiDto peruste;
}
