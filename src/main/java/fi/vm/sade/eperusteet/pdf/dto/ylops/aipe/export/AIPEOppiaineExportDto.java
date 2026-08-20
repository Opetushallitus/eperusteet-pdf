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
public class AIPEOppiaineExportDto {
    private Long id;
    private Long perusteenOppiaineId;
    private LokalisoituTekstiDto paikallinenTarkennus;
    private PerusteAIPEOppiaineSisaltoDto perusteSisalto;
    private List<AIPEOppiaineExportDto> oppimaarat = new ArrayList<>();
    private List<AIPEKurssiExportDto> kurssit = new ArrayList<>();

    public LokalisoituTekstiDto getNimi() {
        return perusteSisalto != null ? perusteSisalto.getNimi() : null;
    }
}
