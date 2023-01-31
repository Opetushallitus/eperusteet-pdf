package fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArvioinninKohdealueDto {
    private LokalisoituTekstiDto otsikko;
    private List<ArvioinninKohdeDto> arvioinninKohteet;
    private KoodiDto koodi;
}
