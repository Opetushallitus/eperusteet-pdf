package fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AIPEVaiheExportDto {
    private Long id;
    private Long perusteenVaiheId;
    private LokalisoituTekstiDto paikallinenTarkennus;
    private List<AIPEOppiaineExportDto> oppiaineet = new ArrayList<>();
    private PerusteAIPEVaiheSisaltoDto perusteSisalto;

    public LokalisoituTekstiDto getNimi() {
        return perusteSisalto != null ? perusteSisalto.getNimi() : null;
    }
}
