package fi.vm.sade.eperusteet.eperusteetpdfservice.service.util;

import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KVLiiteDokumentti extends DokumenttiBase {
    KVLiiteJulkinenDto kvLiiteJulkinenDto;
}
