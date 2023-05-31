
package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.KooditettuDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.OmaOsaAlueTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmaOsaAlueKevytDto implements KooditettuDto {
    private Long id;
    private OmaOsaAlueTyyppi tyyppi;
    private LokalisoituTekstiDto nimi;
    private Long perusteenOsaAlueId;
    private String perusteenOsaAlueKoodi;
    private boolean piilotettu;

    @Override
    public void setKooditettu(LokalisoituTekstiDto kooditettu) {
        this.nimi = kooditettu;
    }

    public void setNimi(LokalisoituTekstiDto nimi) {
        if (nimi != null) {
            this.nimi = nimi;
        }
    }

    @JsonIgnore
    public String getKoodiArvo() {
        return perusteenOsaAlueKoodi != null ? perusteenOsaAlueKoodi.split("_")[1] : null;
    }
}
