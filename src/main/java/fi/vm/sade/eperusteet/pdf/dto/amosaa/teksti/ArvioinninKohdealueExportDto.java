package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArvioinninKohdealueExportDto {
    private LokalisoituTekstiDto otsikko;
    private List<ArvioinninKohdeExportDto> arvioinninKohteet;
}
