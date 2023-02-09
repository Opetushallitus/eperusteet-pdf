package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"koodisto", "uri", "versio"})
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class KoodiDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private String arvo;
    private String uri;
    private String koodisto;
    private Long versio;

    static public KoodiDto of(String koodisto, String arvo) {
        KoodiDto result = new KoodiDto();
        result.setUri(koodisto + "_" + arvo);
        result.setKoodisto(koodisto);
        result.setArvo(arvo);
        return result;
    }

    @JsonIgnore
    public boolean isTemporary() {
        return uri != null && uri.startsWith("temporary_");
    }

}
