package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.JulkaisuTila;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.kayttaja.KayttajanTietoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmanJulkaisuDto {
    private Long id;
    private OpetussuunnitelmaInfoDto opetussuunnitelma;
    private LokalisoituTekstiDto tiedote;
    private Set<Long> dokumentit = new HashSet<>();
    private int revision;
    private Date luotu;
    private String luoja;
    private KayttajanTietoDto kayttajanTieto;
    private JulkaisuTila tila = JulkaisuTila.JULKAISTU;
}
