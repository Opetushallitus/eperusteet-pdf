package fi.vm.sade.eperusteet.pdf.dto.eperusteet.perusteprojekti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.enums.ProjektiTila;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class PerusteprojektiInfoDto implements Serializable {
    private Long id;
    private String nimi;
    private ProjektiTila tila;
    private PerusteDto peruste;
    private String diaarinumero;
    private String ryhmaOid;
    private String koulutustyyppi;
}
