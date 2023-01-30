package fi.vm.sade.eperusteet.pdf.dto.amosaa.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KoodistoKoodiDto {
    private String koodiUri;
    private String koodiArvo;
    private Date voimassaAlkuPvm;
    private Date voimassaLoppuPvm;
    private KoodistoDto koodisto;
    private KoodistoMetadataDto[] metadata;
}
