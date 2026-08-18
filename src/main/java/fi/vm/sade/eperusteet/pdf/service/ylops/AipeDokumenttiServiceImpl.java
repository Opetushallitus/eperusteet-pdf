package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.AIPEKurssiExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.AIPEOppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.AIPESisaltoExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.AIPEVaiheExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPEKurssiSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPELaajaalainenosaaminenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPEOpetuksenkohdealueSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPEOpetuksentavoiteSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPEOppiaineSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.aipe.export.PerusteAIPEVaiheSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.peruste.PerusteTekstiOsaDto;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Element;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.hasLokalisoituteksti;

@Service
public class AipeDokumenttiServiceImpl implements AipeDokumenttiService {

    @Autowired
    private LocalizedMessagesService messages;

    @Override
    public void addAipeSisalto(DokumenttiYlops docBase) {
        AIPESisaltoExportDto export = docBase.getOps().getAipe();
        if (export == null || CollectionUtils.isEmpty(export.getVaiheet())) {
            return;
        }

        export.getVaiheet().stream()
                .filter(Objects::nonNull)
                .forEach(vaihe -> addVaihe(docBase, vaihe));
    }

    private void addVaihe(DokumenttiYlops docBase, AIPEVaiheExportDto exportVaihe) {
        PerusteAIPEVaiheSisaltoDto perusteSisalto = exportVaihe.getPerusteSisalto();
        addHeader(docBase, vaiheNimi(docBase, exportVaihe));

        docBase.getGenerator().increaseDepth();

        if (perusteSisalto != null) {
            addTekstiOsa(docBase, perusteSisalto.getSiirtymaEdellisesta());
            addTekstiOsa(docBase, perusteSisalto.getTehtava());
            addTekstiOsa(docBase, perusteSisalto.getSiirtymaSeuraavaan());
            addTekstiOsa(docBase, perusteSisalto.getPaikallisestiPaatettavatAsiat());
            addVapaatTekstit(docBase, perusteSisalto.getVapaatTekstit());
        }

        addPaikallinenTarkennus(docBase, exportVaihe.getPaikallinenTarkennus());

        if (!CollectionUtils.isEmpty(exportVaihe.getOppiaineet())) {
            exportVaihe.getOppiaineet().stream()
                    .filter(Objects::nonNull)
                    .forEach(oppiaine -> addOppiaine(docBase, oppiaine, false));
        }

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addOppiaine(DokumenttiYlops docBase, AIPEOppiaineExportDto exportOppiaine, boolean oppimaara) {
        PerusteAIPEOppiaineSisaltoDto perusteSisalto = exportOppiaine.getPerusteSisalto();
        addHeader(docBase, oppiaineNimi(docBase, exportOppiaine, oppimaara));

        docBase.getGenerator().increaseDepth();

        if (perusteSisalto != null) {
            addTekstiOsa(docBase, perusteSisalto.getTehtava());
            addTekstiOsa(docBase, perusteSisalto.getTyotavat());
            addTekstiOsa(docBase, perusteSisalto.getOhjaus());
            addTekstiOsa(docBase, perusteSisalto.getArviointi());
            addTekstiOsa(docBase, perusteSisalto.getSisaltoalueinfo());
            addVapaatTekstit(docBase, perusteSisalto.getVapaatTekstit());

            addKuvaus(docBase, perusteSisalto.getPakollinenKurssiKuvaus(), "docgen.pakollinen_kurssi_kuvaus.title");
            addKuvaus(docBase, perusteSisalto.getSyventavaKurssiKuvaus(), "docgen.syventava_kurssi_kuvaus.title");
            addKuvaus(docBase, perusteSisalto.getSoveltavaKurssiKuvaus(), "docgen.soveltava_kurssi_kuvaus.title");
        }

        addPaikallinenTarkennus(docBase, exportOppiaine.getPaikallinenTarkennus());

        List<PerusteAIPEOpetuksentavoiteSisaltoDto> tavoitteet = visibleTavoitteet(perusteSisalto);
        if (!tavoitteet.isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.tavoitteet.title", docBase.getKieli()), "h5");
            addOppiaineTavoitteet(docBase, tavoitteet);
        }

        if (CollectionUtils.isEmpty(exportOppiaine.getOppimaarat()) && !CollectionUtils.isEmpty(exportOppiaine.getKurssit())) {
            addTeksti(docBase, messages.translate("docgen.kurssit.title", docBase.getKieli()), "h5");
            exportOppiaine.getKurssit().stream()
                    .filter(Objects::nonNull)
                    .forEach(kurssi -> addKurssi(docBase, kurssi));
        }

        if (!CollectionUtils.isEmpty(exportOppiaine.getOppimaarat())) {
            exportOppiaine.getOppimaarat().stream()
                    .filter(Objects::nonNull)
                    .forEach(oppimaaraDto -> addOppiaine(docBase, oppimaaraDto, true));
        }

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addKurssi(DokumenttiYlops docBase, AIPEKurssiExportDto exportKurssi) {
        PerusteAIPEKurssiSisaltoDto perusteSisalto = exportKurssi.getPerusteSisalto();
        addTeksti(docBase, kurssiNimi(docBase, exportKurssi), "h6");

        if (perusteSisalto != null) {
            addTeksti(docBase, getTextString(docBase, perusteSisalto.getKuvaus()), "div");
        }

        addPaikallinenTarkennus(docBase, exportKurssi.getPaikallinenTarkennus());

        if (perusteSisalto != null && !CollectionUtils.isEmpty(perusteSisalto.getTavoitteet())) {
            addTeksti(docBase, messages.translate("docgen.liitetyt_tavoitteet", docBase.getKieli()) + ":", "p");
            Element ul = docBase.getDocument().createElement("ul");
            docBase.getBodyElement().appendChild(ul);
            perusteSisalto.getTavoitteet().forEach(tavoite -> {
                Element li = docBase.getDocument().createElement("li");
                ul.appendChild(li);
                li.setTextContent(getTextString(docBase, tavoite.getTavoite()));
            });
        }
    }

    private void addOppiaineTavoitteet(DokumenttiYlops docBase, List<PerusteAIPEOpetuksentavoiteSisaltoDto> tavoitteet) {
        tavoitteet.forEach(tavoite -> {
            addTeksti(docBase, getTextString(docBase, tavoite.getTavoite()), "h5");

            if (hasLokalisoituteksti(docBase, tavoite.getTavoitteistaJohdetutOppimisenTavoitteet())) {
                addTeksti(docBase, messages.translate("tavoitteista-johdetut-oppimisen-tavoitteet", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, tavoite.getTavoitteistaJohdetutOppimisenTavoitteet()), "div");
            }

            Optional<PerusteAIPEOpetuksenkohdealueSisaltoDto> tavoitealue = Optional.ofNullable(tavoite.getKohdealueet())
                    .orElse(Collections.emptySet())
                    .stream()
                    .filter(kohdealue -> kohdealue != null && kohdealue.getNimi() != null)
                    .findFirst();
            if (tavoitealue.isPresent()) {
                addTeksti(docBase, messages.translate("tavoitealue", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, tavoitealue.get().getNimi()), "div");
            }

            List<PerusteAIPELaajaalainenosaaminenSisaltoDto> laajaalaiset = Optional.ofNullable(tavoite.getLaajattavoitteet())
                    .orElse(Collections.emptySet())
                    .stream()
                    .filter(lao -> lao != null && lao.getNimi() != null)
                    .sorted(Comparator.comparing(lao -> getTextString(docBase, lao.getNimi())))
                    .collect(Collectors.toList());
            if (!laajaalaiset.isEmpty()) {
                addTeksti(docBase, messages.translate("laaja-alainen-osaaminen", docBase.getKieli()), "h6");
                laajaalaiset.forEach(lao -> addTeksti(docBase, getTextString(docBase, lao.getNimi()), "p"));
                addTeksti(docBase, "", "p");
            }

            if (hasLokalisoituteksti(docBase, tavoite.getArvioinninKuvaus())) {
                addTeksti(docBase, messages.translate("arvioinnin-kohde", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninKuvaus()), "div");
            }

            if (hasLokalisoituteksti(docBase, tavoite.getArvioinninOtsikko())) {
                addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninOtsikko()), "h6");
            }

            if (!CollectionUtils.isEmpty(tavoite.getArvioinninkohteet())) {
                DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
                taulukko.addOtsikkosarakkeet(
                        messages.translate("arvosana", docBase.getKieli()),
                        messages.translate("osaamisen-kuvaus", docBase.getKieli())
                );

                tavoite.getArvioinninkohteet().stream()
                        .filter(kohde -> kohde.getArvosana() != null)
                        .sorted(Comparator.comparing(kohde -> kohde.getArvosana()))
                        .forEach(kohde -> {
                            DokumenttiRivi rivi = new DokumenttiRivi();
                            rivi.addSarake(
                                    messages.translate("osaamisen-kuvaus-arvosanalle-" + kohde.getArvosana(), docBase.getKieli()),
                                    getTextString(docBase, kohde.getOsaamisenKuvaus() != null
                                            ? kohde.getOsaamisenKuvaus()
                                            : LokalisoituTekstiDto.of("")));
                            taulukko.addRivi(rivi);
                        });

                taulukko.addToDokumentti(docBase);
            }

            if (hasLokalisoituteksti(docBase, tavoite.getVapaaTeksti())) {
                addTeksti(docBase, getTextString(docBase, tavoite.getVapaaTeksti()), "div");
            }
        });
    }

    private void addTekstiOsa(DokumenttiYlops docBase, PerusteTekstiOsaDto tekstiOsa) {
        if (tekstiOsa != null) {
            addTeksti(docBase, getTextString(docBase, tekstiOsa.getOtsikko()), "h5");
            addTeksti(docBase, getTextString(docBase, tekstiOsa.getTeksti()), "div");
        }
    }

    private void addVapaatTekstit(DokumenttiYlops docBase, List<KevytTekstiKappaleDto> vapaatTekstit) {
        if (CollectionUtils.isEmpty(vapaatTekstit)) {
            return;
        }
        vapaatTekstit.forEach(vapaaTeksti -> {
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h6");
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "div");
        });
    }

    private void addKuvaus(DokumenttiYlops docBase, LokalisoituTekstiDto kuvaus, String translationKey) {
        if (hasLokalisoituteksti(docBase, kuvaus)) {
            addTeksti(docBase, messages.translate(translationKey, docBase.getKieli()), "h5");
            addTeksti(docBase, getTextString(docBase, kuvaus), "div");
        }
    }

    private void addPaikallinenTarkennus(DokumenttiYlops docBase, LokalisoituTekstiDto tarkennus) {
        if (hasLokalisoituteksti(docBase, tarkennus) && !ObjectUtils.isEmpty(getTextString(docBase, tarkennus))) {
            addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, tarkennus, "div");
        }
    }

    private List<PerusteAIPEOpetuksentavoiteSisaltoDto> visibleTavoitteet(PerusteAIPEOppiaineSisaltoDto perusteSisalto) {
        if (perusteSisalto == null || CollectionUtils.isEmpty(perusteSisalto.getTavoitteet())) {
            return Collections.emptyList();
        }
        return perusteSisalto.getTavoitteet();
    }

    private String vaiheNimi(DokumenttiYlops docBase, AIPEVaiheExportDto exportVaihe) {
        String nimi = getTextString(docBase, exportVaihe.getNimi());
        return !ObjectUtils.isEmpty(nimi) ? nimi : messages.translate("docgen.vaiheet.title", docBase.getKieli());
    }

    private String oppiaineNimi(DokumenttiYlops docBase, AIPEOppiaineExportDto exportOppiaine, boolean oppimaara) {
        StringBuilder nimiBuilder = new StringBuilder();
        String nimi = getTextString(docBase, exportOppiaine.getNimi());
        if (ObjectUtils.isEmpty(nimi)) {
            nimi = messages.translate(oppimaara ? "docgen.nimeton_oppimaara" : "docgen.nimeton_oppiaine", docBase.getKieli());
        }
        nimiBuilder.append(nimi);

        KoodiDto koodi = exportOppiaine.getPerusteSisalto() != null ? exportOppiaine.getPerusteSisalto().getKoodi() : null;
        if (koodi != null && koodi.getArvo() != null) {
            nimiBuilder.append(" (").append(koodi.getArvo()).append(")");
        }
        return nimiBuilder.toString();
    }

    private String kurssiNimi(DokumenttiYlops docBase, AIPEKurssiExportDto exportKurssi) {
        StringBuilder nimiBuilder = new StringBuilder();
        String nimi = getTextString(docBase, exportKurssi.getNimi());
        nimiBuilder.append(!ObjectUtils.isEmpty(nimi) ? nimi : messages.translate("docgen.kurssit.title", docBase.getKieli()));

        KoodiDto koodi = exportKurssi.getPerusteSisalto() != null ? exportKurssi.getPerusteSisalto().getKoodi() : null;
        if (koodi != null && koodi.getUri() != null) {
            String[] splitArray = koodi.getUri().split("_");
            if (splitArray.length > 0) {
                nimiBuilder.append(" (");
                nimiBuilder.append(splitArray[splitArray.length - 1].toUpperCase());
                nimiBuilder.append(")");
            }
        }
        return nimiBuilder.toString();
    }
}
