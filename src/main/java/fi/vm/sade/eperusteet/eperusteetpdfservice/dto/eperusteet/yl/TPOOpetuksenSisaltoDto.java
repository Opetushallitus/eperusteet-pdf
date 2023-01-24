package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TPOOpetuksenSisaltoDto implements PerusteenSisaltoDto {
    private PerusteenOsaViiteDto.Laaja sisalto;
}
