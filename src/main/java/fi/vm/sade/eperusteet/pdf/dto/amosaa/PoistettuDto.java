package fi.vm.sade.eperusteet.pdf.dto.amosaa;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PoistettuDto extends RevisionDto {
    private Long id;
    private LokalisoituTekstiDto nimi;
    private Reference koulutustoimija;
    private Reference opetussuunnitelma;
    private Long poistettu;
    private SisaltoTyyppi tyyppi;
    private Date pvm;
    private String muokkaajaOid;
}
