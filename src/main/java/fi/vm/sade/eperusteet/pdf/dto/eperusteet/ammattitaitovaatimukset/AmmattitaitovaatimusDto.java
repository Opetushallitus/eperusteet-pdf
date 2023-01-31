package fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AmmattitaitovaatimusDto {
    private Long id;
    private LokalisoituTekstiDto selite;
    private String ammattitaitovaatimusKoodi;
    private Integer jarjestys;
}
