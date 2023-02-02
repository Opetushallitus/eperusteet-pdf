package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineenTallennusDto {
    private OppiaineDto oppiaine;
    private Long vuosiluokkakokonaisuusId;
    private Set<Vuosiluokka> vuosiluokat;
    private List<TekstiosaDto> tavoitteet;
}
