package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeneerinenArviointiasteikkoKaikkiDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kohde;
    private ArviointiAsteikkoDto arviointiAsteikko;
    private boolean julkaistu;
    private boolean valittavissa;
    private Set<KoulutusTyyppi> koulutustyypit;
    private Set<GeneerisenArvioinninOsaamistasonKriteeriKaikkiDto> osaamistasonKriteerit = new HashSet<>();

    private Long _arviointiAsteikko;

    @JsonProperty("_arviointiAsteikko")
    public Reference arviointiasteikkoRef() {
        return Reference.of(_arviointiAsteikko);
    }
}
