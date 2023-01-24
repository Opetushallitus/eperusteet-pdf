package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste;

import com.fasterxml.jackson.annotation.JsonInclude;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.vst.VapaasivistystyoSisaltoKevytDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PerusteDto extends PerusteBaseDto {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Set<SuoritustapaDto> suoritustavat;
    private KVLiiteDto kvliite;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private VapaasivistystyoSisaltoKevytDto vstSisalto;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private OpasSisaltoKevytDto oppaanSisalto;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer laajuus;
}
