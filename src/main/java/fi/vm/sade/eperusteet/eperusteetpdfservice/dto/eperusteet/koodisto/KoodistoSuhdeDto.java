package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoSuhdeDto {
    private String codesUri;
    private Boolean passive;
}
