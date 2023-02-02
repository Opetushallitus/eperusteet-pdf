package fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.lukio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OppaineKurssiTreeStructureDto {
    private String kommentti;
    private List<LukiokurssiOppaineMuokkausDto> kurssit = new ArrayList<>();
    private List<OppiaineJarjestysDto> oppiaineet = new ArrayList<>();
}
