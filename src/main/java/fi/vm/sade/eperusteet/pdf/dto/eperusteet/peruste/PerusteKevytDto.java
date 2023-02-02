package fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.PerusteTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.PerusteTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerusteKevytDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private PerusteTila tila;
    private PerusteTyyppi tyyppi;
    private String koulutustyyppi;
    private boolean esikatseltavissa;
    private Date voimassaoloAlkaa;
    private Date voimassaoloLoppuu;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<SuoritustapaDto> suoritustavat;
}
