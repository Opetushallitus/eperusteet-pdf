package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LokalisoituTekstiHakuDto {
    private long id;
    private Kieli kieli;
    private String teksti;
}
