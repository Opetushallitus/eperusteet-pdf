package fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoMetadataDto {
    private String nimi;
    private String kieli;
}
