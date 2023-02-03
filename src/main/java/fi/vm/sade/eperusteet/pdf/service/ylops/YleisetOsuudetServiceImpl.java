//package fi.vm.sade.eperusteet.pdf.service.ylops;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
//import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
//import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
//import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;
//import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
//import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteDto;
//import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleDto;
//import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
//import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
//import fi.vm.sade.eperusteet.pdf.exception.NotExistsException;
//import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
//import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
//import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
//import fi.vm.sade.eperusteet.pdf.utils.LocalizedMessagesService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
//import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
//import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
//
//@Slf4j
//@Service
//public class YleisetOsuudetServiceImpl implements YleisetOsuudetService {
//
//    @Autowired
//    private LocalizedMessagesService messages;
//
//    @Autowired
//    private EperusteetService eperusteetService;
//
//    @Autowired
//    private YlopsService ylopsService;
//
//    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();
//
//    public void addYleisetOsuudet(DokumenttiYlops docBase) {
//        Optional.ofNullable(docBase.getOps().getTekstit())
//                .ifPresent(tekstit -> {
//                    addTekstiKappale(docBase, tekstit, opetussuunnitelmaVanhaaRakennetta(docBase));
//                });
//    }
//
//    private boolean opetussuunnitelmaVanhaaRakennetta(DokumenttiYlops docBase) {
//        if (KoulutusTyyppi.PERUSOPETUS.equals(docBase.getOps().getKoulutustyyppi())) {
//            return docBase.getOps().getTekstit().getLapset().stream().noneMatch(tekstiKappaleViite -> tekstiKappaleViite.getPerusteTekstikappaleId() != null);
//        }
//        return false;
//    }
//
//    private void addTekstiKappale(DokumenttiYlops docBase, TekstiKappaleViiteDto viite, boolean paataso) {
//        addTekstiKappale(docBase, viite, paataso, false);
//    }
//
//    private void addTekstiKappale(DokumenttiYlops docBase, TekstiKappaleViiteDto viite, boolean paataso, boolean liite) {
//        for (TekstiKappaleViiteDto lapsi : viite.getLapset()) {
//            if (lapsi != null && lapsi.getTekstiKappale() != null && !lapsi.isPiilotettu()) {
//
//                if (liite != isLiite(lapsi, docBase)) {
//                    continue;
//                }
//
//                // Ei näytetä yhteisen osien Pääkappaleiden otsikoita
//                // Opetuksen järjestäminen ja Opetuksen toteuttamisen lähtökohdat
//                if (paataso) {
//                    addTekstiKappale(docBase, lapsi, false, liite);
//                } else {
//                    if (hasTekstiSisalto(docBase, lapsi) || hasTekstiSisaltoRecursive(docBase, lapsi)) {
//
//                        TekstiKappaleDto perusteenTekstikappale = null;
//                        if (lapsi.getPerusteTekstikappaleId() != null) {
//                            perusteenTekstikappale = getPerusteTekstikappale(docBase.getOps().getPerusteenId(), lapsi.getPerusteTekstikappaleId());
//                            if (perusteenTekstikappale != null) {
//                                addHeader(docBase, getTextString(docBase, perusteenTekstikappale.getNimi()));
//                            }
//                        }
//
//                        if (perusteenTekstikappale == null && lapsi.getTekstiKappale().getNimi() != null)  {
//                            addHeader(docBase, getTextString(docBase, lapsi.getTekstiKappale().getNimi()));
//                        }
//
//                        if (!opetussuunnitelmaVanhaaRakennetta(docBase)) {
//
//                            // Perusteen teksti luvulle jos valittu esittäminen
//                            if (lapsi.isNaytaPerusteenTeksti() && perusteenTekstikappale != null) {
//                                addLokalisoituteksti(docBase, perusteenTekstikappale.getTeksti(),"cite");
//                            }
//
//                            if (lapsi.isNaytaPohjanTeksti()) {
//                                List<TekstiKappaleViiteDto.Matala> pohjaTekstit = ylopsService.getTekstiKappaleViiteOriginals(docBase.getOps().getId(), lapsi.getId());
//                                pohjaTekstit.stream()
//                                        .filter(pohjaTeksti -> pohjaTeksti != null && pohjaTeksti.getTekstiKappale() != null && pohjaTeksti.getTekstiKappale().getTeksti() != null)
//                                        .forEach(pohjaTeksti -> addLokalisoituteksti(docBase, pohjaTeksti.getTekstiKappale().getTeksti(), "cite"));
//                            }
//                        }
//
//                        // Opsin teksti luvulle
//                        if (lapsi.getTekstiKappale().getTeksti() != null) {
//                            addLokalisoituteksti(docBase, lapsi.getTekstiKappale().getTeksti(), "div");
//                        }
//
//                        if (lapsi.getTekstiKappale().getNimi() != null) {
//                            docBase.getGenerator().increaseDepth();
//                        }
//
//                        // Rekursiivisesti
//                        addTekstiKappale(docBase, lapsi, false, liite);
//
//                        if (lapsi.getTekstiKappale().getNimi() != null) {
//                            docBase.getGenerator().decreaseDepth();
//                            docBase.getGenerator().increaseNumber();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private boolean hasTekstiSisaltoRecursive(DokumenttiYlops docBase, TekstiKappaleViiteDto tekstiKappaleViite) {
//        return CollectionUtil.treeToStream(tekstiKappaleViite, TekstiKappaleViiteDto::getLapset).anyMatch(viite -> hasTekstiSisalto(docBase, viite));
//    }
//
//    private boolean hasTekstiSisalto(DokumenttiYlops docBase, TekstiKappaleViiteDto viite) {
//        Long pTekstikappaleId = viite.getPerusteTekstikappaleId();
//        if (viite.isNaytaPerusteenTeksti() && pTekstikappaleId != null) {
//            try {
//                if (KoulutustyyppiToteutus.LOPS2019.equals(docBase.getOps().getToteutus())) {
//                    TekstiKappaleViiteDto perusteTekstikappale = ylopsService.getLops2019PerusteTekstikappale(docBase.getOps().getId(), pTekstikappaleId);
//
//                    if (perusteTekstikappale != null && perusteTekstikappale.getTekstiKappale() != null) {
//                        return true;
//                    }
//                } else {
//                    TekstiKappaleDto tekstikappale = opetussuunnitelmaService.getPerusteTekstikappale(docBase.getOps().getId(), pTekstikappaleId);
//                    if (tekstikappale != null) {
//                        return true;
//                    }
//                }
//            } catch (BusinessRuleViolationException | NotExistsException e) {
//            }
//        }
//
//        if (viite.isNaytaPohjanTeksti()) {
//            List<TekstiKappaleViiteDto.Matala> pohjaTekstit = ylopsService.getTekstiKappaleViiteOriginals(docBase.getOps().getId(), viite.getId());
//            boolean pohjateksti = pohjaTekstit.stream()
//                    .anyMatch(pohjaTeksti -> pohjaTeksti != null && pohjaTeksti.getTekstiKappale() != null && pohjaTeksti.getTekstiKappale().getTeksti() != null);
//
//            if (pohjateksti) {
//                return true;
//            }
//        }
//
//        // Opsin teksti luvulle
//        return viite.getTekstiKappale() != null && viite.getTekstiKappale().getTeksti() != null;
//    }
//
//    public void addLiitteet(DokumenttiYlops docBase) {
//        if (docBase.getOps().getTekstit() != null) {
//            addTekstiKappale(docBase, docBase.getOps().getTekstit(), false, true);
//        }
//    }
//
//    public TekstiKappaleDto getPerusteTekstikappale(Long perusteId, Long tekstikappaleId) {
//        PerusteDto perusteDto = objectMapper.convertValue(eperusteetService.getPerusteKaikkiDto(perusteId, null), PerusteDto.class) ;
//        TekstiKappaleViiteDto sisalto = perusteDto.getTekstiKappaleViiteSisalto();
//
//        if (sisalto != null) {
//            TekstiKappaleViiteDto perusteenTekstikappaleViite = CollectionUtil.treeToStream(
//                    sisalto, TekstiKappaleViiteDto::getLapset)
//                    .filter(viiteDto -> viiteDto.getTekstiKappale() != null
//                            && viiteDto.getTekstiKappale().getTeksti() != null
//                            && Objects.equals(tekstikappaleId, viiteDto.getTekstiKappale().getId()))
//                    .findFirst()
//                    .orElse(null);
//            if (perusteenTekstikappaleViite != null) {
//                return new TekstiKappaleDto(
//                        new LokalisoituTekstiDto(perusteenTekstikappaleViite.getTekstiKappale().getNimi().asMap()),
//                        new LokalisoituTekstiDto(perusteenTekstikappaleViite.getTekstiKappale().getTeksti().asMap()),
//                        null);
//            }
//        }
//        return null;
//    }
//
//    private boolean isLiite(TekstiKappaleViiteDto viite, DokumenttiYlops docBase) {
//        return viite.isLiite()
//                || (viite.getTekstiKappale() != null
//                && viite.getTekstiKappale().getNimi() != null
//                && viite.getTekstiKappale().getNimi().getTekstit() != null
//                && viite.getTekstiKappale().getNimi().getTekstit().get(docBase.getKieli()) != null
//                && viite.getTekstiKappale().getNimi().getTekstit().get(docBase.getKieli())
//                .equals(messages.translate("liitteet", docBase.getKieli())))
//                || (viite.getVanhempi() != null && isLiite(viite.getVanhempi(), docBase));
//    }
//}
