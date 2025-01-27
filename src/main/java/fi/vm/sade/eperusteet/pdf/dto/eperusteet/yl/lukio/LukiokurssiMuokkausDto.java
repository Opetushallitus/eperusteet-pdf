package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.IdHolder;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LukiokurssiMuokkausDto implements Serializable, IdHolder {
    private Long id;
    private LukiokurssiTyyppi tyyppi;
    private LokalisoituTekstiDto nimi;
    private String koodiArvo;
    private String koodiUri;
    private LokalisoituTekstiDto lokalisoituKoodi;
    private LokalisoituTekstiDto kuvaus;
    private TekstiOsaDto tavoitteet;
    private TekstiOsaDto keskeinenSisalto;
    private TekstiOsaDto tavoitteetJaKeskeinenSisalto;
    private TekstiOsaDto arviointi;
    private String kommentti;
}
