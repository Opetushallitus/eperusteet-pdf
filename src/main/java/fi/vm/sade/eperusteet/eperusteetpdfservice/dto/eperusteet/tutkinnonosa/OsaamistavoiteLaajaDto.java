package fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.tutkinnonosa;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.Reference;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.ammattitaitovaatimukset.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.arviointi.ArviointiDto;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.util.LokalisoituTekstiDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OsaamistavoiteLaajaDto extends OsaamistavoiteDto {
    private LokalisoituTekstiDto tavoitteet;
    private LokalisoituTekstiDto tunnustaminen;
    private ArviointiDto arviointi;
    private List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimuksetLista;
    private Reference esitieto;
}
