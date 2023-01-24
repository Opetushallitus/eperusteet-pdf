package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.arviointi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArviointiDto {
    private Long id;
    private LokalisoituTekstiDto lisatiedot;
    private List<ArvioinninKohdealueDto> arvioinninKohdealueet;
}
