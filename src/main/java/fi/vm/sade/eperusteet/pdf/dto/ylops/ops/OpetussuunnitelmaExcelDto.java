package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.utils.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaExcelDto {
    private String koodi;
    private LokalisoituTekstiDto nimi;
    private List<Pair<String, LokalisoituTekstiDto>> tutkinnonOsat;
}
