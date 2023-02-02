package fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KoodistoDto {
    private Long id;
    private String koodiUri;
    private String koodiArvo;
    private LokalisoituTekstiDto nimi;

    public KoodistoDto(Long id, String koodiUri, String koodiArvo) {
        this.id = id;
        this.koodiUri = koodiUri;
        this.koodiArvo = koodiArvo;
    }
}
