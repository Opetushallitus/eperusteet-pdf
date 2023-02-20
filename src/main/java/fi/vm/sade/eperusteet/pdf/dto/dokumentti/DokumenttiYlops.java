package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DokumenttiYlops extends DokumenttiBase {
    OpetussuunnitelmaExportDto ops;
    PerusteKaikkiDto perusteDto;
}
