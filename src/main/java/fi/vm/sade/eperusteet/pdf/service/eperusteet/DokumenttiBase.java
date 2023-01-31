package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import fi.vm.sade.eperusteet.pdf.domain.eperusteet.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
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
    PerusteKaikkiDto peruste;
    Kieli kieli;
    Dokumentti dokumentti;
}
