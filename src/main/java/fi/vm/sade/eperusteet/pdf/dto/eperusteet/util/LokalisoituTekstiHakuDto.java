package fi.vm.sade.eperusteet.pdf.dto.eperusteet.util;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
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
