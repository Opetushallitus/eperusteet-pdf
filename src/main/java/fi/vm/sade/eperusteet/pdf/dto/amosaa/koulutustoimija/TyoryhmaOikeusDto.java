package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.dto.enums.HenkiloOikeus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TyoryhmaOikeusDto {
    Long id;
    String henkiloOid;
    HenkiloOikeus oikeus;
}
