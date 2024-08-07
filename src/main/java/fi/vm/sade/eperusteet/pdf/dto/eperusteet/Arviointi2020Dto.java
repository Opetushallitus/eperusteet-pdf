package fi.vm.sade.eperusteet.pdf.dto.eperusteet;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Yhdistää arviointiasteikon ja geneerisen arvioinnin jaetuksi rakenteeksi.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Arviointi2020Dto {
    private Long id;
    private LokalisoituTekstiDto kohde;
    private Reference arviointiAsteikko;
    private List<OsaamistasonKriteerit2020Dto> osaamistasonKriteerit = new ArrayList<>();
}
