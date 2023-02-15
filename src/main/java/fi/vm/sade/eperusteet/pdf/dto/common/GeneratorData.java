package fi.vm.sade.eperusteet.pdf.dto.common;

import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import lombok.Data;

@Data
public class GeneratorData {
    private Long id;
    private Long dokumenttiId;
    private Kieli kieli;
    private DokumenttiTyyppi tyyppi;
    private Long ktId;
}
