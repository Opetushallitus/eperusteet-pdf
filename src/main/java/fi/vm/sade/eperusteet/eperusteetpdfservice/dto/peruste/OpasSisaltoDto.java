package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpasSisaltoDto implements PerusteenSisaltoDto {
    private Long id;
    private PerusteenOsaViiteDto.Laaja sisalto;
    private List<OppaanKiinnitettyKoodiDto> oppaanKiinnitetytKoodit;
}
