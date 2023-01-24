package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.kayttaja;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayttajaProfiiliDto {
    private long id;
    private String oid;
    private List<SuosikkiDto> suosikit;
    private List<KayttajaprofiiliPreferenssiDto> preferenssit;
}
