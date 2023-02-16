package fi.vm.sade.eperusteet.pdf.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Cache haluaa, että on Serializable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoMetadataDto implements Serializable {
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
