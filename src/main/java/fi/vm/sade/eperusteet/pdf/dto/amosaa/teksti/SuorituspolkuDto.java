package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.SuorituspolkuRiviDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SuorituspolkuDto extends SuorituspolkuBaseDto {
    private Boolean naytaKuvausJulkisesti;
    private Boolean piilotaPerusteenTeksti;
    private Set<SuorituspolkuRiviDto> rivit = new HashSet<>();
    private BigDecimal osasuorituspolkuLaajuus;
}
