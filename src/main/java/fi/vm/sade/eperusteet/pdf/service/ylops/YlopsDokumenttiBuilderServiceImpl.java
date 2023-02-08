package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.KoodistoMetadataDto;
import fi.vm.sade.eperusteet.pdf.domain.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import fi.vm.sade.eperusteet.pdf.service.external.CommonExternalService;
import fi.vm.sade.eperusteet.pdf.service.external.KoodistoClientImpl;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import fi.vm.sade.eperusteet.pdf.utils.LocalizedMessagesService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;

@Slf4j
@Profile("!test")
@Service
public class YlopsDokumenttiBuilderServiceImpl implements YlopsDokumenttiBuilderService {

    @Autowired
    private KoodistoClientImpl koodistoClient;

//    @Autowired
//    private PerusopetusService perusopetusService;

//    @Autowired
//    private LukioService lukioService;

//    @Autowired
//    private Lops2019DokumenttiService lops2019DokumenttiService;

//    @Autowired
//    private YleisetOsuudetService yleisetOsuudetService;

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private YlopsService ylopsService;

    @Autowired
    private DokumenttiUtilService dokumenttiUtilService;

    @Autowired
    private CommonExternalService commonExternalService;

    @Override
    public Document generateXML(Dokumentti dokumentti, OpetussuunnitelmaExportDto ops) throws ParserConfigurationException, NullPointerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Luodaan XHTML pohja
        Element rootElement = doc.createElement("html");
        rootElement.setAttribute("lang", dokumentti.getKieli().toString());
        doc.appendChild(rootElement);

        Element headElement = doc.createElement("head");

        // Poistetaan HEAD:in <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
        if (headElement.hasChildNodes()) {
            headElement.removeChild(headElement.getFirstChild());
        }

        Element bodyElement = doc.createElement("body");

        rootElement.appendChild(headElement);
        rootElement.appendChild(bodyElement);

        // Apuluokka datan säilömiseen generoinin ajaksi
        DokumenttiYlops docBase = new DokumenttiYlops();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setKieli(dokumentti.getKieli());
        docBase.setDokumentti(dokumentti);
        docBase.setOps(ops);

        // Kansilehti & Infosivu
        addMetaPages(docBase);

        // Sisältöelementit
//        yleisetOsuudetService.addYleisetOsuudet(docBase);

        if (ops.getKoulutustyyppi() != null) {
//            PerusteDto perusteDto = eperusteetService.getPeruste(ops.getPerusteenDiaarinumero());
//            if (perusteDto == null) {
//                throw new DokumenttiException("Peruste puuttuu", new Throwable());
//            }
//            docBase.setPerusteDto(perusteDto);

            // Perusopetus
            if (KoulutusTyyppi.PERUSOPETUS.equals(ops.getKoulutustyyppi())) {
//                perusopetusService.addVuosiluokkakokonaisuudet(docBase);
            }

            // Lukio
            if (KoulutustyyppiToteutus.LOPS2019.equals(ops.getToteutus())) {
//                lops2019DokumenttiService.addLops2019Sisalto(docBase);
            }
            else if (KoulutusTyyppi.LUKIOKOULUTUS.equals(ops.getKoulutustyyppi())) {
//                lukioService.addOppimistavoitteetJaOpetuksenKeskeisetSisallot(docBase);
            }
        }

        // Liitteet
//        yleisetOsuudetService.addLiitteet(docBase);

        // Alaviitteet
        buildFootnotes(docBase);

        // Kuvat
        try {
            dokumenttiUtilService.buildImages(docBase, dokumentti.getSisaltoId(), dokumentti.getTyyppi());
        }
        catch (HttpMessageNotReadableException ex) {
            log.error(ex.getLocalizedMessage());
        }

        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.kansikuva, dokumentti.getTyyppi(), null);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.ylatunniste, dokumentti.getTyyppi(), null);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.alatunniste, dokumentti.getTyyppi(), null);

        return doc;
    }

    private void addMetaPages(DokumenttiYlops docBase) {
        Element title = docBase.getDocument().createElement("title");
        String nimi = getTextString(docBase, docBase.getOps().getNimi());
        title.appendChild(docBase.getDocument().createTextNode(nimi));
        docBase.getHeadElement().appendChild(title);

        String kuvaus = getTextString(docBase, docBase.getOps().getKuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            Element description = docBase.getDocument().createElement("meta");
            description.setAttribute("name", "description");
            description.setAttribute("content", Jsoup.parse(kuvaus).text());
            docBase.getHeadElement().appendChild(description);
        }

        Set<KoodistoDto> koodistoKoodit = docBase.getOps().getKunnat();
        if (koodistoKoodit != null) {
            Element municipalities = docBase.getDocument().createElement("kunnat");
            for (KoodistoDto koodistoKoodi : koodistoKoodit) {
                Element kuntaEl = docBase.getDocument().createElement("kunta");
                KoodistoKoodiDto koodistoKoodiDto = koodistoClient.get("kunta", koodistoKoodi.getKoodiUri());
                if (koodistoKoodiDto != null && koodistoKoodiDto.getMetadata() != null) {
                    for (KoodistoMetadataDto metadata : koodistoKoodiDto.getMetadata()) {
                        if (metadata.getNimi() != null && metadata.getKieli().toLowerCase()
                                .equals(docBase.getKieli().toString().toLowerCase())) {
                            kuntaEl.setTextContent(metadata.getNimi());
                        }
                    }
                }
                municipalities.appendChild(kuntaEl);
            }
            docBase.getHeadElement().appendChild(municipalities);
        }

        // Organisaatiot
        Element organisaatiot = docBase.getDocument().createElement("organisaatiot");

        docBase.getOps().getOrganisaatiot().stream()
                .map(org -> ylopsService.getOrganisaatio(org.getOid()))
                .filter(Objects::nonNull)
                .filter(node -> {
                    List<String> tyypit = node.getTyypit();
                    if (!CollectionUtils.isEmpty(tyypit)) {
                        for (String tyyppi : tyypit) {
                            if (tyyppi != null && Objects.equals(tyyppi, "Koulutustoimija")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .map(OrganisaatioDto::getNimi)
                .filter(Objects::nonNull)
                .map(x -> x.get(docBase.getKieli()))
                .filter(Objects::nonNull)
                .forEach(koulu -> {
                    if (!ObjectUtils.isEmpty(koulu)) {
                        Element orgEl = docBase.getDocument().createElement("koulu");
                        orgEl.setTextContent(koulu);
                        organisaatiot.appendChild(orgEl);
                    }
                });
        docBase.getHeadElement().appendChild(organisaatiot);

        // Päätöspäivämäärä
        Date paatospaivamaara = docBase.getOps().getPaatospaivamaara();
        if (paatospaivamaara != null) {
            Element dateEl = docBase.getDocument().createElement("meta");
            dateEl.setAttribute("name", "date");
            String paatospaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(paatospaivamaara);
            dateEl.setAttribute("content", paatospaivamaaraText);
            docBase.getHeadElement().appendChild(dateEl);
        }

        Element pdfluotu = docBase.getDocument().createElement("meta");
        pdfluotu.setAttribute("name", "pdfluotu");
        pdfluotu.setAttribute("content", new SimpleDateFormat("d.M.yyyy").format(new Date()));
        pdfluotu.setAttribute("translate", messages.translate("docgen.pdf-luotu", docBase.getKieli()));
        docBase.getHeadElement().appendChild(pdfluotu);

        // Koulun nimi
        Element koulutEl = docBase.getDocument().createElement("koulut");

        docBase.getOps().getOrganisaatiot().stream()
                .map(org -> ylopsService.getOrganisaatio(org.getOid()))
                .filter(Objects::nonNull)
                .filter(node -> {
                    List<String> tyypit = node.getTyypit();
                    if (!CollectionUtils.isEmpty(tyypit)) {
                        for (String tyyppi : tyypit) {
                            if (tyyppi.equals("Oppilaitos")) {
                                return true;
                            }
                        }
                    }
                    return false;
                })
                .map(OrganisaatioDto::getNimi)
                .filter(Objects::nonNull)
                .map(x -> x.get(docBase.getKieli()))
                .filter(Objects::nonNull)
                .forEach(koulu -> {
                    Element kouluEl = docBase.getDocument().createElement("koulu");
                    kouluEl.setTextContent(koulu);
                    koulutEl.appendChild(kouluEl);
                });

        docBase.getHeadElement().appendChild(koulutEl);
    }

    private void buildFootnotes(DokumenttiYlops docBase) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            XPathExpression expression = xpath.compile("//abbr");
            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);

            int noteNumber = 1;
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                Node node = list.item(i);
                if (node.getAttributes() != null & node.getAttributes().getNamedItem("data-viite") != null) {
                    String avain = node.getAttributes().getNamedItem("data-viite").getNodeValue();

                    if (docBase.getOps() != null && docBase.getOps().getId() != null && StringUtils.hasText(avain)) {
                        TermiDto termiDto = getTermi(docBase.getOps().getId(), avain);

                        if (termiDto != null && termiDto.getAlaviite() && termiDto.getSelitys() != null) {
                            element.setAttribute("number", String.valueOf(noteNumber));

                            LokalisoituTekstiDto tekstiDto = termiDto.getSelitys();
                            String selitys = getTextString(docBase, tekstiDto)
                                    .replaceAll("<[^>]+>", ""); // Tällä hetkellä tuetaan vain tekstiä
                            element.setAttribute("text", selitys);
                            noteNumber++;
                        }
                    }
                }
            }
        } catch (XPathExpressionException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private TermiDto getTermi(Long opsId, String avain) {
        TermiDto termiDto = commonExternalService.getTermi(opsId, avain, DokumenttiTyyppi.TOTEUTUSSUUNNITELMA);

        if (termiDto == null) {
            log.info("Termiä ei löytynyt ylopsista avaimella '{}'. Etsitään eperusteista.", avain);
            termiDto = commonExternalService.getTermi(opsId, avain, DokumenttiTyyppi.PERUSTE);
        }
        return termiDto;
    }
}
