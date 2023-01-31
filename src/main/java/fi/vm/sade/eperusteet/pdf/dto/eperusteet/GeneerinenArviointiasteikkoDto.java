package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneerinenArviointiasteikkoDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kohde;
    private Reference arviointiAsteikko;
    private boolean julkaistu;
    private boolean valittavissa;
    private boolean oletusvalinta;
    private Set<KoulutusTyyppi> koulutustyypit;
    private Set<GeneerisenArvioinninOsaamistasonKriteeriDto> osaamistasonKriteerit = new HashSet<>();
}
