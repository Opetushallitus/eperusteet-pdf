package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ammattitaitovaatimukset2019Dto {
    private Long id;
    private LokalisoituTekstiDto kohde;
    private List<Ammattitaitovaatimus2019Dto> vaatimukset = new ArrayList<>();
    private List<AmmattitaitovaatimustenKohdealue2019Dto> kohdealueet = new ArrayList<>();
}
