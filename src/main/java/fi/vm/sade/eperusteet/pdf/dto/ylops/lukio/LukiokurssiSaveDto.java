package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.perustedto.TekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukiokurssiSaveDto implements Serializable {
    private long oppiaineId;
    private LokalisoituTekstiDto nimi;
    private LukiokurssiTyyppi.Paikallinen tyyppi;
    private BigDecimal laajuus = BigDecimal.ONE;
    private LokalisoituTekstiDto kuvaus;
    private String koodiUri;
    private String koodiArvo;
    private LokalisoituTekstiDto lokalisoituKoodi;
    private TekstiOsaDto tavoitteet;
    private TekstiOsaDto keskeinenSisalto;
    private TekstiOsaDto tavoitteetJaKeskeinenSisalto;
}
