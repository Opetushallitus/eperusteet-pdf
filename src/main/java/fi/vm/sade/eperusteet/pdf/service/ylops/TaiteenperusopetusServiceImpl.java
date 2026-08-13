package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TPOOpetuksenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.TaiteenalaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.TaiteenosaDto;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addList;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;

@Slf4j
@Service
public class TaiteenperusopetusServiceImpl implements TaiteenperusopetusService {

    @Autowired
    private LocalizedMessagesService messages;

    @Override
    public void addTaiteenalat(DokumenttiYlops docBase) {
        List<TaiteenalaDto> taiteenalat = docBase.getOps().getTaiteenalat();
        if (CollectionUtils.isEmpty(taiteenalat)) {
            return;
        }

        Map<String, fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto> perusteenTaiteenalat = perusteenTaiteenalat(docBase);
        Map<Long, fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenosaDto> perusteenTaiteenosat = perusteenTaiteenosat(perusteenTaiteenalat.values());

        taiteenalat.forEach(taiteenala -> addTaiteenala(
                docBase,
                taiteenala,
                perusteenTaiteenalat.get(taiteenala.getKoodi()),
                perusteenTaiteenosat));
    }

    private void addTaiteenala(
            DokumenttiYlops docBase,
            TaiteenalaDto taiteenala,
            fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto perusteenTaiteenala,
            Map<Long, fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenosaDto> perusteenTaiteenosat
    ) {

      if (perusteenTaiteenala == null) {
        return;
      }

        addHeader(docBase, getTextString(docBase, taiteenala.getNimi()) + laajuusSuffiksi(docBase, perusteenTaiteenala.getLaajuus()));
        addLokalisoituteksti(docBase, perusteenTaiteenala.getTeksti(), "cite");

        addPaikallinenTarkennus(docBase, taiteenala.getPaikallinenTarkennus());

        if (!CollectionUtils.isEmpty(taiteenala.getTaiteenosat())) {
            docBase.getGenerator().increaseDepth();
            taiteenala.getTaiteenosat().forEach(taiteenosa -> addTaiteenosa(
                    docBase,
                    taiteenosa,
                    perusteenTaiteenosat.get(taiteenosa.getPerusteenTaiteenosanId())));
            docBase.getGenerator().decreaseDepth();
        }

        docBase.getGenerator().increaseNumber();
    }

    private void addTaiteenosa(
            DokumenttiYlops docBase,
            TaiteenosaDto taiteenosa,
            fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenosaDto perusteenTaiteenosa
    ) {
        if (perusteenTaiteenosa == null) {
            return;
        }

        addHeader(docBase, getTextString(docBase, perusteenTaiteenosa.getNimi())
                + laajuusSuffiksi(docBase, perusteenTaiteenosa.getLaajuus()));

        addLokalisoituteksti(docBase, perusteenTaiteenosa.getKuvaus(), "cite");

        if (!CollectionUtils.isEmpty(perusteenTaiteenosa.getTavoitteet())) {
            addTeksti(docBase, messages.translate("tavoitteet", docBase.getKieli()), "h6");
            addList(docBase, perusteenTaiteenosa.getTavoitteet());
        }

        addPaikallinenTarkennus(docBase, taiteenosa.getPaikallinenTarkennus());

        docBase.getGenerator().increaseNumber();
    }

    private void addPaikallinenTarkennus(DokumenttiYlops docBase, LokalisoituTekstiDto paikallinenTarkennus) {
        if (ObjectUtils.isEmpty(getTextString(docBase, paikallinenTarkennus))) {
            return;
        }

        addLokalisoituteksti(docBase, paikallinenTarkennus, "div");
    }

    private String laajuusSuffiksi(DokumenttiYlops docBase, BigDecimal laajuus) {
        if (laajuus == null) {
            return "";
        }

        return ", " + laajuus.stripTrailingZeros().toPlainString()
                + " " + messages.translate("docgen.laajuus.op", docBase.getKieli());
    }

    private Map<String, fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto> perusteenTaiteenalat(DokumenttiYlops docBase) {
        TPOOpetuksenSisaltoDto perusteenSisalto = docBase.getPeruste().getTpoOpetuksenSisalto();
        if (perusteenSisalto == null || perusteenSisalto.getSisalto() == null) {
            return Collections.emptyMap();
        }

        return CollectionUtil.treeToStream(perusteenSisalto.getSisalto(), TaiteenperusopetusServiceImpl::lapset)
                .map(PerusteenOsaViiteDto::getPerusteenOsa)
                .filter(perusteenOsa -> perusteenOsa instanceof fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto)
                .map(perusteenOsa -> (fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto) perusteenOsa)
                .filter(taiteenala -> taiteenala.getKoodi() != null && taiteenala.getKoodi().getUri() != null)
                .collect(Collectors.toMap(taiteenala -> taiteenala.getKoodi().getUri(), Function.identity(), (a, b) -> a));
    }

    private Map<Long, fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenosaDto> perusteenTaiteenosat(
            Collection<fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto> perusteenTaiteenalat
    ) {
        return perusteenTaiteenalat.stream()
                .map(fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto::getTaiteenOsat)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(taiteenosa -> taiteenosa.getId() != null)
                .collect(Collectors.toMap(
                        fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenosaDto::getId,
                        Function.identity(),
                        (a, b) -> a));
    }

    private static Collection<PerusteenOsaViiteDto.Laaja> lapset(PerusteenOsaViiteDto.Laaja viite) {
        return viite.getLapset() != null ? viite.getLapset() : Collections.emptyList();
    }
}
