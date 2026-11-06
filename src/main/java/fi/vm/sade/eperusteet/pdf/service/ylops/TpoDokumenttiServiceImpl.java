package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiRiviTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.TavoiteAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.TavoiteAlueDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.VapaatekstiPaikallinentarkennusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.PerusteTpoTaiteenalaSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.PerusteTpoVaiheSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.TaiteenOpintokokonaisuusExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.TaiteenalaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.TaiteenalanVaiheExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.TavoitealueValintaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.tpo.export.TpoSisaltoExportDto;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.hasLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.tagTeksti;

@Service
public class TpoDokumenttiServiceImpl implements TpoDokumenttiService {

    @Autowired
    private LocalizedMessagesService messages;

    @Override
    public void addTpoSisalto(DokumenttiYlops docBase) {
        TpoSisaltoExportDto export = docBase.getOps().getTpo();
        if (export == null || CollectionUtils.isEmpty(export.getTaiteenalat())) {
            return;
        }

        export.getTaiteenalat().stream()
                .filter(Objects::nonNull)
                .forEach(taiteenala -> addTaiteenala(docBase, taiteenala));
    }

    private void addTaiteenala(DokumenttiYlops docBase, TaiteenalaExportDto taiteenala) {
        addHeader(docBase, getTextString(docBase, taiteenala.getNimi()));

        PerusteTpoTaiteenalaSisaltoDto peruste = taiteenala.getPerusteenTaiteenala();
        if (peruste != null) {
            addDiv(docBase, peruste.getTeksti());
            addTaiteenalaSisalto(docBase, peruste.getAikuistenOpetus(), "docgen.taiteenala.aikuisten-opetus");
            addTaiteenalaSisalto(docBase, peruste.getKasvatus(), "docgen.taiteenala.kasvatus");
            addTaiteenalaSisalto(docBase, peruste.getOppimisenArviointiOpetuksessa(), "docgen.taiteenala.oppimisen-arvionti");
            addTaiteenalaSisalto(docBase, peruste.getTeemaopinnot(), "docgen.taiteenala.teemaopinnot");
            addTaiteenalaSisalto(docBase, peruste.getTyotavatOpetuksessa(), "docgen.taiteenala.tyotavat");
            addTaiteenalaSisalto(docBase, peruste.getYhteisetOpinnot(), "docgen.taiteenala.yhteiset-opinnot");
            addVapaatTekstit(docBase, peruste.getVapaatTekstit());
        }

        addPaikallinenTarkennus(docBase, taiteenala.getPaikallinenTarkennus());

        docBase.getGenerator().increaseDepth();
        if (!CollectionUtils.isEmpty(taiteenala.getVaiheet())) {
            taiteenala.getVaiheet().stream()
                    .filter(Objects::nonNull)
                    .forEach(vaihe -> addVaihe(docBase, vaihe));
        }
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addVaihe(DokumenttiYlops docBase, TaiteenalanVaiheExportDto vaihe) {
        PerusteTpoVaiheSisaltoDto peruste = vaihe.getPerusteenVaihe();
        addHeader(docBase, vaiheNimi(docBase, vaihe));

        if (peruste != null) {
            addDiv(docBase, peruste.getKuvaus());

            if (!CollectionUtils.isEmpty(peruste.getTavoitealueet())) {
                addTeksti(docBase, messages.translate("docgen.tavoitteet-ja-keskeiset-sisallot.title", docBase.getKieli()), "h5");
                addTavoitealueetTaulukko(docBase, peruste.getTavoitealueet(), false);
            }

            if (!CollectionUtils.isEmpty(peruste.getVapaatTekstit())) {
                peruste.getVapaatTekstit().stream()
                        .filter(Objects::nonNull)
                        .forEach(vapaaTeksti -> {
                            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
                            addDiv(docBase, vapaaTeksti.getTeksti());
                            addPaikallinenTarkennus(docBase, vapaanTekstinTarkennus(vaihe, vapaaTeksti.getId()));
                        });
            }
        }

        if (!CollectionUtils.isEmpty(vaihe.getOpintokokonaisuudet())) {
            vaihe.getOpintokokonaisuudet().stream()
                    .filter(Objects::nonNull)
                    .forEach(opintokokonaisuus -> addOpintokokonaisuus(docBase, opintokokonaisuus));
        }

        docBase.getGenerator().increaseNumber();
    }

    private void addOpintokokonaisuus(DokumenttiYlops docBase, TaiteenOpintokokonaisuusExportDto opintokokonaisuus) {
        addTeksti(docBase, opintokokonaisuusNimi(docBase, opintokokonaisuus), "h5");

        if (hasTeksti(docBase, opintokokonaisuus.getKuvaus())) {
            addLokalisoituteksti(docBase, opintokokonaisuus.getKuvaus(), "div");
        }

        List<TavoiteAlueDto> tavoitealueet = valitutTavoitealueet(opintokokonaisuus);
        if (!CollectionUtils.isEmpty(tavoitealueet)) {
            addTavoitealueetTaulukko(docBase, tavoitealueet, true);
        }

        if (hasTeksti(docBase, opintokokonaisuus.getOsaamisenArviointi())) {
            addTeksti(docBase, messages.translate("docgen.osaamisen-arviointi", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, opintokokonaisuus.getOsaamisenArviointi(), "div");
        }
    }

    private void addTavoitealueetTaulukko(DokumenttiYlops docBase, List<TavoiteAlueDto> tavoitealueet, boolean naytaValittuOtsikko) {
        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
        taulukko.addOtsikkosarakkeet(
                messages.translate("docgen.tavoitteet.title", docBase.getKieli()),
                messages.translate("docgen.keskeiset-sisaltoalueet.title", docBase.getKieli()));

        boolean ryhmanAlla = false;
        for (TavoiteAlueDto tavoitealue : tavoitealueet) {
            if (tavoitealue == null) {
                continue;
            }
            DokumenttiRivi rivi = new DokumenttiRivi();

            if (TavoiteAlueTyyppi.OTSIKKO.equals(tavoitealue.getTavoiteAlueTyyppi())) {
                rivi.addSarake(tavoitealue.getOtsikko() != null
                        ? getTextString(docBase, tavoitealue.getOtsikko().getNimi())
                        : "");
                rivi.setColspan(2);
                rivi.setTyyppi(DokumenttiRiviTyyppi.SUBHEADER);
                if (naytaValittuOtsikko) {
                    ryhmanAlla = true;
                }
            }

            if (TavoiteAlueTyyppi.TAVOITESISALTOALUE.equals(tavoitealue.getTavoiteAlueTyyppi())) {
                boolean onTavoitteita = !CollectionUtils.isEmpty(tavoitealue.getTavoitteet());
                String otsikko = tavoitealue.getOtsikko() != null
                        ? getTextString(docBase, tavoitealue.getOtsikko().getNimi())
                        : "";
                boolean naytaAlueenOtsikko = naytaValittuOtsikko
                        && StringUtils.hasText(otsikko)
                        && (!ryhmanAlla || !onTavoitteita);

                StringBuilder tavoitteet = new StringBuilder();
                if (naytaAlueenOtsikko) {
                    tavoitteet.append(sisennetty(tagTeksti(otsikko, "div"), ryhmanAlla));
                }
                if (onTavoitteita) {
                    boolean sisennys = naytaValittuOtsikko && (ryhmanAlla || naytaAlueenOtsikko);
                    for (KoodiDto tavoite : tavoitealue.getTavoitteet()) {
                        if (tavoite == null) {
                            continue;
                        }
                        String teksti = tagTeksti(getTextString(docBase, tavoite.getNimi()), "div");
                        tavoitteet.append(sisennetty(teksti, sisennys));
                    }
                }

                StringBuilder sisaltoalueet = new StringBuilder();
                if (!CollectionUtils.isEmpty(tavoitealue.getKeskeisetSisaltoalueet())) {
                    tavoitealue.getKeskeisetSisaltoalueet().stream()
                            .filter(Objects::nonNull)
                            .forEach(sisaltoalue -> sisaltoalueet.append(tagTeksti(getTextString(docBase, sisaltoalue), "div")));
                }

                rivi.addSarake(tavoitteet.toString(), sisaltoalueet.toString());
            }

            taulukko.addRivi(rivi);
        }

        taulukko.addToDokumentti(docBase);
    }

    private String sisennetty(String teksti, boolean sisennys) {
        if (!sisennys) {
            return teksti;
        }
        return "<div style=\"margin-left:12pt\">" + teksti + "</div>";
    }

    private List<TavoiteAlueDto> valitutTavoitealueet(TaiteenOpintokokonaisuusExportDto opintokokonaisuus) {
        List<TavoiteAlueDto> alueet = opintokokonaisuus.getTavoitealueet();
        if (CollectionUtils.isEmpty(alueet) || CollectionUtils.isEmpty(opintokokonaisuus.getTavoitealueValinnat())) {
            return alueet;
        }

        Map<Long, Set<String>> urit = new HashMap<>();
        for (TavoitealueValintaDto valinta : opintokokonaisuus.getTavoitealueValinnat()) {
            if (valinta == null || valinta.getTavoitealueId() == null || !StringUtils.hasText(valinta.getKoodiUri())) {
                continue;
            }
            urit.computeIfAbsent(valinta.getTavoitealueId(), id -> new HashSet<>()).add(valinta.getKoodiUri());
        }

        return alueet.stream()
                .filter(alue -> alue != null && urit.containsKey(alue.getId()))
                .map(alue -> {
                    Set<String> valitut = urit.get(alue.getId());
                    List<KoodiDto> tavoitteet = alue.getTavoitteet() == null
                            ? new ArrayList<>()
                            : alue.getTavoitteet().stream()
                                    .filter(tavoite -> tavoite != null && valitut.contains(tavoite.getUri()))
                                    .collect(Collectors.toList());
                    return TavoiteAlueDto.builder()
                            .id(alue.getId())
                            .tavoiteAlueTyyppi(alue.getTavoiteAlueTyyppi())
                            .otsikko(alue.getOtsikko())
                            .tavoitteet(tavoitteet)
                            .keskeisetSisaltoalueet(alue.getKeskeisetSisaltoalueet() == null
                                    ? new ArrayList<>()
                                    : new ArrayList<>(alue.getKeskeisetSisaltoalueet()))
                            .build();
                })
                .collect(Collectors.toList());
    }

    private void addTaiteenalaSisalto(DokumenttiYlops docBase, KevytTekstiKappaleDto tekstiKappale, String placeholder) {
        if (tekstiKappale == null || !hasTeksti(docBase, tekstiKappale.getTeksti())) {
            return;
        }
        String otsikko = getTextString(docBase, tekstiKappale.getNimi());
        addTeksti(docBase, !ObjectUtils.isEmpty(otsikko) ? otsikko : messages.translate(placeholder, docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, tekstiKappale.getTeksti()), "div");
    }

    private void addVapaatTekstit(DokumenttiYlops docBase, List<KevytTekstiKappaleDto> vapaatTekstit) {
        if (CollectionUtils.isEmpty(vapaatTekstit)) {
            return;
        }
        vapaatTekstit.stream()
                .filter(Objects::nonNull)
                .forEach(vapaaTeksti -> {
                    addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
                    addDiv(docBase, vapaaTeksti.getTeksti());
                });
    }

    private void addPaikallinenTarkennus(DokumenttiYlops docBase, LokalisoituTekstiDto tarkennus) {
        if (hasTeksti(docBase, tarkennus)) {
            addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, tarkennus, "div");
        }
    }

    private void addDiv(DokumenttiYlops docBase, LokalisoituTekstiDto teksti) {
        if (hasTeksti(docBase, teksti)) {
            addTeksti(docBase, getTextString(docBase, teksti), "div");
        }
    }

    private boolean hasTeksti(DokumenttiYlops docBase, LokalisoituTekstiDto teksti) {
        return hasLokalisoituteksti(docBase, teksti) && !ObjectUtils.isEmpty(getTextString(docBase, teksti));
    }

    private LokalisoituTekstiDto vapaanTekstinTarkennus(TaiteenalanVaiheExportDto vaihe, Long perusteenVapaaTekstiId) {
        if (perusteenVapaaTekstiId == null || CollectionUtils.isEmpty(vaihe.getVapaatTekstit())) {
            return null;
        }
        return vaihe.getVapaatTekstit().stream()
                .filter(Objects::nonNull)
                .filter(vapaa -> perusteenVapaaTekstiId.equals(vapaa.getPerusteenVapaaTekstiId()))
                .map(VapaatekstiPaikallinentarkennusDto::getPaikallinenTarkennus)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    private String vaiheNimi(DokumenttiYlops docBase, TaiteenalanVaiheExportDto vaihe) {
        String nimi = getTextString(docBase, vaihe.getNimi());
        if (ObjectUtils.isEmpty(nimi)) {
            nimi = messages.translate("docgen.taiteenala.nimeton-vaihe", docBase.getKieli());
        }
        Integer laajuus = vaihe.getPerusteenVaihe() != null ? vaihe.getPerusteenVaihe().getLaajuus() : null;
        if (laajuus != null) {
            nimi += ", " + laajuus + " " + messages.translate("docgen.laajuus.op", docBase.getKieli());
        }
        return nimi;
    }

    private String opintokokonaisuusNimi(DokumenttiYlops docBase, TaiteenOpintokokonaisuusExportDto opintokokonaisuus) {
        String nimi = getTextString(docBase, opintokokonaisuus.getNimi());
        if (ObjectUtils.isEmpty(nimi)) {
            nimi = messages.translate("docgen.opintokokonaisuus.nimeton-opintokokonaisuus", docBase.getKieli());
        }
        if (opintokokonaisuus.getLaajuus() != null) {
            nimi += ", " + opintokokonaisuus.getLaajuus() + " " + messages.translate("docgen.laajuus.op", docBase.getKieli());
        }
        return nimi;
    }
}
