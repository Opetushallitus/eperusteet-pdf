package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteDto;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Getter
@Setter
public class YlopsDokumenttiBase {
    Document document;
    Element headElement;
    Element bodyElement;
    OpetussuunnitelmaDto ops;
    PerusteDto perusteDto;
    CharapterNumberGenerator generator;
    Kieli kieli;
    Dokumentti dokumentti;
}
