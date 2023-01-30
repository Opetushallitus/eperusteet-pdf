package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class KotoOpintoExportDto extends KotoOpintoDto {
    private fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.KotoOpintoDto perusteenOsa;
}
