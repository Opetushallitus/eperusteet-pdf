package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.perusteprojekti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.ProjektiTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteKevytDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class PerusteprojektiListausDto implements Serializable  {
    private Long id;
    private String nimi;
    private ProjektiTila tila;
    private PerusteKevytDto peruste;
    private String diaarinumero;
    private String ryhmaOid;
    private String koulutustyyppi;
}
