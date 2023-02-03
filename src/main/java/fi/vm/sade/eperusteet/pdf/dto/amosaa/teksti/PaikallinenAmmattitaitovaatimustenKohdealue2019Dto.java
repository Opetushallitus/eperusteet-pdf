package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaikallinenAmmattitaitovaatimustenKohdealue2019Dto {
    private LokalisoituTekstiDto kuvaus;
    private List<PaikallinenAmmattitaitovaatimus2019Dto> vaatimukset = new ArrayList<>();
}
