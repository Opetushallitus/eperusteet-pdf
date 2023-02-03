package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukioOppiaineSaveDto implements Serializable {
    private Long oppiaineId;
    private LokalisoituTekstiDto nimi;
    private String laajuus;
    private boolean koosteinen;
    private String koodiUri;
    private String koodiArvo;
    private TekstiosaDto tehtava;
    private TekstiosaDto tavoitteet;
    private TekstiosaDto arviointi;
    private Map<LukiokurssiTyyppi, LokalisoituTekstiDto> kurssiTyyppiKuvaukset = new HashMap<>();
}
