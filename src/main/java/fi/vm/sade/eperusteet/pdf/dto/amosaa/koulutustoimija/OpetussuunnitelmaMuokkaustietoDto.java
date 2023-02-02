package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.MuokkausTapahtuma;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja.KayttajanTietoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaMuokkaustietoDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    private MuokkausTapahtuma tapahtuma;
    private Long perusteId;
    private Long kohdeId;
    private NavigationType kohde;
    private Date luotu;
    private String muokkaaja;
    private String lisatieto;
    private boolean poistettu;

    private KayttajanTietoDto kayttajanTieto;
}
