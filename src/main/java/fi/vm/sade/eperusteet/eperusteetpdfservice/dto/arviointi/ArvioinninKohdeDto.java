package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.arviointi;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
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
    private Reference arviointiAsteikko;
    private Set<OsaamistasonKriteeriDto> osaamistasonKriteerit;
}
