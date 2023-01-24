package fi.vm.sade.eperusteet.pdf.dto.eperusteet.util;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TaiteenalaViiteUpdateDto extends UpdateDto<TaiteenalaDto> {
}
