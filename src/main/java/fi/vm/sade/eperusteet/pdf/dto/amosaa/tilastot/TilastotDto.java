package fi.vm.sade.eperusteet.pdf.dto.amosaa.tilastot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TilastotDto {
    private Long koulutuksenjarjestajia;
    private Long opetussuunnitelmia;
    private Long kayttajia;
}
