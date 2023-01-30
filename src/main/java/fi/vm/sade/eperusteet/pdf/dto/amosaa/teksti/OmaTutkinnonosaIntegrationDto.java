package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OmaTutkinnonosaIntegrationDto {
    private Long id;
    private String koodi;
    private String koodiPrefix;
}
