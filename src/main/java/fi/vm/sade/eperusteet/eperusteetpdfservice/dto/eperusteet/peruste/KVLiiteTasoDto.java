package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KVLiiteTasoDto {
    String codeUri;
    String codeValue;
    LokalisoituTekstiDto nimi;

    public int getJarjestys() {
        if (codeUri.startsWith("nqf")) {
            return 0;
        }
        else if (codeUri.startsWith("eqf")) {
            return 1;
        }
        else if (codeUri.startsWith("isced")) {
            return 2;
        }
        return 99;
    }
}
