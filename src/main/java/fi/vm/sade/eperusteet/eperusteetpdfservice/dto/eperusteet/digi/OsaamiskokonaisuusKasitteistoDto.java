package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.digi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.DigitaalinenOsaaminenTaso;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaamiskokonaisuusKasitteistoDto {
    private Long id;
    private DigitaalinenOsaaminenTaso taso;
    private LokalisoituTekstiDto kuvaus;
}
