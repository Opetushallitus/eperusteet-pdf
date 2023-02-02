package fi.vm.sade.eperusteet.pdf.dto.ylops.ops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpetussuunnitelmaJulkaistuQuery implements Serializable {
    private String nimi = "";
    private String kieli = "fi";
    private String perusteenDiaarinumero = "";
    private int sivu = 0;
    private int sivukoko = 10;
}
