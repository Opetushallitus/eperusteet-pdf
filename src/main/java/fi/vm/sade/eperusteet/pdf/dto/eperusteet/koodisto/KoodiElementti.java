package fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.KoodistoMetadataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodiElementti {
    private String codeElementUri;
    private String codeElementValue;
    private Long codeElementVersion;
    private Boolean passive;
    private KoodistoMetadataDto[] parentMetadata;
    private KoodistoMetadataDto[] relationMetadata;
}
