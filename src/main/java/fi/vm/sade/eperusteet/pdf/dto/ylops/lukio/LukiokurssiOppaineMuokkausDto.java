package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import fi.vm.sade.eperusteet.pdf.dto.ylops.IdHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LukiokurssiOppaineMuokkausDto implements Serializable, IdHolder {
    private Long id;
    private List<KurssinOppiaineDto> oppiaineet = new ArrayList<>();

    public LukiokurssiOppaineMuokkausDto(Long id) {
        this.id = id;
    }
}
