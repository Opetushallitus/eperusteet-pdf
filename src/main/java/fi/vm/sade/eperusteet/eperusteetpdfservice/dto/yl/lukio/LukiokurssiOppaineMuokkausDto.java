package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.yl.lukio;

import com.sun.istack.NotNull;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.IdHolder;
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
    @NotNull
    private Long id;
    private List<KurssinOppiaineDto> oppiaineet = new ArrayList<>();

    public LukiokurssiOppaineMuokkausDto(Long id) {
        this.id = id;
    }
}
