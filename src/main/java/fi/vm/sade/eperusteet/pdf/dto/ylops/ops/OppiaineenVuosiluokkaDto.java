package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Vuosiluokka;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineenVuosiluokkaDto implements ReferenceableDto {
    private Long id;
    private Vuosiluokka vuosiluokka;
    private List<KeskeinenSisaltoalueDto> sisaltoalueet;
    private List<OpetuksenTavoiteDto> tavoitteet;
    private LokalisoituTekstiDto vapaaTeksti;
}
