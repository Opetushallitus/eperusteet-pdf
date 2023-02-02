package fi.vm.sade.eperusteet.pdf.dto.ylops.ukk;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KysymysDto implements Serializable {
    private Long id;
    private LokalisoituTekstiDto kysymys;
    private LokalisoituTekstiDto vastaus;
    private Set<OrganisaatioDto> organisaatiot;
    private Date luotu;
}
