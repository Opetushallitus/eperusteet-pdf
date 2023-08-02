package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class DokumenttiBase {
    Document document;
    Element headElement;
    Element bodyElement;
    Kieli kieli;
    GeneratorData generatorData;
    CharapterNumberGenerator generator;
    PerusteKaikkiDto peruste;

    public Optional<PerusteenOsaViiteDto.Laaja> getPerusteenOsa(Long perusteenOsaId) {
        return CollectionUtil.treeToStream(
                        peruste.getSisallot().stream().findFirst().get().getSisalto(),
                        PerusteenOsaViiteDto.Laaja::getLapset)
                .filter(laps -> laps.getPerusteenOsa() != null)
                .filter(osa -> osa.getPerusteenOsa().getId().equals(perusteenOsaId))
                .findFirst();
    }
}
