package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DokumenttiYlops extends DokumenttiBase {
    OpetussuunnitelmaExportDto ops;
    PerusteDto perusteDto;
}
