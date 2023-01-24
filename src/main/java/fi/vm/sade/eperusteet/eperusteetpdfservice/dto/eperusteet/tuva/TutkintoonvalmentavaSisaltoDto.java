package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tuva;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutkintoonvalmentavaSisaltoDto implements PerusteenSisaltoDto {
    private Long id;
    private PerusteenOsaViiteDto.Laaja sisalto;
}
