package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaKevytDto extends OpetussuunnitelmaBaseDto {
    private OpetussuunnitelmaBaseDto pohja;
    private Set<OpsVuosiluokkakokonaisuusKevytDto> vuosiluokkakokonaisuudet;
    private Set<OpsOppiaineKevytDto> oppiaineet;
}
