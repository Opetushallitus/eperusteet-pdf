package fi.vm.sade.eperusteet.pdf.dto.ylops.lukio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LukioOppiaineTiedotExportDto {
    private LukioOppiaineTiedotDto tiedot;
    private List<LukioOppiaineTiedotDto> oppimaarat;
}
