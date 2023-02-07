package fi.vm.sade.eperusteet.pdf.dto.common;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OsaamistasoDto {
    private Long id;
    private LokalisoituTekstiDto otsikko;
    private KoodiDto koodi;
}
