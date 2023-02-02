package fi.vm.sade.eperusteet.pdf.dto.ylops.lops2019;

import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.lops2019.oppiaineet.moduuli.Lops2019ModuuliDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lops2019OpintojaksoPerusteDto {
    @Builder.Default
    private List<Lops2019ModuuliDto> moduulit = new ArrayList<>();
}
