package fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.OsaAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsaAlueDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private LokalisoituTekstiDto kuvaus;

    private OsaAlueTyyppi tyyppi;
    private KoodiDto koodi;
    private String koodiUri;
    private String koodiArvo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private KoodiDto kielikoodi;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ValmaTelmaSisaltoDto valmaTelmaSisalto;
    private OsaamistavoiteDto pakollisetOsaamistavoitteet;
    private OsaamistavoiteDto valinnaisetOsaamistavoitteet;

    public String getKoodiUri() {
        KoodiDto koodi = this.getKoodi();
        if (koodi != null) {
            return koodi.getUri();
        } else {
            return koodiUri;
        }
    }

    public String getKoodiArvo() {
        KoodiDto koodi = this.getKoodi();
        if (koodi != null) {
            return koodi.getArvo();
        } else {
            return koodiArvo;
        }
    }
}
