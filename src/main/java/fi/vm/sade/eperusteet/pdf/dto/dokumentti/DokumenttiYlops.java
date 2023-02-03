package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DokumenttiYlops extends DokumenttiBase {
    OpetussuunnitelmaDto ops;
    PerusteDto perusteDto;
}
