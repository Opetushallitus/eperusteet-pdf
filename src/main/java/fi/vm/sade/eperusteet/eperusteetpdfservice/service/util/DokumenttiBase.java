package fi.vm.sade.eperusteet.eperusteetpdfservice.service.util;

import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.Dokumentti;
import fi.vm.sade.eperusteet.eperusteetpdfservice.domain.enums.Kieli;
import fi.vm.sade.eperusteet.eperusteetpdfservice.dto.peruste.PerusteDto;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Getter
@Setter
public class DokumenttiBase {
    Document document;
    Element headElement;
    Element bodyElement;
    PerusteDto peruste;
    Kieli kieli;
    Dokumentti dokumentti;
}
