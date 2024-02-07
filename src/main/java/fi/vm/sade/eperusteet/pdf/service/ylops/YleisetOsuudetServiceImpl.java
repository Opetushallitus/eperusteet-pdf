package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.TekstiKappaleViiteExportDto;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.NotExistsException;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;

@Slf4j
@Service
public class YleisetOsuudetServiceImpl implements YleisetOsuudetService {

    @Autowired
    private LocalizedMessagesService messages;

    public void addYleisetOsuudet(DokumenttiYlops docBase) {
        Optional.ofNullable(docBase.getOps().getTekstit())
                .ifPresent(tekstit -> {
                    addTekstiKappale(docBase, tekstit, opetussuunnitelmaVanhaaRakennetta(docBase));
                });
    }

    private boolean opetussuunnitelmaVanhaaRakennetta(DokumenttiYlops docBase) {
        if (KoulutusTyyppi.PERUSOPETUS.equals(docBase.getOps().getKoulutustyyppi())) {
            return docBase.getOps().getTekstit().getLapset().stream().noneMatch(tekstiKappaleViite -> tekstiKappaleViite.getPerusteTekstikappaleId() != null);
        }
        return false;
    }

    private void addTekstiKappale(DokumenttiYlops docBase, TekstiKappaleViiteExportDto.Puu viite, boolean paataso) {
        addTekstiKappale(docBase, viite, paataso, false);
    }

    private void addTekstiKappale(DokumenttiYlops docBase, TekstiKappaleViiteExportDto.Puu viite, boolean paataso, boolean liite) {
        for (TekstiKappaleViiteExportDto.Puu lapsi : viite.getLapset()) {
            if (lapsi != null && lapsi.getTekstiKappale() != null && !lapsi.isPiilotettu()) {

                if (liite != isLiite(lapsi, docBase)) {
                    continue;
                }

                // Ei näytetä yhteisen osien Pääkappaleiden otsikoita
                // Opetuksen järjestäminen ja Opetuksen toteuttamisen lähtökohdat
                if (paataso) {
                    addTekstiKappale(docBase, lapsi, false, liite);
                } else {
                    if (hasTekstiSisalto(docBase, lapsi) || hasTekstiSisaltoRecursive(docBase, lapsi)) {

                        fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto perusteenTekstikappale = null;
                        if (lapsi.getPerusteTekstikappaleId() != null) {
                            perusteenTekstikappale = getPerusteTekstikappale(docBase.getPeruste(), lapsi.getPerusteTekstikappaleId());
                            if (perusteenTekstikappale != null) {
                                addHeader(docBase, getTextString(docBase, perusteenTekstikappale.getNimi()));
                            }
                        }

                        if (perusteenTekstikappale == null && lapsi.getTekstiKappale().getNimi() != null)  {
                            addHeader(docBase, getTextString(docBase, lapsi.getTekstiKappale().getNimi()));
                        }

                        if (!opetussuunnitelmaVanhaaRakennetta(docBase)) {

                            // Perusteen teksti luvulle jos valittu esittäminen
                            if (lapsi.isNaytaPerusteenTeksti()) {
                                if (perusteenTekstikappale != null) {
                                    addLokalisoituteksti(docBase, perusteenTekstikappale.getTeksti(),"cite");

                                    if (perusteenTekstikappale.getTunniste() != null && perusteenTekstikappale.getTunniste().equals(PerusteenOsaTunniste.LAAJAALAINENOSAAMINEN)) {
                                        PerusteKaikkiDto peruste = docBase.getPeruste();
                                        if (peruste.getAipeOpetuksenPerusteenSisalto() != null && peruste.getAipeOpetuksenPerusteenSisalto().getLaajaalaisetosaamiset() != null) {
                                            peruste.getAipeOpetuksenPerusteenSisalto().getLaajaalaisetosaamiset().forEach(lao -> {
                                                addLokalisoituteksti(docBase, lao.getNimi(),"h5i");
                                                addLokalisoituteksti(docBase, lao.getKuvaus(),"cite");
                                            });
                                        }
                                    }
                                }

                            }

                            if (lapsi.isNaytaPohjanTeksti()) {
                                List<TekstiKappaleViiteExportDto> pohjaTekstit = getTekstiKappaleViiteOriginals(docBase.getOps().getId(), lapsi);
                                pohjaTekstit.stream()
                                        .filter(pohjaTeksti -> pohjaTeksti != null && pohjaTeksti.getTekstiKappale() != null && pohjaTeksti.getTekstiKappale().getTeksti() != null)
                                        .forEach(pohjaTeksti -> addLokalisoituteksti(docBase, pohjaTeksti.getTekstiKappale().getTeksti(), "cite"));
                            }
                        }

                        // Opsin teksti luvulle
                        if (lapsi.getTekstiKappale().getTeksti() != null) {
                            addLokalisoituteksti(docBase, lapsi.getTekstiKappale().getTeksti(), "div");
                        }

                        if (lapsi.getTekstiKappale().getNimi() != null) {
                            docBase.getGenerator().increaseDepth();
                        }

                        // Rekursiivisesti
                        addTekstiKappale(docBase, lapsi, false, liite);

                        if (lapsi.getTekstiKappale().getNimi() != null) {
                            docBase.getGenerator().decreaseDepth();
                            docBase.getGenerator().increaseNumber();
                        }
                    }
                }
            }
        }
    }

    private boolean hasTekstiSisaltoRecursive(DokumenttiYlops docBase, TekstiKappaleViiteExportDto.Puu tekstiKappaleViite) {
        return CollectionUtil.treeToStream(tekstiKappaleViite, TekstiKappaleViiteExportDto.Puu::getLapset).anyMatch(viite -> hasTekstiSisalto(docBase, viite));
    }

    private boolean hasTekstiSisalto(DokumenttiYlops docBase, TekstiKappaleViiteExportDto.Puu viite) {
        Long pTekstikappaleId = viite.getPerusteTekstikappaleId();
        if (viite.isNaytaPerusteenTeksti() && pTekstikappaleId != null) {
            try {
                if (getPerusteTekstikappale(docBase.getPeruste(), pTekstikappaleId) != null) {
                    return true;
                }
            } catch (BusinessRuleViolationException | NotExistsException e) {
            }
        }

        if (viite.isNaytaPohjanTeksti()) {
            List<TekstiKappaleViiteExportDto> pohjaTekstit = getTekstiKappaleViiteOriginals(docBase.getOps().getId(), viite);
            boolean pohjateksti = pohjaTekstit.stream()
                    .anyMatch(pohjaTeksti -> pohjaTeksti != null && pohjaTeksti.getTekstiKappale() != null && pohjaTeksti.getTekstiKappale().getTeksti() != null);

            if (pohjateksti) {
                return true;
            }
        }

        // Opsin teksti luvulle
        return viite.getTekstiKappale() != null && viite.getTekstiKappale().getTeksti() != null;
    }

    public List<TekstiKappaleViiteExportDto> getTekstiKappaleViiteOriginals(Long opsId, TekstiKappaleViiteExportDto.Puu viite) {
        List<TekstiKappaleViiteExportDto> viiteList = new ArrayList<>();
        TekstiKappaleViiteExportDto viiteOriginal = viite.getOriginal();

        if (viiteOriginal != null && viiteOriginal.getOriginal() != null && viiteOriginal.isNaytaPohjanTeksti()) {
            viiteList.add(viiteOriginal.getOriginal());
        }

        viiteList.add(viiteOriginal);
        return viiteList;
    }

    public void addLiitteet(DokumenttiYlops docBase) {
        if (docBase.getOps().getTekstit() != null) {
            addTekstiKappale(docBase, docBase.getOps().getTekstit(), false, true);
        }
    }

    public fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto getPerusteTekstikappale(PerusteKaikkiDto perusteDto, Long tekstikappaleId) {
        PerusteenOsaViiteDto.Laaja perusteenTekstikappaleViite = getPerusteTekstikappaleViite(perusteDto, tekstikappaleId);

        if (perusteenTekstikappaleViite != null) {
            return (fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto)perusteenTekstikappaleViite.getPerusteenOsa();
        }
        return null;
    }

    public PerusteenOsaViiteDto.Laaja getPerusteTekstikappaleViite(PerusteKaikkiDto perusteDto, Long tekstikappaleId) {
        PerusteenOsaViiteDto.Laaja sisalto = perusteDto.getSisallot().stream().findFirst().get().getSisalto();

        if (sisalto != null) {
            return CollectionUtil.treeToStream(
                            sisalto, PerusteenOsaViiteDto.Laaja::getLapset)
                    .filter(viiteDto -> {
                        if (viiteDto.getPerusteenOsa() instanceof fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto) {
                            fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto tk = (fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto) viiteDto.getPerusteenOsa();

                            if (tk.getId().equals(tekstikappaleId)) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private boolean isLiite(TekstiKappaleViiteExportDto.Puu viite, DokumenttiYlops docBase) {
        return viite.isLiite()
                || (viite.getTekstiKappale() != null
                && viite.getTekstiKappale().getNimi() != null
                && viite.getTekstiKappale().getNimi().getTekstit() != null
                && viite.getTekstiKappale().getNimi().getTekstit().get(docBase.getKieli()) != null
                && viite.getTekstiKappale().getNimi().getTekstit().get(docBase.getKieli())
                .equals(messages.translate("liitteet", docBase.getKieli())))
                || (docBase.getVanhempi(viite).isPresent() && isLiite(docBase.getVanhempi(viite).get(), docBase));
    }
}
