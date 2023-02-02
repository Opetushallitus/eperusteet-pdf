package fi.vm.sade.eperusteet.pdf.dto.dokumentti;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class DokumenttiTaulukko {

    public static final String TABLE_HEADER_BGCOLOR = "#d4e3f4";
    private String otsikko;
    private ArrayList<DokumenttiRivi> rivit = new ArrayList<>();

    private ArrayList<String> otsikkoSarakkeet = new ArrayList<>();

    public void addOtsikkoSarake(String sarake) {
        otsikkoSarakkeet.add(sarake);
    }

    public void addOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public void addOtsikkosarakkeet(String... sarakkeet) {
        DokumenttiRivi otsikkoRivi = new DokumenttiRivi();
        Arrays.stream(sarakkeet).forEach(otsikkoRivi::addSarake);
        otsikkoRivi.setTyyppi(DokumenttiRiviTyyppi.HEADER);
        addRivi(otsikkoRivi);
    }

    public void addRivi(DokumenttiRivi rivi) {
        rivit.add(rivi);
    }

    public void addToDokumentti(DokumenttiBase docBase) {
        if (rivit.size() > 0) {
            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(this.toString()));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();
            docBase.getBodyElement().appendChild(docBase.getDocument().importNode(node, true));
        }
    }

    public void addToDokumentti(AmosaaDokumenttiBase docBase) {
        if (rivit.size() > 0) {
            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(this.toString()));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();
            docBase.getBodyElement().appendChild(docBase.getDocument().importNode(node, true));
        }
    }

    //Amosaan käyttämä
    public static void addRow(AmosaaDokumenttiBase docBase, Element taulukko, String teksti, boolean header) {
        Element tr = docBase.getDocument().createElement("tr");
        taulukko.appendChild(tr);
        if (header) {
            tr.setAttribute("bgcolor", TABLE_HEADER_BGCOLOR);
        }

        if (header) {
            Element th = docBase.getDocument().createElement("th");
            th.appendChild(newBoldElement(docBase.getDocument(), teksti));
            tr.appendChild(th);
        } else {
            Element td = docBase.getDocument().createElement("td");
            td.appendChild(newBoldElement(docBase.getDocument(), teksti));
            tr.appendChild(td);
        }
    }

    public static Element newBoldElement(Document doc, String teksti) {
        Element strong = doc.createElement("strong");
        strong.appendChild(doc.createTextNode(teksti));
        return strong;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<div>");

        if (otsikko != null) {
            builder.append("<strong>");
            builder.append(otsikko);
            builder.append("</strong>");
        }

        builder.append("<table border=\"1\">");

        rivit.forEach((rivi) -> {
            builder.append(String.format("<tr bgcolor=\"%s\" fontcolor=\"%s\">", rivi.getBackgroundColor(), rivi.getFontColor()));
            builder.append(rivi.toString());
            builder.append("</tr>");
        });

        builder.append("</table>");

        builder.append("</div>");
        return builder.toString();
    }
}
