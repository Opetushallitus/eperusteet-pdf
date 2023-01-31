package fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmmattitaitovaatimusKohdeDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto selite;
    private List<AmmattitaitovaatimusDto> vaatimukset;
}
