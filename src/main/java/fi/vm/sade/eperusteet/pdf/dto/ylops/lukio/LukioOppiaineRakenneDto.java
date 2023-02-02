package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LukioOppiaineRakenneDto<Tyyppi, KurssiTyyppi extends LukiokurssiListausOpsDto>
        extends LukioOppimaaraPerusTiedotDto
        implements Serializable {
    protected List<Tyyppi> oppimaarat = new ArrayList<>();
    private LokalisoituTekstiDto kieli;
    protected List<KurssiTyyppi> kurssit = new ArrayList<>();
}
