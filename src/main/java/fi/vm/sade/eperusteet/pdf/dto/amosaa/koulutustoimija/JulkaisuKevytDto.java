package fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JulkaisuKevytDto {
    private int revision;
    private Date luotu;
}
