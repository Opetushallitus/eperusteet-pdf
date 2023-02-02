package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.Tyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaQuery implements Serializable {
    private String koulutustyyppi;
    private Tyyppi tyyppi;
    private String organisaatio;
    private Long perusteenId;
    private String perusteenDiaarinumero;
}
