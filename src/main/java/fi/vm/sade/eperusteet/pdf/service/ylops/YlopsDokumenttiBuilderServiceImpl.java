package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.domain.common.Dokumentti;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.TemplateTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.YlopsDokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.exception.DokumenttiException;
import fi.vm.sade.eperusteet.pdf.service.PdfService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.service.external.YlopsService;
import fi.vm.sade.eperusteet.pdf.utils.LocalizedMessagesService;
import fi.vm.sade.eperusteet.utils.dto.dokumentti.DokumenttiMetaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Base64;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;

@Service
public class YlopsDokumenttiBuilderServiceImpl implements YlopsDokumenttiBuilderService {

    private static final Logger LOG = LoggerFactory.getLogger(YlopsDokumenttiBuilderServiceImpl.class);

    private static final float COMPRESSION_LEVEL = 0.9f;

//    @Autowired
//    private TermistoService termistoService;
//
//    @Autowired
//    private LiiteService liiteService;
//
//    @Autowired
//    private KoodistoService koodistoService;
//
//    @Autowired
//    private OrganisaatioService organisaatioService;
//
//    @Autowired
//    private YleisetOsuudetService yleisetOsuudetService;
//
//    @Autowired
//    private PerusopetusService perusopetusService;
//
//    @Autowired
//    private LukioService lukioService;
//
//    @Autowired
//    private Lops2019DokumenttiService lops2019DokumenttiService;

    @Autowired
    private EperusteetService eperusteetService;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private YlopsService ylopsService;

    @Override
    public byte[] generatePdf(Dokumentti dokumentti) throws TransformerException, IOException, SAXException, ParserConfigurationException, NullPointerException, DokumenttiException {

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
        YlopsDokumenttiBase docBase = new YlopsDokumenttiBase();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setKieli(dokumentti.getKieli());
        docBase.setDokumentti(dokumentti);

        OpetussuunnitelmaDto ops = ylopsService.getOpetussuunnitelmaTemp(dokumentti.getSisaltoId());
        docBase.setOps(ops);

        // Kansilehti & Infosivu
//        addMetaPages(docBase);

//        // Sisältöelementit
//        yleisetOsuudetService.addYleisetOsuudet(docBase);
//
//        if (ops.getKoulutustyyppi() != null) {
//            PerusteDto perusteDto = eperusteetService.getPeruste(ops.getPerusteenDiaarinumero());
//            if (perusteDto == null) {
//                throw new DokumenttiException("Peruste puuttuu", new Throwable());
//            }
//            docBase.setPerusteDto(perusteDto);
//
//            // Perusopetus
//            if (KoulutusTyyppi.PERUSOPETUS.equals(ops.getKoulutustyyppi())) {
//                perusopetusService.addVuosiluokkakokonaisuudet(docBase);
//            }
//
//            // Lukio
//            if (KoulutustyyppiToteutus.LOPS2019.equals(ops.getToteutus())) {
//                lops2019DokumenttiService.addLops2019Sisalto(docBase);
//            }
//            else if (KoulutusTyyppi.LUKIOKOULUTUS.equals(ops.getKoulutustyyppi())) {
//                lukioService.addOppimistavoitteetJaOpetuksenKeskeisetSisallot(docBase);
//            }
//        }
//
//        // Liitteet
//        yleisetOsuudetService.addLiitteet(docBase);

        // Alaviitteet
//        buildFootnotes(docBase);

        // Kuvat
        try {
//            buildImages(docBase);
        }
        catch (HttpMessageNotReadableException ex) {
            LOG.error(ex.getLocalizedMessage());
        }

        buildKuva(docBase, Kuvatyyppi.kansikuva);
        buildKuva(docBase, Kuvatyyppi.ylatunniste);
        buildKuva(docBase, Kuvatyyppi.alatunniste);

        LOG.info("Generate PDF (opsId=" + docBase.getOps().getId() + ")");

        DokumenttiMetaDto meta = DokumenttiMetaDto.builder()
                .title(getTextString(docBase, ops.getNimi()))
                .subject(messages.translate("docgen.meta.subject.ops", dokumentti.getKieli()))
                .build();

        // PDF luonti XHTML dokumentista
        return pdfService.xhtml2pdf(doc, meta, TemplateTyyppi.YLOPS);
    }

//    private void addMetaPages(YlopsDokumenttiBase docBase) {
//        Element title = docBase.getDocument().createElement("title");
//        String nimi = getTextString(docBase, docBase.getOps().getNimi());
//        title.appendChild(docBase.getDocument().createTextNode(nimi));
//        docBase.getHeadElement().appendChild(title);
//
//        String kuvaus = getTextString(docBase, docBase.getOps().getKuvaus());
//        if (!ObjectUtils.isEmpty(kuvaus)) {
//            Element description = docBase.getDocument().createElement("meta");
//            description.setAttribute("name", "description");
//            description.setAttribute("content", Jsoup.parse(kuvaus).text());
//            docBase.getHeadElement().appendChild(description);
//        }
//
//        Set<KoodistoKoodi> koodistoKoodit = docBase.getOps().getKunnat();
//        if (koodistoKoodit != null) {
//            Element municipalities = docBase.getDocument().createElement("kunnat");
//            for (KoodistoKoodi koodistoKoodi : koodistoKoodit) {
//                Element kuntaEl = docBase.getDocument().createElement("kunta");
//                KoodistoKoodiDto koodistoKoodiDto = koodistoService.get("kunta", koodistoKoodi.getKoodiUri());
//                if (koodistoKoodiDto != null && koodistoKoodiDto.getMetadata() != null) {
//                    for (KoodistoMetadataDto metadata : koodistoKoodiDto.getMetadata()) {
//                        if (metadata.getNimi() != null && metadata.getKieli().toLowerCase()
//                                .equals(docBase.getKieli().toString().toLowerCase())) {
//                            kuntaEl.setTextContent(metadata.getNimi());
//                        }
//                    }
//                }
//                municipalities.appendChild(kuntaEl);
//            }
//            docBase.getHeadElement().appendChild(municipalities);
//        }
//
//        // Organisaatiot
//        Element organisaatiot = docBase.getDocument().createElement("organisaatiot");
//
//        docBase.getOps().getOrganisaatiot().stream()
//                .map(org -> organisaatioService.getOrganisaatio(org))
//                .filter(Objects::nonNull)
//                .filter(node -> {
//                    JsonNode tyypit = node.get("tyypit");
//                    if (tyypit != null && tyypit.isArray()) {
//                        for (JsonNode tyyppi : tyypit) {
//                            if (tyyppi != null && Objects.equals(tyyppi.textValue(), "Koulutustoimija")) {
//                                return true;
//                            }
//                        }
//                    }
//                    return false;
//                })
//                .map(node -> node.get("nimi"))
//                .filter(Objects::nonNull)
//                .map(x -> x.get(docBase.getKieli().toString()))
//                .filter(Objects::nonNull)
//                .map(JsonNode::asText)
//                .forEach(koulu -> {
//                    if (!ObjectUtils.isEmpty(koulu)) {
//                        Element orgEl = docBase.getDocument().createElement("koulu");
//                        orgEl.setTextContent(koulu);
//                        organisaatiot.appendChild(orgEl);
//                    }
//                });
//        docBase.getHeadElement().appendChild(organisaatiot);
//
//        // Päätöspäivämäärä
//        Date paatospaivamaara = docBase.getOps().getPaatospaivamaara();
//        if (paatospaivamaara != null) {
//            Element dateEl = docBase.getDocument().createElement("meta");
//            dateEl.setAttribute("name", "date");
//            String paatospaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(paatospaivamaara);
//            dateEl.setAttribute("content", paatospaivamaaraText);
//            docBase.getHeadElement().appendChild(dateEl);
//        }
//
//        Element pdfluotu = docBase.getDocument().createElement("meta");
//        pdfluotu.setAttribute("name", "pdfluotu");
//        pdfluotu.setAttribute("content", new SimpleDateFormat("d.M.yyyy").format(new Date()));
//        pdfluotu.setAttribute("translate", messages.translate("docgen.pdf-luotu", docBase.getKieli()));
//        docBase.getHeadElement().appendChild(pdfluotu);
//
//        // Koulun nimi
//        Element koulutEl = docBase.getDocument().createElement("koulut");
//
//        docBase.getOps().getOrganisaatiot().stream()
//                .map(org -> organisaatioService.getOrganisaatio(org))
//                .filter(Objects::nonNull)
//                .filter(node -> {
//                    JsonNode tyypit = node.get("tyypit");
//                    if (tyypit != null && tyypit.isArray()) {
//                        for (JsonNode tyyppi : tyypit) {
//                            if (tyyppi.textValue().equals("Oppilaitos")) {
//                                return true;
//                            }
//                        }
//                    }
//                    return false;
//                })
//                .map(node -> node.get("nimi"))
//                .filter(Objects::nonNull)
//                .map(x -> x.get(docBase.getKieli().toString()))
//                .filter(Objects::nonNull)
//                .map(JsonNode::asText)
//                .forEach(koulu -> {
//                    Element kouluEl = docBase.getDocument().createElement("koulu");
//                    kouluEl.setTextContent(koulu);
//                    koulutEl.appendChild(kouluEl);
//                });
//
//        docBase.getHeadElement().appendChild(koulutEl);
//    }
//
//    private void buildFootnotes(YlopsDokumenttiBase docBase) {
//        XPathFactory xPathfactory = XPathFactory.newInstance();
//        XPath xpath = xPathfactory.newXPath();
//        try {
//            XPathExpression expression = xpath.compile("//abbr");
//            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);
//
//            int noteNumber = 1;
//            for (int i = 0; i < list.getLength(); i++) {
//                Element element = (Element) list.item(i);
//                Node node = list.item(i);
//                if (node.getAttributes() != null & node.getAttributes().getNamedItem("data-viite") != null) {
//                    String avain = node.getAttributes().getNamedItem("data-viite").getNodeValue();
//
//                    if (docBase.getOps() != null && docBase.getOps().getId() != null && StringUtils.hasText(avain)) {
//                        TermiDto termiDto = termistoService.getTermi(docBase.getOps().getId(), avain);
//
//                        if (termiDto != null && termiDto.isAlaviite() && termiDto.getSelitys() != null) {
//                            element.setAttribute("number", String.valueOf(noteNumber));
//
//                            LokalisoituTekstiDto tekstiDto = termiDto.getSelitys();
//                            String selitys = getTextString(docBase, tekstiDto)
//                                    .replaceAll("<[^>]+>", ""); // Tällä hetkellä tuetaan vain tekstiä
//                            element.setAttribute("text", selitys);
//                            noteNumber++;
//                        }
//                    }
//                }
//            }
//        } catch (XPathExpressionException e) {
//            LOG.error(e.getLocalizedMessage());
//        }
//    }

//    private void buildImages(YlopsDokumenttiBase docBase) {
//        XPathFactory xPathfactory = XPathFactory.newInstance();
//        XPath xpath = xPathfactory.newXPath();
//        try {
//            XPathExpression expression = xpath.compile("//img");
//            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);
//
//            for (int i = 0; i < list.getLength(); i++) {
//                Element element = (Element) list.item(i);
//                String id = element.getAttribute("data-uid");
//                String src = element.getAttribute("src");
//
//                if ("".equals(id) && "".equals(src)) {
//                    continue;
//                }
//
//                UUID uuid = null;
//                try {
//                    uuid = UUID.fromString(id);
//                } catch (IllegalArgumentException e) {
//                    // Jos data-uuid puuttuu, koitetaan hakea src:n avulla
//                    if (src.contains("eperusteet-ylops-service")) {
//                        String[] parts = src.split("/");
//                        if (parts.length > 1 && Objects.equals(parts[parts.length - 2], "kuvat")) {
//                            uuid = UUID.fromString(parts[parts.length - 1]);
//                        }
//                    }
//                }
//
//                if (uuid == null) {
//                    LOG.error("src {}, id {} ", src, id);
//                    throw new BusinessRuleViolationException("kuva-uuid-ei-loytynyt");
//                }
//
//                // Ladataan kuvat data muistiin
//                InputStream in = liiteService.export(docBase.getOps().getId(), uuid, docBase.getPerusteDto().getId());
//
//                // Tehdään muistissa olevasta datasta kuva
//                BufferedImage bufferedImage = ImageIO.read(in);
//
//                int width = bufferedImage.getWidth();
//                int height = bufferedImage.getHeight();
//
//                // Muutetaan kaikkien kuvien väriavaruus RGB:ksi jotta PDF/A validointi menee läpi
//                // Asetetaan lisäksi läpinäkyvien kuvien taustaksi valkoinen väri
//                BufferedImage tempImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
//                        BufferedImage.TYPE_3BYTE_BGR);
//                tempImage.getGraphics().setColor(new Color(255, 255, 255, 0));
//                tempImage.getGraphics().fillRect(0, 0, width, height);
//                tempImage.getGraphics().drawImage(bufferedImage, 0, 0, null);
//                bufferedImage = tempImage;
//
//                ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
//                ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
//                jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//                jpgWriteParam.setCompressionQuality(COMPRESSION_LEVEL);
//
//                // Muunnetaan kuva base64 enkoodatuksi
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                MemoryCacheImageOutputStream imageStream = new MemoryCacheImageOutputStream(out);
//                jpgWriter.setOutput(imageStream);
//                IIOImage outputImage = new IIOImage(bufferedImage, null, null);
//                jpgWriter.write(null, outputImage, jpgWriteParam);
//                jpgWriter.dispose();
//                String base64 = Base64.getEncoder().encodeToString(out.toByteArray());
//
//                // Lisätään bas64 kuva img elementtiin
//                element.setAttribute("width", String.valueOf(width));
//                element.setAttribute("height", String.valueOf(height));
//                element.setAttribute("src", "data:image/jpg;base64," + base64);
//            }
//
//        } catch (XPathExpressionException | IOException | NotExistsException e) {
//            LOG.error(e.getLocalizedMessage());
//        }
//    }

    private void buildKuva(YlopsDokumenttiBase docBase, Kuvatyyppi tyyppi) {
        Dokumentti dokumentti = docBase.getDokumentti();

        byte[] kuva = ylopsService.getDokumenttiKuva(dokumentti.getSisaltoId(), tyyppi, dokumentti.getKieli());
        if (kuva == null) {
            return;
        }

        Element head = docBase.getHeadElement();
        Element element = docBase.getDocument().createElement(tyyppi.toString());
        Element img = docBase.getDocument().createElement("img");

        String base64 = Base64.getEncoder().encodeToString(kuva);
        img.setAttribute("src", "data:image/jpg;base64," + base64);

        element.appendChild(img);
        head.appendChild(element);
    }
}
