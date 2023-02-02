package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.domain.common.enums.NavigationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmanMuokkaustietoLisaparametritDto {
    private NavigationType kohde;
    private Long kohdeId;
}
