package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.OsaamistasoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OsaamistasonKriteeriDto {
    @JsonProperty("_osaamistaso")
    private Reference osaamistasoRef;
    private OsaamistasoDto osaamistaso;
    private List<LokalisoituTekstiDto> kriteerit;
}
