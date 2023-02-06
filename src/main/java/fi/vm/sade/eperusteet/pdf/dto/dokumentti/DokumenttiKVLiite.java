package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DokumenttiKVLiite extends DokumenttiBase {
    KVLiiteJulkinenDto kvLiiteJulkinenDto;
    PerusteKaikkiDto peruste;
}
