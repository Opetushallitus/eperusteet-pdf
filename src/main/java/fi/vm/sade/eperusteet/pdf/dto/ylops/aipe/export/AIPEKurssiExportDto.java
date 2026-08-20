package fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AIPEKurssiExportDto {
    private Long id;
    private Long perusteenKurssiId;
    private LokalisoituTekstiDto paikallinenTarkennus;
    private PerusteAIPEKurssiSisaltoDto perusteSisalto;

    public LokalisoituTekstiDto getNimi() {
        return perusteSisalto != null ? perusteSisalto.getNimi() : null;
    }
}
