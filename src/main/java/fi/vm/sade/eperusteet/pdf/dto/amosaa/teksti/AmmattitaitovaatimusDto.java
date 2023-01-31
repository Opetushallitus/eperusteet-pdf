package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmmattitaitovaatimusDto {
    private Long id;
    private LokalisoituTekstiDto selite;
    private String ammattitaitovaatimusKoodi;
    private Integer jarjestys;
}
