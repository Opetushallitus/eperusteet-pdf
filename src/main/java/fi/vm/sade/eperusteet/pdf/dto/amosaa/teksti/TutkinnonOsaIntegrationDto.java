package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.dto.enums.TutkinnonOsaTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutkinnonOsaIntegrationDto {
    private Long id;
    private TutkinnonOsaTyyppi tyyppi;
    private OmaTutkinnonosaIntegrationDto omatutkinnonosa;
}
