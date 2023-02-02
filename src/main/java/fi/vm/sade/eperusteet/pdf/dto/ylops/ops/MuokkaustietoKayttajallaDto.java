package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.ylops.kayttaja.KayttajanTietoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class MuokkaustietoKayttajallaDto extends OpetussuunnitelmanMuokkaustietoDto {
    private KayttajanTietoDto kayttajanTieto;
}
