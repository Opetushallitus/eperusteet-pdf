package fi.vm.sade.eperusteet.pdf.dto.amosaa.tilastot;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.KoulutustoimijaBaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToimijanTilastoDto {
    private KoulutustoimijaBaseDto koulutustoimija;
    private Long julkaistu;
    private Long valmis;
    private Long luonnos;
}
