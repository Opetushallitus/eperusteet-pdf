package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.yl;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AIPEOpetuksenSisaltoDto implements PerusteenSisaltoDto {
    private PerusteenOsaViiteDto.Laaja sisalto;
    private List<LaajaalainenOsaaminenDto> laajaalaisetosaamiset = new ArrayList<>();
    private List<AIPEVaiheDto> vaiheet = new ArrayList<>();
}
