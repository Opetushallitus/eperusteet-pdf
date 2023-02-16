package fi.vm.sade.eperusteet.pdf.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto.KoodistoVersioDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Cache haluaa, että on Serializable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoDto implements Serializable {
    private Long id;
    private String koodistoUri;
    private String koodiUri;
    private String koodiArvo;
    private KoodistoVersioDto latestKoodistoVersio;
    private LokalisoituTekstiDto nimi;

    public static KoodistoDto of(String uri) {
        KoodistoDto result = new KoodistoDto();
        result.setKoodistoUri(uri);
        return result;
    }
}
