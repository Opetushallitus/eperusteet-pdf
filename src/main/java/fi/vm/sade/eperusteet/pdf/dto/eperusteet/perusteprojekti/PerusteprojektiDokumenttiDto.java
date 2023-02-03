package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.ProjektiTila;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteDokumenttiDto;
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
