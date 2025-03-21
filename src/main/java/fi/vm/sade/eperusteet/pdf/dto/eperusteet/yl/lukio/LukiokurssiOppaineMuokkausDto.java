package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.IdHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LukiokurssiOppaineMuokkausDto implements Serializable, IdHolder {
    private Long id;
    private List<KurssinOppiaineDto> oppiaineet = new ArrayList<>();

    public LukiokurssiOppaineMuokkausDto(Long id) {
        this.id = id;
    }
}
