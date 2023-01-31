package fi.vm.sade.eperusteet.pdf.service.util;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.service.eperusteet.DokumenttiBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KVLiiteDokumentti extends DokumenttiBase {
    KVLiiteJulkinenDto kvLiiteJulkinenDto;
}
