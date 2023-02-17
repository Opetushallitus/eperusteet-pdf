package fi.vm.sade.eperusteet.pdf.utils;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiAmosaa;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class DokumenttiUtils {
    public static String selectSubjectTranslationKey(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return "docgen.meta.subject.peruste";
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS) || tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return "docgen.meta.subject.ops";
        } else {
            return "docgen.meta.subject.kvliite";
        }
    }

    public static boolean hasLokalisoituteksti(DokumenttiBase docBase, LokalisoituTekstiDto lTeksti) {
        return lTeksti != null && lTeksti.getTekstit() != null && lTeksti.getTekstit().get(docBase.getKieli()) != null;
    }

    public static void addLokalisoituteksti(DokumenttiBase docBase, LokalisoituTekstiDto lTeksti, String tagi) {
        addLokalisoituteksti(docBase, lTeksti, tagi, null);
    }

    public static void addLokalisoituteksti(DokumenttiBase docBase, LokalisoituTekstiDto lTeksti, String tagi, Element el) {
        if (lTeksti != null && lTeksti.getTekstit() != null && lTeksti.getTekstit().get(docBase.getKieli()) != null) {
            String teksti = lTeksti.getTekstit().get(docBase.getKieli());
            teksti = "<" + tagi + ">" + unescapeHtml5(teksti) + "</" + tagi + ">";

            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(teksti));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

            if (el != null) {
                el.appendChild(docBase.getDocument().importNode(node, true));
            } else {
                docBase.getBodyElement().appendChild(docBase.getDocument().importNode(node, true));
            }
        }
    }

    public static void addTeksti(DokumenttiBase docBase, String teksti, String tagi) {
        addTeksti(docBase.getDocument(), teksti, tagi, docBase.getBodyElement());
    }

    public static void addTeksti(DokumenttiAmosaa docBase, String teksti, String tagi) {
        addTeksti(docBase.getDocument(), teksti, tagi, docBase.getBodyElement());
    }

    public static void addTeksti(DokumenttiBase docBase, String teksti, String tagi, Element element) {
        addTeksti(docBase.getDocument(), teksti, tagi, element);
    }

    public static void addTeksti(DokumenttiAmosaa docBase, String teksti, String tagi, Element element) {
        addTeksti(docBase.getDocument(), teksti, tagi, element);
    }

    public static void addTeksti(Document doc, String teksti, String tagi, Element element) {
        if (teksti != null) {
            teksti = unescapeHtml5(teksti);
            teksti = "<" + tagi + ">" + teksti + "</" + tagi + ">";

            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(teksti));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

            element.appendChild(doc.importNode(node, true));
        }
    }

    public static String tagTeksti(String teksti, String tagi) {
        if (teksti != null) {

            teksti = unescapeHtml5(teksti);
            return "<" + tagi + ">" + teksti + "</" + tagi + ">";
        }
        return "<" + tagi + "></" + tagi + ">";
    }

    public static void addHeader(DokumenttiBase docBase, String text) {
        addHeader(docBase, text, null);
    }

    public static void addHeader(DokumenttiBase docBase, String text, String id) {
        addHeader(docBase, text, id, true);
    }

    public static void addHeader(DokumenttiBase docBase, String text, boolean showHeaderNumber) {
        addHeader(docBase, text, null, showHeaderNumber);
    }

    public static void addHeader(DokumenttiBase docBase, String text, String id, boolean showHeaderNumber) {
        if (text != null) {
            Element header = docBase.getDocument().createElement("h" + docBase.getGenerator().getDepth());
            header.setAttribute("number", docBase.getGenerator().generateNumber());
            header.setAttribute("showHeaderNumber", showHeaderNumber + "");
            header.appendChild(docBase.getDocument().createTextNode(cleanHtml(text)));
            if (id != null) {
                header.setAttribute("id", id);
            }
            docBase.getBodyElement().appendChild(header);
        }
    }

    public static String getTextString(DokumenttiBase docBase, LokalisoituTekstiDto lokalisoituTekstiDto) {
        return getString(lokalisoituTekstiDto, docBase.getKieli());
    }

    public static String getTextString(DokumenttiAmosaa docBase, LokalisoituTekstiDto lokalisoituTekstiDto) {
        return getString(lokalisoituTekstiDto, docBase.getKieli());
    }

    public static String getTextString(DokumenttiYlops docBase, LokalisoituTekstiDto lokalisoituTekstiDto) {
        return getString(lokalisoituTekstiDto, docBase.getKieli());
    }

    private static String getString(LokalisoituTekstiDto lokalisoituTekstiDto, Kieli kieli) {
        if (lokalisoituTekstiDto != null && lokalisoituTekstiDto.getTekstit() != null && kieli != null
                && lokalisoituTekstiDto.getTekstit().containsKey(kieli)
                && lokalisoituTekstiDto.getTekstit().get(kieli) != null) {
            return unescapeHtml5(lokalisoituTekstiDto.getTekstit().get(kieli));
        } else {
            return "";
        }
    }

    public static String getTextString(Kieli kieli, LokalisoituTekstiDto lokalisoituTekstiDto) {
        if (lokalisoituTekstiDto != null && lokalisoituTekstiDto.getTekstit() != null && kieli != null
                && lokalisoituTekstiDto.getTekstit().containsKey(kieli)
                && lokalisoituTekstiDto.getTekstit().get(kieli) != null) {
            return unescapeHtml5(lokalisoituTekstiDto.getTekstit().get(kieli));
        } else {
            return "";
        }
    }

    public static void addList(DokumenttiBase docBase, Collection<LokalisoituTekstiDto> tekstit) {
        addStringList(docBase, tekstit.stream()
                .filter(Objects::nonNull)
                .map(kuvaus -> getTextString(docBase, kuvaus))
                .collect(Collectors.toList()));
    }

    public static void addStringList(DokumenttiBase docBase, Collection<String> tekstit) {
        Element ul = docBase.getDocument().createElement("ul");
        tekstit.stream()
                .filter(str -> !StringUtils.isEmpty(str))
                .forEach(str -> {
                    Element li = docBase.getDocument().createElement("li");
                    Document doc = new W3CDom().fromJsoup(Jsoup.parse(str));
                    Node node = doc.getDocumentElement().getChildNodes().item(1).getFirstChild();
                    li.appendChild(docBase.getDocument().importNode(node, true));
                    ul.appendChild(li);
                });
        docBase.getBodyElement().appendChild(ul);
    }

    public static void addPlaceholder(DokumenttiBase docBase) {
        docBase.getBodyElement().appendChild(docBase.getDocument().createElement("br"));
    }

    public static String unescapeHtml5(String string) {
        return StringEscapeUtils.unescapeHtml4((Jsoup.clean(stripNonValidXMLCharacters(string), ValidHtml.WhitelistType.NORMAL_PDF.getWhitelist())));
    }

    public static String stripNonValidXMLCharacters(String in) {
        StringBuilder out = new StringBuilder();
        char current;

        if (in == null || ("".equals(in))) return "";
        for (int i = 0; i < in.length(); i++) {
            current = in.charAt(i);
            if (current == 0x9
                    || current == 0xA
                    || current == 0xD
                    || current >= 0x20 && current <= 0xD7FF
                    || current >= 0xE000 && current <= 0xFFFD) {
                out.append(current);
            }
        }

        return out.toString();
    }

    public static Element newBoldElement(Document doc, String teksti) {
        Element strong = doc.createElement("strong");
        strong.appendChild(doc.createTextNode(teksti));
        return strong;
    }

    public static Element newItalicElement(DokumenttiBase docBase, String teksti) {
        Element emphasis = docBase.getDocument().createElement("em");

        Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(unescapeHtml5(teksti)));
        Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

        emphasis.appendChild(docBase.getDocument().importNode(node, true));
        return emphasis;
    }

    public static String selectLaajuusYksikkoMessage(LaajuusYksikko laajuusYksikko) {
        switch (laajuusYksikko) {
            case OPINTOVIIKKO:
                return "docgen.laajuus.ov";
            case OPINTOPISTE:
                return "docgen.laajuus.op";
            case OSAAMISPISTE:
                return "docgen.laajuus.osp";
            case KURSSI:
                return "docgen.laajuus.kurssi";
            case TUNTI:
                return "docgen.laajuus.tunti";
            case VIIKKO:
                return "docgen.laajuus.viikkoa";
            case VUOSIVIIKKOTUNTI:
                return "docgen.laajuus.vuosiviikkotunti";
            case VUOSI:
                return "docgen.laajuus.vuosi";
            default:
                return "docgen.laajuus.op"; // palautetaan 'op', joka oli default ennen laajuusyksikön tuloa
        }
    }

    public static void addTekstiosa(DokumenttiBase docBase, TekstiosaDto tekstiosa, String tagi) {
        if (tekstiosa != null) {
            LokalisoituTekstiDto otsikko = tekstiosa.getOtsikko();
            LokalisoituTekstiDto teksti = tekstiosa.getTeksti();
            if (otsikko != null) {
                addLokalisoituteksti(docBase, otsikko, tagi);
            }
            if (teksti != null) {
                addLokalisoituteksti(docBase, teksti, tagi);
            }
        }
    }

    public static String cleanHtml(String string) {
        if (string == null) {
            return "";
        }
        String cleanXmlString = Jsoup.clean(stripNonValidXMLCharacters(string), ValidHtml.WhitelistType.NORMAL_PDF.getWhitelist());
        return StringEscapeUtils.unescapeHtml4(cleanXmlString.replace("&nbsp;", " "));
    }

    public static Element getList(DokumenttiBase docBase, Collection<LokalisoituTekstiDto> tekstit) {
        return getStringList(docBase, tekstit.stream()
                .filter(Objects::nonNull)
                .map(kuvaus -> getTextString(docBase, kuvaus))
                .collect(Collectors.toList()));
    }

    public static Element getStringList(DokumenttiBase docBase, Collection<String> tekstit) {
        Element ul = docBase.getDocument().createElement("ul");
        tekstit.stream()
                .filter(str -> !StringUtils.isEmpty(str))
                .forEach(str -> {
                    Element li = docBase.getDocument().createElement("li");
                    Document doc = new W3CDom().fromJsoup(Jsoup.parse(str));
                    Node node = doc.getDocumentElement().getChildNodes().item(1).getFirstChild();
                    li.appendChild(docBase.getDocument().importNode(node, true));
                    ul.appendChild(li);
                });
        return ul;
    }

}
