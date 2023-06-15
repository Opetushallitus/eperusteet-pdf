package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.ReferenceableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OppiaineenVuosiluokkakokonaisuusSuppeaDto implements ReferenceableDto {
    private Long id;
    private Reference vuosiluokkakokonaisuus;
    private Integer jnro;
    private Set<OppiaineenVuosiluokkaKevytDto> vuosiluokat;
    private Boolean piilotettu;
}
