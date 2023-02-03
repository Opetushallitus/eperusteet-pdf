package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import com.fasterxml.jackson.annotation.JsonProperty;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.OppiaineTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.OppiaineValinnainenTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Tila;
import fi.vm.sade.eperusteet.pdf.dto.ylops.Reference;
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
    private Long jnro;
    private String laajuus;
    private boolean koosteinen;
    private LokalisoituTekstiDto nimi;
    private Boolean abstrakti;

    public OppiaineValinnainenTyyppi getValinnainenTyyppi() {
        return valinnainenTyyppi != null ? valinnainenTyyppi : OppiaineValinnainenTyyppi.EI_MAARITETTY;
    }
}
