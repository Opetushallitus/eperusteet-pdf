package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.VuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class DokumenttiYlops extends DokumenttiBase {
    OpetussuunnitelmaExportDto ops;

    public Optional<VuosiluokkaKokonaisuusDto> getPerusteVlk(UUID uuid) {
        return peruste.getPerusopetuksenPerusteenSisalto().getVuosiluokkakokonaisuudet(uuid);
    }
}
