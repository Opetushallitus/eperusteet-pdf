package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ammattitaitovaatimus2019Dto {
    private KoodiDto koodi;
    private LokalisoituTekstiDto vaatimus;

    public LokalisoituTekstiDto getVaatimus() {
        if (this.koodi != null) {
            return this.koodi.getNimi();
        }
        return vaatimus;
    }
}
