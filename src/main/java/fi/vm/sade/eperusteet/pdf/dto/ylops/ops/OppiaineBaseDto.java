package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.enums.OppiaineTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.OppiaineValinnainenTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Tila;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class OppiaineBaseDto {
    private Long id;
    private UUID tunniste;
    private Tila tila;
    private OppiaineTyyppi tyyppi;
    private OppiaineValinnainenTyyppi valinnainenTyyppi = OppiaineValinnainenTyyppi.EI_MAARITETTY;
    @JsonProperty("_liittyvaOppiaine")
    private Reference liittyvaOppiaine;
    private String laajuus;
    private boolean koosteinen;
    private LokalisoituTekstiDto nimi;
    private Boolean abstrakti;

    public OppiaineValinnainenTyyppi getValinnainenTyyppi() {
        return valinnainenTyyppi != null ? valinnainenTyyppi : OppiaineValinnainenTyyppi.EI_MAARITETTY;
    }
}
