package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.JulkaisuTila;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.kayttaja.KayttajanTietoDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JulkaisuBaseDto {
    private int revision;
    private PerusteBaseDto peruste;
    private LokalisoituTekstiDto tiedote;
    private Date luotu;
    private String luoja;
    private KayttajanTietoDto kayttajanTieto;
    private JulkaisuTila tila = JulkaisuTila.JULKAISTU;
}
