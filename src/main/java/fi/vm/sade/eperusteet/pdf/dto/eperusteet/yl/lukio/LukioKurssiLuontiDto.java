package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.LukiokurssiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TekstiOsaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LukioKurssiLuontiDto implements Serializable {
    @NotNull
    private LukiokurssiTyyppi tyyppi;
    private List<KurssinOppiaineDto> oppiaineet;
    @NotNull
    private LokalisoituTekstiDto nimi;
    private String koodiArvo;
    private String koodiUri;
    private LokalisoituTekstiDto lokalisoituKoodi;
    private Optional<LokalisoituTekstiDto> kuvaus;
    private Optional<TekstiOsaDto> tavoitteet;
    private Optional<TekstiOsaDto> keskeinenSisalto;
    private Optional<TekstiOsaDto> tavoitteetJaKeskeinenSisalto;
    private Optional<TekstiOsaDto> arviointi;
    private String kommentti;
}
