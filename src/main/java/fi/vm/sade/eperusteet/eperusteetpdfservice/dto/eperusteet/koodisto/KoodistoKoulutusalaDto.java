package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoKoulutusalaDto {
    private String koodiUri;
    private String versio;
    private KoodistoMetadataDto[] metadata;
}
