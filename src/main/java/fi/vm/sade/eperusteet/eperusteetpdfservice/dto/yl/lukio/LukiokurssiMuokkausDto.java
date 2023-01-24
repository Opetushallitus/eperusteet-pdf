package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.lukio;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.IdHolder;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.TekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LukiokurssiMuokkausDto implements Serializable, IdHolder {
    @NotNull
    private Long id;
    @NotNull
    private LukiokurssiTyyppi tyyppi;
    @NotNull
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
