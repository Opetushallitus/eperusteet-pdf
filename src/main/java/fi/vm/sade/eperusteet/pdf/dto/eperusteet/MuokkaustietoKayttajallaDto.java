package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.kayttaja.KayttajanTietoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MuokkaustietoKayttajallaDto extends PerusteenMuokkaustietoDto {
    private KayttajanTietoDto kayttajanTieto;
}
