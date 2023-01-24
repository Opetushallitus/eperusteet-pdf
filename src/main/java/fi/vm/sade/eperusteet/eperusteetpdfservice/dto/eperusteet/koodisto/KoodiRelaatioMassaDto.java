package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.koodisto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KoodiRelaatioMassaDto {
    private String codeElementUri;
    private String relationType;
    private Boolean isChild;
    private List<String> relations;
    private Boolean child;
}
