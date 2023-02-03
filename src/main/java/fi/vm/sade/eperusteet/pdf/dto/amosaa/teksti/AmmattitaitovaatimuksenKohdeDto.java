package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmmattitaitovaatimuksenKohdeDto {
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto selite;
    private List<AmmattitaitovaatimusDto> vaatimukset = new ArrayList<>();
}
