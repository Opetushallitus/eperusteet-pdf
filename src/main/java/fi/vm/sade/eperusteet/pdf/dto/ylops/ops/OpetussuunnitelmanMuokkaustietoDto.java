package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.MuokkausTapahtuma;
import fi.vm.sade.eperusteet.pdf.dto.enums.NavigationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmanMuokkaustietoDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    private MuokkausTapahtuma tapahtuma;
    private Long opetussuunnitelmaId;
    private Long kohdeId;
    private NavigationType kohde;
    private Date luotu;
    private String muokkaaja;
    private String lisatieto;
    private boolean poistettu;
    private Set<OpetussuunnitelmanMuokkaustietoLisaparametritDto> lisaparametrit;
}
