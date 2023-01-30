package fi.vm.sade.eperusteet.pdf.dto.amosaa.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KoodistoMetadataDto {
    private String nimi;
    private String kieli;
    private String kuvaus;

    public static KoodistoMetadataDto of(String nimi, String kieli, String kuvaus) {
        KoodistoMetadataDto koodistoMetadataDto = new KoodistoMetadataDto();
        koodistoMetadataDto.setNimi(nimi);
        koodistoMetadataDto.setKieli(kieli);
        koodistoMetadataDto.setKuvaus(kuvaus);
        return koodistoMetadataDto;
    }
}
