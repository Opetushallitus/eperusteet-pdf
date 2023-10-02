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
import org.jsoup.parser.Parser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DokumenttiUtils {
    public static String selectSubjectTranslationKey(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return "docgen.meta.subject.peruste";
        } else if (tyyppi.equals(DokumenttiTyyppi.YLOPS) || tyyppi.equals(DokumenttiTyyppi.AMOSAA)) {
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

    public static void addLokalisoituteksti(DokumenttiBase docBase, LokalisoituTekstiDto lokalisoituTekstiDto, String tagi, Element el) {
        if (getKielistettyTeksti(lokalisoituTekstiDto, docBase.getKieli()) != null) {
            String teksti = getKielistettyTeksti(lokalisoituTekstiDto, docBase.getKieli());
            teksti = "<" + tagi + ">" + cleanHtml(teksti) + "</" + tagi + ">";

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
            teksti = cleanHtml(teksti);
            teksti = "<" + tagi + ">" + teksti + "</" + tagi + ">";

            Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(teksti));
            Node node = tempDoc.getDocumentElement().getChildNodes().item(1).getFirstChild();

            element.appendChild(doc.importNode(node, true));
        }
    }

    public static String tagTeksti(String teksti, String tagi) {
        if (teksti != null) {

            teksti = cleanHtml(teksti);
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
        return getTextString(docBase.getKieli(), lokalisoituTekstiDto);
    }

    public static String getTextString(Kieli kieli, LokalisoituTekstiDto lokalisoituTekstiDto) {
        if (getKielistettyTeksti(lokalisoituTekstiDto, kieli) != null) {
            return cleanHtml(getKielistettyTeksti(lokalisoituTekstiDto, kieli));
        } else {
            return "";
        }
    }

    private static String getKielistettyTeksti(LokalisoituTekstiDto lokalisoituTekstiDto, Kieli kieli) {
        if (lokalisoituTekstiDto == null || kieli == null || lokalisoituTekstiDto.getTekstit() == null) {
            return null;
        }

        if (lokalisoituTekstiDto.getTekstit().containsKey(kieli)) {
            return lokalisoituTekstiDto.getTekstit().get(kieli);
        }

        Optional<Kieli> vaihtoehtoinenKieli = Arrays.stream(Kieli.values())
                .filter(vaihtoehtoKieli -> lokalisoituTekstiDto.getTekstit().containsKey(vaihtoehtoKieli) && lokalisoituTekstiDto.getTekstit().get(vaihtoehtoKieli) != null).findFirst();
        return vaihtoehtoinenKieli.map(value -> "[" + lokalisoituTekstiDto.getTekstit().get(value) + "]").orElse(null);
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

    private static String removeInternalLink(String text) {
        org.jsoup.nodes.Document stringRoutenodeCleaned = Jsoup.parse(text, "", Parser.xmlParser());
        stringRoutenodeCleaned.select("a[routenode]").forEach(org.jsoup.nodes.Node::unwrap);
        return stringRoutenodeCleaned.toString();
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

        Document tempDoc = new W3CDom().fromJsoup(Jsoup.parseBodyFragment(cleanHtml(teksti)));
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
        string = removeInternalLink(string);
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
