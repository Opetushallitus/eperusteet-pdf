package fi.vm.sade.eperusteet.pdf.dto.amosaa.ops;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.RakenneOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SuorituspolkuOsaDto extends RakenneOsaDto {
    SuorituspolkuRiviJulkinenDto paikallinenKuvaus;
}
