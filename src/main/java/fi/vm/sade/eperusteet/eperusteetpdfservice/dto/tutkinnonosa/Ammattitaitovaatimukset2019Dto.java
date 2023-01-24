package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.tutkinnonosa;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ammattitaitovaatimukset2019Dto {
    private Long id;
    private LokalisoituTekstiDto kohde;
    private List<Ammattitaitovaatimus2019Dto> vaatimukset = new ArrayList<>();
    private List<AmmattitaitovaatimustenKohdealue2019Dto> kohdealueet = new ArrayList<>();
}
