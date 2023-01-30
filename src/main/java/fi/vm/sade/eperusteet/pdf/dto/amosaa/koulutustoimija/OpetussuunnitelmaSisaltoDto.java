package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;
import lombok.Data;

@Data
public class OpetussuunnitelmaSisaltoDto {
    // TODO: Tämä on kriittinen tehokkuuden kannalta. Tee nopeammaksi
    SisaltoViiteDto sisalto;
}
