package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.OsaamistasoDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiKVLiite;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteTasoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Service
public class KVLiiteBuilderServiceImpl implements KVLiiteBuilderService {

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private EperusteetService eperusteetService;

    @Value("${docgenPath: ''}")
    private String docgenPath;

    @Override
    public Document generateXML(PerusteKaikkiDto peruste, Kieli kieli) throws ParserConfigurationException, JsonProcessingException {
        // Luodaan uusi dokumentti
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Luodaan XHTML pohja
        Element rootElement = doc.createElement("html");
        rootElement.setAttribute("lang", kieli.toString());
        doc.appendChild(rootElement);

        // Head-elementti
        Element headElement = doc.createElement("head");
        rootElement.appendChild(headElement);

        // Poistetaan HEAD:in <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        if (headElement.hasChildNodes()) {
            headElement.removeChild(headElement.getFirstChild());
        }

        // Body-elementti
        Element bodyElement = doc.createElement("body");
        rootElement.appendChild(bodyElement);

        // Apuolio dataan siirtelyyn
        DokumenttiKVLiite docBase = new DokumenttiKVLiite();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setKieli(kieli);
        docBase.setPeruste(peruste);
        docBase.setKvLiiteJulkinenDto(eperusteetService.getKvLiite(peruste.getId()));

        // Rakennetaan varsinainen dokumentti
        addKVLiite(docBase);

        return doc;
    }

    private void addKVLiite(DokumenttiKVLiite docBase) {
        addHeader(docBase);
        addTutkintoNimiSuomeksi(docBase);
        addTutkinnonNimiDokumentinKielella(docBase);
        addAmmatillinenOsaaminen(docBase);
        addVirallisuus(docBase);
        addTutkintotodistuksenSaanti(docBase);
        addSelventavaHuomautus(docBase);
    }

    private void addHeader(DokumenttiKVLiite docBase) {
        // Hieman lisää tilaa
        Element p = docBase.getDocument().createElement("p");
        docBase.getBodyElement().appendChild(p);

        // Lisätään taulukko
        Element table = docBase.getDocument().createElement("table");
        table.setAttribute("border", "0");
        docBase.getBodyElement().appendChild(table);
        Element tr = docBase.getDocument().createElement("tr");
        table.appendChild(tr);

        // Lisätään Europass kuva
        Element europassTd = docBase.getDocument().createElement("td");
        tr.appendChild(europassTd);
        europassTd.setAttribute("colspan", "2");
        europassTd.setAttribute("align", "left");
        Element europassImg = docBase.getDocument().createElement("img");
        europassTd.appendChild(europassImg);
        europassImg.setAttribute("src", docgenPath + "kvliite/europass.jpg");

        // Lisätään teksti
        Element titleTd = docBase.getDocument().createElement("td");
        tr.appendChild(titleTd);
        Element titleHeader = docBase.getDocument().createElement("h1");
        titleTd.appendChild(titleHeader);
        titleTd.setAttribute("colspan", "6");
        titleTd.setAttribute("align", "center");
        titleHeader.setTextContent(messages.translate("docgen.kvliite.nimi", docBase.getKieli()).toUpperCase());

        // Lisätään Suomen lippu
        Element flagTd = docBase.getDocument().createElement("td");
        tr.appendChild(flagTd);
        flagTd.setAttribute("colspan", "2");
        flagTd.setAttribute("align", "right");
        Element flagImg = docBase.getDocument().createElement("img");
        flagTd.appendChild(flagImg);
        flagImg.setAttribute("src", docgenPath + "kvliite/fi.jpg");
    }

    private void addTutkintoNimiSuomeksi(DokumenttiKVLiite docBase) {
        // Lisätään taulukko
        Element table = docBase.getDocument().createElement("table");
        docBase.getBodyElement().appendChild(table);
        table.setAttribute("border", "1");

        // Lisätään tutkinnon nimi
        Element tr = docBase.getDocument().createElement("tr");
        table.appendChild(tr);
        tr.setAttribute("bgcolor", "#F2F2F2");
        Element th = docBase.getDocument().createElement("th");
        tr.appendChild(th);
        th.appendChild(DokumenttiUtils.newBoldElement(
                docBase.getDocument(), messages.translate("docgen.kvliite.tutkinnon-nimi", docBase.getKieli())));

        // Lisätään tutkinnon tiedot
        Element tr2 = docBase.getDocument().createElement("tr");
        table.appendChild(tr2);
        Element td = docBase.getDocument().createElement("td");
        tr2.appendChild(td);
        td.setAttribute("align", "center");

        // Nimi
        LokalisoituTekstiDto nimi = docBase.getKvLiiteJulkinenDto().getNimi();
        if (nimi != null && nimi.getTekstit() != null) {
            Element p = docBase.getDocument().createElement("p");
            td.appendChild(p);
            if (nimi.getTekstit().containsKey(Kieli.FI)) {
                Element perusteNimiEl = docBase.getDocument().createElement("strong");
                p.appendChild(perusteNimiEl);
                perusteNimiEl.appendChild(DokumenttiUtils.newItalicElement(docBase, nimi.get(Kieli.FI)));
                Element br = docBase.getDocument().createElement("br");
                p.appendChild(br);
            }

            if (nimi.getTekstit().containsKey(Kieli.SV)) {
                p.appendChild(DokumenttiUtils.newItalicElement(docBase,
                        nimi.get(Kieli.SV)));
            }
        }

        // Voimaantulopaiva ja diaari
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();
        StringBuilder voimaantulopaivaJaDiaari = new StringBuilder();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        if (kvLiiteJulkinenDto.getVoimassaoloAlkaa() == null || kvLiiteJulkinenDto.getDiaarinumero() == null) {
            throw new NotImplementedException("Voimaantulopäivää ja/tai diaarinumeroa ei ole annettu");
        }

        voimaantulopaivaJaDiaari.append(messages.translate("docgen.kvliite.tutkinnon-perusteiden-voimaantulopaiva", docBase.getKieli()));
        voimaantulopaivaJaDiaari.append(" ");
        voimaantulopaivaJaDiaari.append(dateFormat.format(kvLiiteJulkinenDto.getVoimassaoloAlkaa()));
        voimaantulopaivaJaDiaari.append(" ");

        voimaantulopaivaJaDiaari.append("(");
        voimaantulopaivaJaDiaari.append(kvLiiteJulkinenDto.getDiaarinumero());
        voimaantulopaivaJaDiaari.append(")");

        td.appendChild(DokumenttiUtils.newItalicElement(docBase, voimaantulopaivaJaDiaari.toString()));
    }

    private void addTutkinnonNimiDokumentinKielella(DokumenttiKVLiite docBase) {
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();

        // Jos dokumentin kieli ei ole suomi ja dokumentin kielellä löytyy nimi
        if (docBase.getKieli().equals(Kieli.EN)
                && kvLiiteJulkinenDto.getNimi() != null
                && kvLiiteJulkinenDto.getNimi().getTekstit() != null
                && kvLiiteJulkinenDto.getNimi().getTekstit().containsKey(docBase.getKieli())) {

            // Lisätään taulukko
            Element table = docBase.getDocument().createElement("table");
            docBase.getBodyElement().appendChild(table);
            table.setAttribute("border", "1");

            // Lisätään tutkinnon nimi
            {
                Element tr = docBase.getDocument().createElement("tr");
                table.appendChild(tr);
                tr.setAttribute("bgcolor", "#F2F2F2");
                Element th = docBase.getDocument().createElement("th");
                tr.appendChild(th);
                th.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                        messages.translate("docgen.kvliite.tutkinnon-kaannetty-nimi", docBase.getKieli())));
            }

            // Lisätään tutkinnon nimelle rivi
            {
                Element tr = docBase.getDocument().createElement("tr");
                table.appendChild(tr);
                Element td = docBase.getDocument().createElement("td");
                tr.appendChild(td);
                td.setAttribute("align", "center");

                Element br = docBase.getDocument().createElement("br");
                td.appendChild(br);

                // Lisätään tutkinnon nimi dokumentin kielellä
                Element p = docBase.getDocument().createElement("p");
                td.appendChild(p);
                Element perusteNimiEl = docBase.getDocument().createElement("strong");
                p.appendChild(perusteNimiEl);
                perusteNimiEl.appendChild(DokumenttiUtils.newItalicElement(docBase,
                        kvLiiteJulkinenDto.getNimi().get(docBase.getKieli())));

                td.appendChild(br);
            }

            // Lisätään lailisuuskohta
            {
                Element tr = docBase.getDocument().createElement("tr");
                table.appendChild(tr);
                Element td = docBase.getDocument().createElement("td");
                tr.appendChild(td);
                td.setAttribute("align", "center");

                Element p = docBase.getDocument().createElement("p");
                td.appendChild(p);
                Element small = docBase.getDocument().createElement("small");
                p.appendChild(small);
                small.setTextContent(messages.translate("docgen.kvliite.kaanos-ei-ole-lainvoimainen", docBase.getKieli()));
            }
        }
    }

    private void addAmmatillinenOsaaminen(DokumenttiKVLiite docBase) {
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();

        // Lisätään taulukko
        Element table = docBase.getDocument().createElement("table");
        docBase.getBodyElement().appendChild(table);
        table.setAttribute("border", "1");

        // Lisätään ammatillinen osaamisen otsikko
        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            tr.setAttribute("bgcolor", "#F2F2F2");
            Element th = docBase.getDocument().createElement("th");
            tr.appendChild(th);
            th.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.ammatillinen-osaaminen", docBase.getKieli())));
        }

        // Lisätään tutkinnon nimelle rivi
        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element td = docBase.getDocument().createElement("td");
            tr.appendChild(td);

            // Muodostuminen
            if (kvLiiteJulkinenDto.getMuodostumisenKuvaus() != null) {

                kvLiiteJulkinenDto.getMuodostumisenKuvaus().forEach((suoritustapakoodi, lokalisoituTekstiDto) -> {
                    StringBuilder otsikkoBuilder = new StringBuilder();
                    otsikkoBuilder.append(messages.translate("docgen.tutkinnon_muodostuminen.title",
                            docBase.getKieli()));

                    if (kvLiiteJulkinenDto.getMuodostumisenKuvaus().size() > 1) {
                        otsikkoBuilder.append(" (");
                        otsikkoBuilder.append(
                                messages.translate("docgen.tutkinnon_muodostuminen.title.suoritustapa."
                                        + suoritustapakoodi.toString(), docBase.getKieli()));
                        otsikkoBuilder.append(")");
                    }
                    Element p = docBase.getDocument().createElement("p");
                    td.appendChild(p);
                    p.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(), otsikkoBuilder.toString()));

                    DokumenttiUtils.addTeksti(docBase,
                            DokumenttiUtils.getTextString(docBase, lokalisoituTekstiDto), "div", td);
                });
            }

            // Suorittaneen osaaminen
            {
                LokalisoituTekstiDto suorittaneenOsaaminen = kvLiiteJulkinenDto.getSuorittaneenOsaaminen();
                if (suorittaneenOsaaminen != null && suorittaneenOsaaminen.getTekstit().containsKey(docBase.getKieli())) {

                Element p = docBase.getDocument().createElement("p");
                td.appendChild(p);
                p.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                        messages.translate("docgen.kvliite.tutkinnon-suorittaneen-osaaminen", docBase.getKieli())));

                DokumenttiUtils.addTeksti(docBase,
                        DokumenttiUtils.getTextString(docBase, suorittaneenOsaaminen), "div", td);
                }
            }
        }
    }

    private void addVirallisuus(DokumenttiKVLiite docBase) {
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();

        // Lisätään taulukko
        Element table = docBase.getDocument().createElement("table");
        docBase.getBodyElement().appendChild(table);
        table.setAttribute("border", "1");

        // Tyotehtävät
        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            tr.setAttribute("bgcolor", "#F2F2F2");
            Element th = docBase.getDocument().createElement("th");
            th.setAttribute("colspan", "2");
            tr.appendChild(th);
            th.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.tyotehtavat", docBase.getKieli())));
        }

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element td = docBase.getDocument().createElement("td");
            tr.appendChild(td);
            td.setAttribute("colspan", "2");

            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase,
                            kvLiiteJulkinenDto.getTyotehtavatJoissaVoiToimia()), "div", td);
        }

        // Virallinen asema
        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            tr.setAttribute("bgcolor", "#F2F2F2");
            Element th = docBase.getDocument().createElement("th");
            th.setAttribute("colspan", "2");
            tr.appendChild(th);
            th.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.virallinen-asema", docBase.getKieli())));
        }

        addVirallinenAsema(docBase, table);

        // Säädösperusta
        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element td = docBase.getDocument().createElement("td");
            tr.appendChild(td);
            td.setAttribute("colspan", "2");

            td.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.saadosperusta", docBase.getKieli())));

            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase,
                            kvLiiteJulkinenDto.getSaadosPerusta()), "div", td);
        }
    }

    private void addVirallinenAsema(DokumenttiKVLiite docBase, Element table) {
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element leftTd = docBase.getDocument().createElement("td");
            tr.appendChild(leftTd);
            Element rightTd = docBase.getDocument().createElement("td");
            tr.appendChild(rightTd);

            LokalisoituTekstiDto tutkintotodistuksenAntaja = kvLiiteJulkinenDto.getTutkintotodistuksenAntaja();
            if (tutkintotodistuksenAntaja != null) {
                leftTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                        messages.translate("docgen.kvliite.antajan-nimi-ja-asema", docBase.getKieli())));

                DokumenttiUtils.addTeksti(docBase,
                        DokumenttiUtils.getTextString(docBase,tutkintotodistuksenAntaja), "div", leftTd);
            }

            LokalisoituTekstiDto tutkinnostaPaattavaViranomainen= kvLiiteJulkinenDto.getTutkinnostaPaattavaViranomainen();
            if (tutkinnostaPaattavaViranomainen != null) {
                rightTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                        messages.translate("docgen.kvliite.paattavan-nimi", docBase.getKieli())));

                DokumenttiUtils.addTeksti(docBase,
                        DokumenttiUtils.getTextString(docBase, tutkinnostaPaattavaViranomainen), "div", rightTd);
            }
        }

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element leftTd = docBase.getDocument().createElement("td");
            tr.appendChild(leftTd);
            Element rightTd = docBase.getDocument().createElement("td");
            tr.appendChild(rightTd);

            leftTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.taso", docBase.getKieli())));
            if (kvLiiteJulkinenDto.getTasot() != null) {
                kvLiiteJulkinenDto.getTasot().stream()
                        .sorted(Comparator.comparingInt(KVLiiteTasoDto::getJarjestys))
                        .forEach(taso -> {
                            if (taso.getNimi() != null && taso.getNimi().get(docBase.getKieli()) != null) {
                                DokumenttiUtils.addTeksti(docBase, taso.getNimi().get(docBase.getKieli()), "span", leftTd);
                            }
                        });
            }

            rightTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.arviointi-asteikko", docBase.getKieli())));
            if (kvLiiteJulkinenDto.getArvosanaAsteikko() != null) {
                ArviointiAsteikkoDto arviointiAsteikkoDto = eperusteetService.getArviointiasteikko(kvLiiteJulkinenDto.getArvosanaAsteikko().getIdLong());
                StringJoiner joiner = new StringJoiner(" / ");
                List<OsaamistasoDto> osaamistasot = arviointiAsteikkoDto.getOsaamistasot();
                // EP-1315
                if (osaamistasot.size() == 1) {
                    joiner.add(messages.translate("docgen.kvliite.kvliiteen-yksiportainen-arviointiasteikko", docBase.getKieli()));
                } else {
                    osaamistasot.forEach(osaamistasoDto -> joiner
                            .add(DokumenttiUtils.getTextString(docBase, osaamistasoDto.getOtsikko())));
                }
                DokumenttiUtils.addTeksti(docBase, joiner.toString(), "div", rightTd);
            }
        }

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element leftTd = docBase.getDocument().createElement("td");
            tr.appendChild(leftTd);
            Element rightTd = docBase.getDocument().createElement("td");
            tr.appendChild(rightTd);

            leftTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.jatko-opintokelpoisuus", docBase.getKieli())));
            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase, kvLiiteJulkinenDto.getJatkoopintoKelpoisuus()),
                    "div",
                    leftTd);

            rightTd.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.kv-sopimukset", docBase.getKieli())));
            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase, kvLiiteJulkinenDto.getKansainvalisetSopimukset()),
                    "div",
                    rightTd);
        }
    }

    private void addTutkintotodistuksenSaanti(DokumenttiKVLiite docBase) {
        KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();

        // Lisätään taulukko
        Element table = docBase.getDocument().createElement("table");
        docBase.getBodyElement().appendChild(table);
        table.setAttribute("border", "1");

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            tr.setAttribute("bgcolor", "#F2F2F2");
            Element th = docBase.getDocument().createElement("th");
            tr.appendChild(th);
            th.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.tutkintotodistuksen-saanti", docBase.getKieli())));
        }

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element td = docBase.getDocument().createElement("td");
            tr.appendChild(td);

            // Tutkintotodistuksen saaminen
            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase,
                            kvLiiteJulkinenDto.getTutkintotodistuksenSaaminen()), "div", td);
        }

        {
            Element tr = docBase.getDocument().createElement("tr");
            table.appendChild(tr);
            Element td = docBase.getDocument().createElement("td");
            tr.appendChild(td);

            // Pohjakoulutusvaatimukset
            td.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.pohjakoulutusvaatimukset", docBase.getKieli())));
            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase,
                            kvLiiteJulkinenDto.getPohjakoulutusvaatimukset()), "div", td);

            // Lisätietoja
            td.appendChild(DokumenttiUtils.newBoldElement(docBase.getDocument(),
                    messages.translate("docgen.kvliite.lisatietoja", docBase.getKieli())));
            DokumenttiUtils.addTeksti(docBase,
                    DokumenttiUtils.getTextString(docBase,
                            kvLiiteJulkinenDto.getLisatietoja()), "div", td);
        }
    }

    private void addSelventavaHuomautus(DokumenttiKVLiite docBase) {
        // Selventävä huomautus
        {
            Element article = docBase.getDocument().createElement("article");

            // Otsikko
            {
                Element h6 = docBase.getDocument().createElement("h6");
                Element otsikkoEl = docBase.getDocument().createElement("strong");
                otsikkoEl.setTextContent(messages.translate("docgen.kvliite.selventava-huomautus-otsikko", docBase.getKieli()));
                h6.appendChild(otsikkoEl);
                article.appendChild(h6);
            }

            // Teksti
            {
                Element p = docBase.getDocument().createElement("p");
                p.setTextContent(messages.translate("docgen.kvliite.selventava-huomautus-teksti", docBase.getKieli()));
                article.appendChild(p);
            }

            // Lisätietoa
            {
                Element p = docBase.getDocument().createElement("p");
                p.setTextContent(messages.translate("docgen.kvliite.selventava-huomautus-lisatietoja", docBase.getKieli()) + " ");

                Element a = docBase.getDocument().createElement("a");
                String linkki = messages.translate("docgen.kvliite.selventava-huomautus-lisatietoja-linkki", docBase.getKieli());
                a.setTextContent(linkki);
                a.setAttribute("href", linkki);
                p.appendChild(a);
                article.appendChild(p);
            }

            // Allekirjoitus
            {
                Element p = docBase.getDocument().createElement("p");
                p.setTextContent(messages.translate("docgen.kvliite.selventava-huomautus-allekirjoitus", docBase.getKieli()));
                article.appendChild(p);
            }

            docBase.getBodyElement().appendChild(article);
        }
    }
}
