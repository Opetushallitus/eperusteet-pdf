package fi.vm.sade.eperusteet.pdf.dto.ylops;

import fi.vm.sade.eperusteet.pdf.dto.ylops.kayttaja.KayttajanTietoDto;
import lombok.Data;

@Data
public class RevisionKayttajaDto extends RevisionDto {
    private KayttajanTietoDto kayttajanTieto;
}
