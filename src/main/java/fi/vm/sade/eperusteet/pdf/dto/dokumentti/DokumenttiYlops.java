package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.VuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.TekstiKappaleViiteExportDto;
import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
public class DokumenttiYlops extends DokumenttiBase {
    OpetussuunnitelmaExportDto ops;

    public Optional<VuosiluokkaKokonaisuusDto> getPerusteVlk(UUID uuid) {
        return peruste.getPerusopetuksenPerusteenSisalto().getVuosiluokkakokonaisuudet(uuid);
    }

    public Optional<TekstiKappaleViiteExportDto.Puu> getVanhempi(TekstiKappaleViiteExportDto.Puu viite) {
        return CollectionUtil.treeToStream(ops.getTekstit(), TekstiKappaleViiteExportDto.Puu::getLapset)
                .filter(vanhempi -> vanhempi.getLapset().contains(viite))
                .findAny();
    }
}
