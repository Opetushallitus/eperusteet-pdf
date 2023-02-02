package fi.vm.sade.eperusteet.pdf.dto.ylops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionDto {
    private Integer numero;
    private Date pvm;
    private String muokkaajaOid;
    private String kommentti = "";
    private String nimi = "";
}
