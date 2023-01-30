package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryDto {
    private int sivu = 0;
    private int sivukoko = 25;
    private boolean tuleva = true;
    private boolean siirtyma = true;
    private boolean voimassaolo = true;
    private boolean poistunut = true;
    private String nimi;
    private String kieli = "fi";
    private String jarjestys;
    private boolean jarjestysNouseva = false;
}
