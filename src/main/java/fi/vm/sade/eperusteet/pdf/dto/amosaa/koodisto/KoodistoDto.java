package fi.vm.sade.eperusteet.pdf.dto.amosaa.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoDto {
    private Long id;
    private String koodiUri;
    private String koodiArvo;
    private String koodistoUri;
    private LokalisoituTekstiDto nimi;

    public KoodistoDto() {
    }

    public KoodistoDto(Long id, String koodiUri, String koodiArvo) {
        this.id = id;
        this.koodiUri = koodiUri;
        this.koodiArvo = koodiArvo;
    }

    public static KoodistoDto of(String uri) {
        KoodistoDto result = new KoodistoDto();
        result.setKoodistoUri(uri);
        return result;
    }
}
