package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.YstavaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KoulutustoimijaYstavaDto extends KoulutustoimijaBaseDto {
    YstavaStatus status;
}
