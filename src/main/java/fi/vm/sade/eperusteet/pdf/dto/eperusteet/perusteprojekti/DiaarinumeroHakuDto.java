package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import fi.vm.sade.eperusteet.pdf.dto.enums.ProjektiTila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaarinumeroHakuDto implements Serializable {
    private Boolean loytyi;
    private ProjektiTila tila;
    private Long id;
    private String diaarinumero;
}
