package fi.vm.sade.eperusteet.pdf.dto.ylops.peruste;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerusteTekstiOsaDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto teksti;
}
