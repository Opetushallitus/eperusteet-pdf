package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.PerusteDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.TekstiKappaleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerusteTekstikappaleillaDto {
    private PerusteDto peruste;
    private List<TekstiKappaleDto> tekstikappeet;
}
