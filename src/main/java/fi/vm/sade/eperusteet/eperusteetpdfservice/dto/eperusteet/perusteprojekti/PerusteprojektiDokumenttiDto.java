package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.ProjektiTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteDokumenttiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteprojektiDokumenttiDto {
    private PerusteDokumenttiDto peruste;
    private ProjektiTila tila;
}
