package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteenOsaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PerusteenOsaUpdateDto extends UpdateDto<PerusteenOsaDto.Laaja> {
}
