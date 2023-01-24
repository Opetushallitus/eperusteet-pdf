package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteValidointiDto;
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
