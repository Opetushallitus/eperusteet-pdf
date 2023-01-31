package fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti;

import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.amosaa.enums.OmaOsaAlueTyyppi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmaOsaAlueDto {
    private Long id;
    private OmaOsaAlueTyyppi tyyppi;
    private LokalisoituTekstiDto nimi;
    private String perusteenOsaAlueKoodi;
    private Long perusteenOsaAlueId;
    private LokalisoituTekstiDto tavatjaymparisto;
    private LokalisoituTekstiDto arvioinnista;
    private boolean piilotettu;
    private List<VapaaTekstiDto> vapaat = new ArrayList<>();
    private PaikallisetAmmattitaitovaatimukset2019Dto osaamistavoitteet = new PaikallisetAmmattitaitovaatimukset2019Dto();
}
