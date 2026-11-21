package fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.TavoiteAlueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaiteenOpintokokonaisuusExportDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private Long laajuus;
    private LokalisoituTekstiDto kuvaus;
    private LokalisoituTekstiDto osaamisenArviointi;
    private List<TavoitealueValintaDto> tavoitealueValinnat = new ArrayList<>();
    private List<TavoiteAlueDto> tavoitealueet = new ArrayList<>();
}
