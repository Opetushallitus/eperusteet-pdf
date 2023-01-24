package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteKoulutuskoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektiKoulutuskoodiDto {
    private Long id;
    private PerusteKoulutuskoodiDto peruste;
}
