package fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArvioinninKohdeDto {
    private LokalisoituTekstiDto otsikko;
    private LokalisoituTekstiDto selite;
    @JsonProperty("_arviointiAsteikko")
    private Reference arviointiAsteikko;

    @JsonProperty("arviointiAsteikko")
    private ArviointiAsteikkoDto arviointiAsteikkoDto;
    private Set<OsaamistasonKriteeriDto> osaamistasonKriteerit;
}
