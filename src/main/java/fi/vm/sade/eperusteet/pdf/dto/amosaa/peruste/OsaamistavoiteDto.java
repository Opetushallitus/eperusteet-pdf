package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.utils.domain.utils.Kieli;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OsaamistavoiteDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private boolean pakollinen;
    private BigDecimal laajuus;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Kieli kieli;
}
