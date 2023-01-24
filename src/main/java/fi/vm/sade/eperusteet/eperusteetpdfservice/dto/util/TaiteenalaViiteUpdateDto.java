package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.TaiteenalaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TaiteenalaViiteUpdateDto extends UpdateDto<TaiteenalaDto> {
}
