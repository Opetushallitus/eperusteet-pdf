package fi.vm.sade.eperusteet.pdf.dto.amosaa.osaamismerkki;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OsaamismerkkiKappaleDto {
    private Long id;
    private LokalisoituTekstiDto kuvaus;
    private List<OsaamismerkkiKoodiDto> osaamismerkkiKoodit;
}
