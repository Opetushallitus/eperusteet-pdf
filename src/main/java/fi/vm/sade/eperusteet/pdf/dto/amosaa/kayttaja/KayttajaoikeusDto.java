package fi.vm.sade.eperusteet.pdf.dto.amosaa.kayttaja;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.Reference;
import fi.vm.sade.eperusteet.pdf.dto.enums.KayttajaoikeusTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KayttajaoikeusDto {
    private Long id;
    private Reference kayttaja;
    private Reference opetussuunnitelma;
    private Reference koulutustoimija;
    private KayttajaoikeusTyyppi oikeus;
}
