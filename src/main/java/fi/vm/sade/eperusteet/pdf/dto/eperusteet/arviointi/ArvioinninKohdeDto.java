package fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
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
