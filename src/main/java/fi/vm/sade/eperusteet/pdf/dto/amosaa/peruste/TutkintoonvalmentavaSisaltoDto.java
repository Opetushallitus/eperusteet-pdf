package fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutkintoonvalmentavaSisaltoDto {
    private Long id;
    private PerusteenOsaViiteDto.Laaja sisalto;
}
