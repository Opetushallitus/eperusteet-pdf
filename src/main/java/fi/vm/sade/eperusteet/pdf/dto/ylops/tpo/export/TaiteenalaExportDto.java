package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

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
public class TaiteenalaExportDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto paikallinenTarkennus;
    private PerusteTpoTaiteenalaSisaltoDto perusteenTaiteenala;
    private List<TaiteenalanVaiheExportDto> vaiheet = new ArrayList<>();
}
