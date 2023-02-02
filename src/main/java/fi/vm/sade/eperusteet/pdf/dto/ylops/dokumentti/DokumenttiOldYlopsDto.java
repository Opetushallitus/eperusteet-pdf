package fi.vm.sade.eperusteet.pdf.dto.ylops.dokumentti;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DokumenttiOldYlopsDto {
    private Long id;
    private Long opsId;
    private String luoja;
    private Kieli kieli;
    private Date aloitusaika;
    private Date valmistumisaika;
    private DokumenttiTila tila = DokumenttiTila.EI_OLE;
    private boolean kansikuva = false;
    private boolean ylatunniste = false;
    private boolean alatunniste = false;
    private String virhekoodi = "";
}
