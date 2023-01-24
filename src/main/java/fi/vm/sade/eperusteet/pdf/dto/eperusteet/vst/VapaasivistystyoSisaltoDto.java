package fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenSisaltoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VapaasivistystyoSisaltoDto implements PerusteenSisaltoDto {
    private Long id;
    private PerusteenOsaViiteDto.Laaja sisalto;
    private Integer laajuus;
}
