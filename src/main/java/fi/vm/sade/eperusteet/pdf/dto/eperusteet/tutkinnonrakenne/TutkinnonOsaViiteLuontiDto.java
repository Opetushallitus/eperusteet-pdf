package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKevytDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutkinnonOsaViiteLuontiDto extends TutkinnonOsaViiteDto {
    private boolean kopioiMuokattavaksi;
    private PerusteKevytDto alkuperainenPeruste;
}
