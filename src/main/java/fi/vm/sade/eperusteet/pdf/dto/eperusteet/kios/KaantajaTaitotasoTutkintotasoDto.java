package fi.vm.sade.eperusteet.pdf.dto.eperusteet.kios;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KaantajaTaitotasoTutkintotasoDto {

    private Long id;
    private LokalisoituTekstiDto nimi;
    @Builder.Default
    private List<KaantajaTaitotasoTutkintotasoOsaDto> osat = new ArrayList<>();
}

