package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja.KayttajanTietoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class RevisionKayttajaDto extends RevisionDto {
    private KayttajanTietoDto kayttajanTieto;
}
