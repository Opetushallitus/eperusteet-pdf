package fi.vm.sade.eperusteet.pdf.service.eperusteet;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.common.AbstractRakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.MuodostumisSaantoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.Reference;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiPeruste;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiRiviTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.KoulutustyyppiToteutus;
import fi.vm.sade.eperusteet.pdf.dto.enums.LaajuusYksikko;
import fi.vm.sade.eperusteet.pdf.dto.enums.OsaAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.PerusteenOsaTunniste;
import fi.vm.sade.eperusteet.pdf.dto.enums.TavoiteAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerisenArvioinninOsaamistasonKriteeriKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.KevytTekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArvioinninKohdeDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArvioinninKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArviointiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.Lops2019OppiaineKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.Lops2019SisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019ArviointiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019OpiskeluymparistoTyotavatDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019OppiaineTavoitealueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019OppiaineTavoitteetDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.Lops2019TehtavaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.lops2019.oppiaineet.moduuli.Lops2019ModuuliTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.SuoritustapaLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TekstiKappaleDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TutkintonimikeKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Ammattitaitovaatimukset2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Ammattitaitovaatimus2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.AmmattitaitovaatimustenKohdealue2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaAlueKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaamisenTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Osaamistavoite2020Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaamistavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaamistavoiteLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.ValmaTelmaSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.KoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteSuppeaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.TuvaLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoKielitaitotasoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoOpintoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoSisalto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.OpintokokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.TavoitesisaltoalueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEKurssiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOpetuksenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEOppiaineLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.AIPEVaiheDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.KeskeinenSisaltoalueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.LaajaalainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OpetuksenKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OpetuksenTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineenVuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.PerusopetuksenPerusteenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TaiteenalaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TekstiOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.VuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.service.external.EperusteetService;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import fi.vm.sade.eperusteet.pdf.utils.Pair;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getList;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.newBoldElement;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.newItalicElement;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.sisaltaaDokumentinKielenSisallon;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.tagTeksti;

@Slf4j
@Service
public class EperusteetDokumenttiBuilderServiceImpl implements EperusteetDokumenttiBuilderService {

    @Autowired
    private EperusteetService eperusteetService;

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private DokumenttiUtilService dokumenttiUtilService;

    @Autowired
    private MapperFacade mapper;

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    @Override
    public Document generateXML(PerusteKaikkiDto perusteData, GeneratorData generatorData) throws ParserConfigurationException, JsonProcessingException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Luodaan XHTML pohja
        Element rootElement = doc.createElement("html");
        rootElement.setAttribute("lang", generatorData.getKieli().toString());
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
        DokumenttiPeruste docBase = new DokumenttiPeruste();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setGenerator(new CharapterNumberGenerator());
        docBase.setKieli(generatorData.getKieli());
        docBase.setPeruste(perusteData);
        if (perusteData.getKoulutustyyppi() != null && KoulutusTyyppi.of(perusteData.getKoulutustyyppi()).isAmmatillinen()) {
            docBase.setKvLiiteJulkinenDto(eperusteetService.getKvLiite(perusteData.getId()));
        }
        docBase.setGeneratorData(generatorData);

        // Tästä aloitetaan varsinaisen dokumentin muodostus
        addMetaPages(docBase); // Kansilehti & Infosivu

        PerusteenOsaViiteDto.Laaja sisalto = docBase.getSisalto();

        if (perusteData.getKoulutustyyppi() != null && KoulutusTyyppi.of(perusteData.getKoulutustyyppi()).isLukio()) {
            addPerusteenOsat(docBase, sisalto); // Tekstikappaleet
            addLukioOppiaineet(docBase);
        } else if (perusteData.getKoulutustyyppi() != null && KoulutusTyyppi.of(perusteData.getKoulutustyyppi()).equals(KoulutusTyyppi.PERUSOPETUS)) {
            addPerusteenOsat(docBase, sisalto); // Tekstikappaleet
            addPerusopetus(docBase);
        } else {
            addTutkinnonMuodostuminen(docBase);
            addTutkinnonosat(docBase);
            addPerusteenOsat(docBase, sisalto); // Tekstikappaleet
            addAipeSisalto(docBase);
        }
        addTekstikappaleLiitteet(docBase, sisalto);
        addFootnotes(docBase);

        // Kuvat
        dokumenttiUtilService.buildImages(docBase, generatorData);

        return doc;
    }

    private void addMetaPages(DokumenttiPeruste docBase) {
        // Nimi
        Element title = docBase.getDocument().createElement("title");
        String nimi = getTextString(docBase, docBase.getPeruste().getNimi());
        docBase.getDocument().getDocumentElement().setAttribute("opetushallitus", messages.translate("opetushallitus", docBase.getKieli()));

        if (!ObjectUtils.isEmpty(nimi)) {
            title.appendChild(docBase.getDocument().createTextNode(nimi));
            docBase.getHeadElement().appendChild(title);

            // Perusteen nimi
            Element perusteenNimi = docBase.getDocument().createElement("meta");
            perusteenNimi.setAttribute("name", "perusteenNimi");

            if (docBase.getPeruste().getTyyppi().equals(PerusteTyyppi.OPAS)) {
                Element opas = docBase.getDocument().createElement("opas");
                opas.setTextContent(nimi);
                docBase.getHeadElement().appendChild(opas);
                perusteenNimi.setAttribute("translate", messages.translate("oppaan-nimi", docBase.getKieli()));
            } else {
                Element peruste = docBase.getDocument().createElement("peruste");
                peruste.setTextContent(nimi);
                docBase.getHeadElement().appendChild(peruste);
                perusteenNimi.setAttribute("translate", messages.translate("perusteen-nimi", docBase.getKieli()));
            }
            docBase.getHeadElement().appendChild(perusteenNimi);
        }

        if (KoulutustyyppiToteutus.AMMATILLINEN.equals(docBase.getPeruste().getToteutus())) {
            Element etusivuYlaviite = docBase.getDocument().createElement("meta");
            etusivuYlaviite.setAttribute("name", "etusivuYlaviite");
            etusivuYlaviite.setAttribute("translate", messages.translate("tutkinnon-perusteet", docBase.getKieli()));
            docBase.getHeadElement().appendChild(etusivuYlaviite);
        }
        {
            Element description = docBase.getDocument().createElement("description");
            docBase.getHeadElement().appendChild(description);

            KVLiiteJulkinenDto kvLiiteJulkinenDto = docBase.getKvLiiteJulkinenDto();
            if (kvLiiteJulkinenDto != null) {

                // Tutkinnon suorittaneen osaaminen
                String suorittaneenOsaaminen = getTextString(docBase, kvLiiteJulkinenDto.getSuorittaneenOsaaminen());
                if (!ObjectUtils.isEmpty(suorittaneenOsaaminen)) {
                    addTeksti(docBase,
                            messages.translate("docgen.kvliite.tutkinnon-suorittaneen-osaaminen", docBase.getKieli()),
                            "h6",
                            description);
                    addTeksti(docBase, suorittaneenOsaaminen, "div", description);
                }

                // Työtehtäviä, joissa tutkinnon suorittanut voi toimia
                String tyotehtavat = getTextString(docBase, kvLiiteJulkinenDto.getTyotehtavatJoissaVoiToimia());
                if (!ObjectUtils.isEmpty(tyotehtavat)) {
                    addTeksti(docBase,
                            messages.translate("docgen.kvliite.tyotehtavat", docBase.getKieli()),
                            "h6",
                            description);
                    addTeksti(docBase, tyotehtavat, "div", description);
                }
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        // sisallysluettelo
        Element sisalto = docBase.getDocument().createElement("meta");
        sisalto.setAttribute("name", "sisalto");
        sisalto.setAttribute("translate", messages.translate("sisalto", docBase.getKieli()));
        docBase.getHeadElement().appendChild(sisalto);

        // Voimaantulo
        if (docBase.getPeruste().getVoimassaoloAlkaa() != null) {
            Element description = docBase.getDocument().createElement("meta");
            description.setAttribute("name", "voimaantulo");
            description.setAttribute("content", dateFormat.format(docBase.getPeruste().getVoimassaoloAlkaa()));
            description.setAttribute("translate", messages.translate("voimaantulo", docBase.getKieli()));
            docBase.getHeadElement().appendChild(description);
        }

        // Voimaantulon päättyminen
        if (docBase.getPeruste().getVoimassaoloLoppuu() != null) {
            Element description = docBase.getDocument().createElement("meta");
            description.setAttribute("name", "voimassaolo-paattyminen");
            description.setAttribute("content", dateFormat.format(docBase.getPeruste().getVoimassaoloLoppuu()));
            description.setAttribute("translate", messages.translate("voimassaolo-paattyminen", docBase.getKieli()));
            docBase.getHeadElement().appendChild(description);
        }

        Element pdfluotu = docBase.getDocument().createElement("meta");
        pdfluotu.setAttribute("name", "pdfluotu");
        pdfluotu.setAttribute("content", dateFormat.format(new Date()));
        pdfluotu.setAttribute("translate", messages.translate("docgen.pdf-luotu", docBase.getKieli()));
        docBase.getHeadElement().appendChild(pdfluotu);

        // Oppaille ei lisätä perusteiden tietoja
        if (docBase.getPeruste().getTyyppi() == PerusteTyyppi.OPAS) {
            return;
        }

        // Diaarinumero
        if (docBase.getPeruste().getDiaarinumero() != null) {
            Element diary = docBase.getDocument().createElement("meta");
            diary.setAttribute("name", "diary");
            diary.setAttribute("content", docBase.getPeruste().getDiaarinumero().toString());
            diary.setAttribute("translate", messages.translate("maarayksen-diaarinumero", docBase.getKieli()));
            docBase.getHeadElement().appendChild(diary);
        }

        // Korvaavat määräykset
        if (!CollectionUtils.isEmpty(docBase.getPeruste().getKorvattavatDiaarinumerot())) {
            Element korvaavat = docBase.getDocument().createElement("korvaavat");
            korvaavat.setAttribute("translate", messages.translate("korvattavat-maaraykset", docBase.getKieli()));

            docBase.getPeruste().getKorvattavatDiaarinumerot()
                    .forEach(numero -> {
                        Element korvaava = docBase.getDocument().createElement("korvaava");
                        korvaava.setTextContent(numero);
                        korvaavat.appendChild(korvaava);
                    });
            docBase.getHeadElement().appendChild(korvaavat);
        }

        // Muutosmääräykset
        if (!CollectionUtils.isEmpty(docBase.getPeruste().getMuutosmaaraykset())) {
            Element muutosmaaraykset = docBase.getDocument().createElement("muutosmaaraykset");
            muutosmaaraykset.setAttribute("translate", messages.translate("muutosmaaraykset", docBase.getKieli()));

            docBase.getPeruste().getMuutosmaaraykset().forEach(muutosmaarays -> {
                Element muutosmaaraysEl = docBase.getDocument().createElement("muutosmaarays");

                Element linkki = docBase.getDocument().createElement("a");
                linkki.setAttribute("href", getTextString(docBase, muutosmaarays.getUrl(eperusteetServiceUrl, docBase.getPeruste().getId())));
                if (muutosmaarays.getNimi() != null) {
                    linkki.setTextContent(getTextString(docBase, muutosmaarays.getNimi()));
                } else {
                    linkki.setTextContent(getTextString(docBase, muutosmaarays.getUrl(eperusteetServiceUrl, docBase.getPeruste().getId())));
                }
                muutosmaaraysEl.appendChild(linkki);
                muutosmaaraykset.appendChild(muutosmaaraysEl);
            });
            docBase.getHeadElement().appendChild(muutosmaaraykset);
        }

        // Koulutuskoodit
        if (!CollectionUtils.isEmpty(docBase.getPeruste().getKoulutukset())) {
            Element koulutukset = docBase.getDocument().createElement("koulutukset");
            koulutukset.setAttribute("translate", messages.translate("koulutukset", docBase.getKieli()));

            docBase.getPeruste().getKoulutukset().forEach(koulutus -> {
                String koulutusNimi = getTextString(docBase, koulutus.getNimi());
                if (!ObjectUtils.isEmpty(koulutus.getKoulutuskoodiArvo())) {
                    koulutusNimi += " (" + koulutus.getKoulutuskoodiArvo() + ")";
                }
                Element koulutusEl = docBase.getDocument().createElement("koulutus");
                koulutusEl.setTextContent(koulutusNimi);
                koulutukset.appendChild(koulutusEl);
            });
            docBase.getHeadElement().appendChild(koulutukset);
        }

        // Osaamisalat
        if (!CollectionUtils.isEmpty(docBase.getPeruste().getOsaamisalat())) {
            Element osaamisalat = docBase.getDocument().createElement("osaamisalat");
            osaamisalat.setAttribute("translate", messages.translate("osaamisalat", docBase.getKieli()));

            docBase.getPeruste().getOsaamisalat().forEach(osaamisala -> {
                String osaamisalaNimi = getTextString(docBase, osaamisala.getNimi());
                    if (!ObjectUtils.isEmpty(osaamisala.getArvo())) {
                        osaamisalaNimi += " (" + osaamisala.getArvo() + ")";
                    }
                    Element osaamisalaEl = docBase.getDocument().createElement("osaamisala");
                    osaamisalaEl.setTextContent(osaamisalaNimi);
                    osaamisalat.appendChild(osaamisalaEl);
                });
            docBase.getHeadElement().appendChild(osaamisalat);
        }

        // Tutkintonimikkeet
        List<TutkintonimikeKoodiDto> nimikeKoodit =  docBase.getPeruste().getTutkintonimikkeet();
        if (!CollectionUtils.isEmpty(nimikeKoodit)) {
            Element tutkintonimikkeet = docBase.getDocument().createElement("tutkintonimikkeet");
            tutkintonimikkeet.setAttribute("translate", messages.translate("tutkintonimikkeet", docBase.getKieli()));

            nimikeKoodit.forEach(tnkoodi -> {
                Element tutkintonimike = docBase.getDocument().createElement("tutkintonimike");
                tutkintonimike.setTextContent(tnkoodi.getNimi().get(docBase.getKieli()) + " (" + tnkoodi.getTutkintonimikeArvo() + ")");
                tutkintonimikkeet.appendChild(tutkintonimike);
            });
            if (tutkintonimikkeet.hasChildNodes()) {
                docBase.getHeadElement().appendChild(tutkintonimikkeet);
            }
        }
    }

    private void addAipeSisalto(DokumenttiPeruste docBase) {
        AIPEOpetuksenSisaltoDto aipeSisalto = docBase.getPeruste().getAipeOpetuksenPerusteenSisalto();
        if (aipeSisalto != null) {
            addVaiheet(docBase, aipeSisalto);
        }
    }

    private void addVaiheet(DokumenttiPeruste docBase, AIPEOpetuksenSisaltoDto aipeSisalto) {
        aipeSisalto.getVaiheet().forEach(aipeVaihe -> addVaihe(docBase, aipeVaihe));
    }

    private void addVaihe(DokumenttiPeruste docBase, AIPEVaiheDto vaihe) {
        addHeader(docBase, getTextString(docBase, getOptionalValue(vaihe.getNimi())));

        docBase.getGenerator().increaseDepth();

        addTekstiOsa(docBase, vaihe.getSiirtymaEdellisesta());
        addTekstiOsa(docBase, vaihe.getTehtava());
        addTekstiOsa(docBase, vaihe.getSiirtymaSeuraavaan());
        addTekstiOsa(docBase, vaihe.getPaikallisestiPaatettavatAsiat());

        if (vaihe.getOppiaineet().size() > 0) {
            vaihe.getOppiaineet().forEach(aipeOppiaine -> addOppiaine(docBase, vaihe, aipeOppiaine));
        }

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addFootnotes(DokumenttiPeruste docBase) {
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        try {
            XPathExpression expression = xpath.compile("//abbr");
            NodeList list = (NodeList) expression.evaluate(docBase.getDocument(), XPathConstants.NODESET);

            int noteNumber = 1;
            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                element.setAttribute("text", element.getTextContent());

                Node node = list.item(i);
                if (node.getAttributes() != null & node.getAttributes().getNamedItem("data-viite") != null) {
                    String avain = node.getAttributes().getNamedItem("data-viite").getNodeValue();

                    if (docBase.getPeruste() != null && docBase.getPeruste().getId() != null) {
                        TermiDto termi = dokumenttiUtilService.getTermiFromExternalService(docBase.getPeruste().getId(), avain, docBase.getGeneratorData().getTyyppi());

                        if (termi != null && termi.getAlaviite() && termi.getSelitys() != null) {
                            element.setAttribute("number", String.valueOf(noteNumber));

                            LokalisoituTekstiDto tekstiDto = termi.getSelitys();
                            String selitys = getTextString(docBase, tekstiDto).replaceAll("<(?!\\/?(a)(>|\\s))[^<]+?>", "");
                            addTeksti(docBase, selitys, "attrfootnote", element);
                            noteNumber++;
                        }
                    }
                }
            }

        } catch (XPathExpressionException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    private void addTutkinnonMuodostuminen(DokumenttiPeruste docBase) {
        if (docBase.getSuoritustapa().map(SuoritustapaLaajaDto::getRakenne).isEmpty()) {
            return;
        }

        if (KoulutusTyyppi.of(docBase.getPeruste().getKoulutustyyppi()).equals(KoulutusTyyppi.VALMA)
                || KoulutusTyyppi.of(docBase.getPeruste().getKoulutustyyppi()).equals(KoulutusTyyppi.TELMA)) {
            addHeader(docBase, messages.translate("docgen.koulutuksen_muodostuminen.title", docBase.getKieli()));
        } else {
            addHeader(docBase, messages.translate("docgen.tutkinnon_muodostuminen.title", docBase.getKieli()));
        }

        RakenneModuuliDto rakenne = docBase.getSuoritustapa().get().getRakenne();
        String kuvaus = getTextString(docBase, rakenne.getKuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        // Luodaan muodostumistaulukko
        Element taulukko = docBase.getDocument().createElement("table");
        taulukko.setAttribute("border", "1");
        docBase.getBodyElement().appendChild(taulukko);
        Element tbody = docBase.getDocument().createElement("tbody");
        taulukko.appendChild(tbody);

        addRakenneOsa(docBase, rakenne, tbody, 0);

        docBase.getGenerator().increaseNumber();
    }

    private void addRakenneOsa(DokumenttiPeruste docBase, AbstractRakenneOsaDto osa, Element tbody, int depth) {
        if (osa instanceof RakenneModuuliDto) {
            // Ryhmä
            RakenneModuuliDto rakenneModuuli = (RakenneModuuliDto) osa;
            addRakenneModuuli(docBase, rakenneModuuli, tbody, depth);

            // Rekursiivisesti koko puu
            for (AbstractRakenneOsaDto lapsi : rakenneModuuli.getOsat()) {
                addRakenneOsa(docBase, lapsi, tbody, depth + 1);
            }
        } else if (osa instanceof RakenneOsaDto) {
            // Tutkinnon osa
            RakenneOsaDto rakenneOsa = (RakenneOsaDto) osa;

            String nimi = getOtsikko(docBase, docBase.getTutkinnonosaViite(rakenneOsa.getTutkinnonOsaViite().getIdLong()).get(), false);
            String kuvaus = getTextString(docBase, rakenneOsa.getKuvaus());

            Element tr = docBase.getDocument().createElement("tr");
            Element td = docBase.getDocument().createElement("td");
            td.setAttribute("class", "td" + depth);
            Element p = docBase.getDocument().createElement("p");

            tbody.appendChild(tr);
            tr.appendChild(td);
            td.appendChild(p);
            p.setTextContent(nimi);
            if (!ObjectUtils.isEmpty(kuvaus)) {
                td.appendChild(newItalicElement(docBase, kuvaus));
            }

            if (rakenneOsa.getPakollinen() != null && rakenneOsa.getPakollinen()) {
                String glyph = messages.translate("docgen.rakenneosa.pakollinen.glyph", docBase.getKieli());
                p.appendChild(docBase.getDocument().createTextNode(", "));
                p.appendChild(newBoldElement(docBase.getDocument(), glyph));
            }
        }
    }

    private void addRakenneModuuli(DokumenttiPeruste docBase, RakenneModuuliDto rakenneModuuli, Element tbody, int depth) {
        MuodostumisSaantoDto muodostumisSaanto = rakenneModuuli.getMuodostumisSaanto();

        String kokoTeksti = getKokoTeksti(muodostumisSaanto, docBase.getKieli());
        String laajuusTeksti = getLaajuusTeksti(muodostumisSaanto, docBase.getLaajuusYksikko(), docBase.getKieli());
        String kuvaus = getTextString(docBase, rakenneModuuli.getKuvaus());
        String nimi = getTextString(docBase, rakenneModuuli.getNimi());
        if (kokoTeksti != null) {
            nimi += " | " + kokoTeksti;
        }
        if (laajuusTeksti != null) {
            nimi += " | " + laajuusTeksti;
        }

        Element tr = docBase.getDocument().createElement("tr");
        Element th = docBase.getDocument().createElement("th");
        th.setAttribute("class", "th" + depth);
        Element td = docBase.getDocument().createElement("td");
        td.setAttribute("class", "td" + depth);
        Element p = docBase.getDocument().createElement("p");

        switch (depth) {
            case 0:
                break;
            case 1:
                tr.setAttribute("bgcolor", "#AAAAAA");
                tbody.appendChild(tr);
                tr.appendChild(th);
                th.appendChild(p);
                p.appendChild(newBoldElement(docBase.getDocument(), nimi.toUpperCase()));
                if (!ObjectUtils.isEmpty(kuvaus)) {
                    th.appendChild(newItalicElement(docBase, kuvaus));
                }
                break;
            case 2:
                tr.setAttribute("bgcolor", "#EEEEEE");

                tbody.appendChild(tr);
                tr.appendChild(td);
                td.appendChild(p);
                p.appendChild(newBoldElement(docBase.getDocument(), nimi));
                if (!ObjectUtils.isEmpty(kuvaus)) {
                    td.appendChild(newItalicElement(docBase, kuvaus));
                }
                break;

            default:
                tbody.appendChild(tr);
                tr.appendChild(td);
                td.appendChild(p);
                p.appendChild(newBoldElement(docBase.getDocument(), nimi));
                if (!ObjectUtils.isEmpty(kuvaus)) {
                    td.appendChild(newItalicElement(docBase, kuvaus));
                }
                break;
        }
    }

    private void addTutkinnonosat(DokumenttiPeruste docBase) {
        if (docBase.getSuoritustapa().map(SuoritustapaLaajaDto::getTutkinnonOsat).isEmpty()) {
            return;
        }

        Set<TutkinnonOsaViiteSuppeaDto> osat = new TreeSet<>((o1, o2) -> {
            String nimi1 = getTextString(docBase, docBase.getTutkinnonOsa(o1.getTutkinnonOsa().getIdLong()).get().getNimi());
            String nimi2 = getTextString(docBase, docBase.getTutkinnonOsa(o2.getTutkinnonOsa().getIdLong()).get().getNimi());

            // Ensisijaisesti järjestysnumeron mukaan
            int o1i = o1.getJarjestys() != null ? o1.getJarjestys() : Integer.MAX_VALUE;
            int o2i = o2.getJarjestys() != null ? o2.getJarjestys() : Integer.MAX_VALUE;
            if (o1i < o2i) {
                return -1;
            } else if (o1i > o2i) {
                return 1;
            }

            // Toissijaisesti aakkosjärjestyksessä
            if (!nimi1.equals(nimi2)) {
                return nimi1.compareTo(nimi2);
            }

            // Viimeisenä kanta-avaimen mukaan
            Long id1 = Long.valueOf(o1.getTutkinnonOsa().getId());
            Long id2 = Long.valueOf(o2.getTutkinnonOsa().getId());
            if (id1 < id2) {
                return -1;
            } else if (id1 > id2) {
                return 1;
            }
            // Ovat samat
            return 0;
        });

        osat.addAll(docBase.getSuoritustapa().get().getTutkinnonOsat());
        if (KoulutusTyyppi.of(docBase.getPeruste().getKoulutustyyppi()).equals(KoulutusTyyppi.VALMA)
                || KoulutusTyyppi.of(docBase.getPeruste().getKoulutustyyppi()).equals(KoulutusTyyppi.TELMA)) {
            addHeader(docBase, messages.translate("docgen.koulutuksen_osat.title", docBase.getKieli()));
        } else {
            addHeader(docBase, messages.translate("docgen.tutkinnon_osat.title", docBase.getKieli()));
        }

        docBase.getGenerator().increaseDepth();

        osat.forEach(viite -> {
            TutkinnonOsaKaikkiDto osa = docBase.getTutkinnonOsa(viite.getTutkinnonOsa().getIdLong()).get();

            String otsikko = getOtsikko(docBase, viite);
            addHeader(docBase, otsikko);

            String kuvaus = getTextString(docBase, osa.getKuvaus());
            if (!ObjectUtils.isEmpty(kuvaus)) {
                addTeksti(docBase, kuvaus, "div");
            }

            TutkinnonOsaTyyppi tyyppi = osa.getTyyppi();
            if (tyyppi == TutkinnonOsaTyyppi.NORMAALI) {
                addTavoitteet(docBase, osa);
                addAmmattitaitovaatimukset(docBase,
                        osa.getAmmattitaitovaatimukset2019(),
                        osa.getAmmattitaitovaatimuksetLista(),
                        osa.getAmmattitaitovaatimukset());
                addGeneerinenArviointi(docBase, osa.getGeneerinenArviointiasteikko());

                if (osa.getGeneerinenArviointiasteikko() == null) {
                    addArviointi(docBase, osa.getArviointi(), tyyppi);
                }
                addValmatelmaSisalto(docBase, osa.getValmaTelmaSisalto());
                addAmmattitaidonOsoittamistavat(docBase, osa);
                addVapaatTekstit(docBase, osa);
            } else if (TutkinnonOsaTyyppi.isTutke(tyyppi)) {
                addTutke2Osat(docBase, osa);
            }
            docBase.getGenerator().increaseNumber();
        });
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addLukioOppiaineet(DokumenttiPeruste docBase) {
        Lops2019SisaltoDto perusteenSisalto = docBase.getPeruste().getLops2019Sisalto();
        if (perusteenSisalto == null) {
            return;
        }

        addHeader(docBase, messages.translate("oppiaineet", docBase.getKieli()));
        docBase.getGenerator().increaseDepth();

        List<Lops2019OppiaineKaikkiDto> oppiaineet = docBase.getPeruste().getLops2019Sisalto().getOppiaineet();
        if (!ObjectUtils.isEmpty(oppiaineet)) {
            oppiaineet.stream()
                    .sorted()
                    .forEach(oa -> {
                addOppiaine(docBase, oa);
                docBase.getGenerator().increaseNumber();
            });
        }

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addOppiaine(DokumenttiPeruste docBase, Lops2019OppiaineKaikkiDto oa) {
        StringBuilder nimiBuilder = new StringBuilder();
        String nimi = getTextString(docBase, oa.getNimi());
        nimiBuilder.append(StringUtils.isEmpty(nimi)
                ? messages.translate("nimeton-oppiaine", docBase.getKieli())
                : nimi);
        fi.vm.sade.eperusteet.pdf.dto.ylops.KoodiDto koodi = oa.getKoodi();
        if (koodi != null && koodi.getArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(koodi.getArvo());
            nimiBuilder.append(")");
        }
        addHeader(docBase, nimiBuilder.toString());

        // Tehtävä
        Lops2019TehtavaDto tehtava = oa.getTehtava();
        if (tehtava != null && tehtava.getKuvaus() != null) {
            addTeksti(docBase, messages.translate("oppiaine-ja-tehtava", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, tehtava.getKuvaus(), "div");
        }

        Lops2019OpiskeluymparistoTyotavatDto opiskeluymparistoTyotavat = oa.getOpiskeluymparistoTyotavat();
        if (opiskeluymparistoTyotavat != null && opiskeluymparistoTyotavat.getKuvaus() != null) {
            addTeksti(docBase, messages.translate("opiskeluymparisto-ja-tyotavat", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, opiskeluymparistoTyotavat.getKuvaus(), "div");
        }

        // Tavoitteet
        Lops2019OppiaineTavoitteetDto tavoitteet = oa.getTavoitteet();
        if (tavoitteet != null && (tavoitteet.getKuvaus() != null || !ObjectUtils.isEmpty(tavoitteet.getTavoitealueet()))) {
            addTeksti(docBase, messages.translate("tavoitteet", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, tavoitteet.getKuvaus(), "div");

            List<Lops2019OppiaineTavoitealueDto> tavoitealueet = tavoitteet.getTavoitealueet();
            if (!ObjectUtils.isEmpty(tavoitealueet)) {

                tavoitealueet.forEach(ta -> {
                    LokalisoituTekstiDto kohde = ta.getKohde();
                    if (kohde != null && !ObjectUtils.isEmpty(ta.getTavoitteet())) {
                        Element div = docBase.getDocument().createElement("div");

                        Element nimiEl = docBase.getDocument().createElement("em");
                        nimiEl.setTextContent(getTextString(docBase, ta.getNimi()));
                        div.appendChild(nimiEl);

                        Element kohdeEl = docBase.getDocument().createElement("p");
                        kohdeEl.setTextContent(getTextString(docBase, kohde));
                        div.appendChild(kohdeEl);

                        Element ul = docBase.getDocument().createElement("ul");
                        ta.getTavoitteet().forEach(tavoite -> {
                            Element li = docBase.getDocument().createElement("li");
                            Element liCite = docBase.getDocument().createElement("div");
                            liCite.setTextContent(getTextString(docBase, tavoite));
                            li.appendChild(liCite);
                            ul.appendChild(li);
                        });

                        div.appendChild(ul);
                        docBase.getBodyElement().appendChild(div);
                    }
                });
            }
        }

        // Arviointi
        Lops2019ArviointiDto arviointi = oa.getArviointi();
        if (Optional.ofNullable(arviointi).map(Lops2019ArviointiDto::getKuvaus).isPresent()) {
            addTeksti(docBase, messages.translate("arviointi", docBase.getKieli()), "h6");
            addLokalisoituteksti(docBase, arviointi.getKuvaus(), "div");
        }

        docBase.getGenerator().increaseDepth();

        // oppimäärät
        oa.getOppimaarat().stream()
                .sorted()
                .forEach(om -> {
            addOppiaine(docBase, om);
            docBase.getGenerator().increaseNumber();
        });

        // moduulit

        if (oa.getModuulit().stream().anyMatch(Lops2019ModuuliBaseDto::getPakollinen)) {
            addTeksti(docBase, messages.translate("pakolliset-moduulit", docBase.getKieli()), "h5");

            oa.getModuulit().stream().filter(Lops2019ModuuliBaseDto::getPakollinen).forEach(moduuli -> {
                addModuuli(docBase, moduuli);
            });
        }

        if (oa.getModuulit().stream().anyMatch(moduuli -> !moduuli.getPakollinen())) {
            addTeksti(docBase, messages.translate("valinnaiset-moduulit", docBase.getKieli()), "h5");

            oa.getModuulit().stream().filter(moduuli -> !moduuli.getPakollinen()).forEach(moduuli -> {
                addModuuli(docBase, moduuli);
            });
        }

        docBase.getGenerator().increaseNumber();
        docBase.getGenerator().decreaseDepth();
    }

    private void addModuuli(DokumenttiPeruste docBase, Lops2019ModuuliDto moduuli) {
        // Moduulin nimi
        StringBuilder nimiBuilder = new StringBuilder();
        String nimi = getTextString(docBase, moduuli.getNimi());
        nimiBuilder.append(StringUtils.isEmpty(nimi)
                ? messages.translate("nimeton-moduuli", docBase.getKieli())
                : nimi);
        nimiBuilder.append(", ");
        nimiBuilder.append(moduuli.getLaajuus().stripTrailingZeros().toPlainString());
        nimiBuilder.append(" ");
        nimiBuilder.append(messages.translate("op", docBase.getKieli()));

        KoodiDto koodi = moduuli.getKoodi();
        if (koodi != null && koodi.getArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(koodi.getArvo());
            nimiBuilder.append(")");
        }
        addTeksti(docBase, nimiBuilder.toString(), "h5");

        // Moduulin tavoitteet
        Lops2019ModuuliTavoiteDto tavoitteet = moduuli.getTavoitteet();
        if (!ObjectUtils.isEmpty(tavoitteet)) {
            addTeksti(docBase, messages.translate("tavoitteet", docBase.getKieli()), "h6");
            Element div = docBase.getDocument().createElement("div");

            LokalisoituTekstiDto kohde = tavoitteet.getKohde();
            if (kohde != null) {
                // Kohde
                Element kohdeEl = docBase.getDocument().createElement("p");
                kohdeEl.setTextContent(getTextString(docBase, kohde));
                div.appendChild(kohdeEl);
            }
            // Tavoitteet
            div.appendChild(getList(docBase, moduuli.getTavoitteet().getTavoitteet()));
            docBase.getBodyElement().appendChild(div);
        }

        // Moduulin keskeiset sisällöt
        List<Lops2019ModuuliSisaltoDto> sisallot = moduuli.getSisallot();
        if (!ObjectUtils.isEmpty(sisallot)) {
            addTeksti(docBase, messages.translate("keskeiset-sisallot", docBase.getKieli()), "h6");
            Element div = docBase.getDocument().createElement("div");

            sisallot.stream()
                    .filter(Objects::nonNull)
                    .forEach(sisalto -> {
                        LokalisoituTekstiDto kohde = sisalto.getKohde();
                        if (kohde != null) {
                            // Kohde
                            Element kohdeEl = docBase.getDocument().createElement("p");
                            kohdeEl.setTextContent(getTextString(docBase, kohde));
                            div.appendChild(kohdeEl);
                        }

                        // Sisallöt
                        if (!ObjectUtils.isEmpty(sisalto.getSisallot())) {
                            div.appendChild(getList(docBase, sisalto.getSisallot()));
                        }
                    });
            docBase.getBodyElement().appendChild(div);
        }
    }

    private void addPerusopetus(DokumenttiPeruste docBase) {
        PerusopetuksenPerusteenSisaltoDto perusopetuksenPerusteenSisaltoDto = docBase.getPeruste().getPerusopetuksenPerusteenSisalto();
        Set<VuosiluokkaKokonaisuusDto> vuosiluokat = perusopetuksenPerusteenSisaltoDto.getVuosiluokkakokonaisuudet();

        if (!ObjectUtils.isEmpty(vuosiluokat)) {
            vuosiluokat.stream()
                    .sorted(Comparator.comparing(VuosiluokkaKokonaisuusDto::getId))
                    .forEach(vuosiluokka -> {
                        addHeader(docBase, getTextString(docBase, vuosiluokka.getNimi()));
                        docBase.getGenerator().increaseDepth();

                        if (vuosiluokka.getTehtava() != null) {
                            addTeksti(docBase, getTextString(docBase, vuosiluokka.getTehtava().getOtsikko()), "h5");
                            addLokalisoituteksti(docBase, vuosiluokka.getTehtava().getTeksti(), "div");
                        }

                        if (vuosiluokka.getSiirtymaEdellisesta() != null) {
                            addTeksti(docBase, getTextString(docBase, vuosiluokka.getSiirtymaEdellisesta().getOtsikko()), "h5");
                            addLokalisoituteksti(docBase, vuosiluokka.getSiirtymaEdellisesta().getTeksti(), "div");
                        }

                        if (vuosiluokka.getSiirtymaSeuraavaan() != null) {
                            addTeksti(docBase, getTextString(docBase, vuosiluokka.getSiirtymaSeuraavaan().getOtsikko()), "h5");
                            addLokalisoituteksti(docBase, vuosiluokka.getSiirtymaSeuraavaan().getTeksti(), "div");
                        }

                        if (vuosiluokka.getLaajaalainenOsaaminen() != null) {
                            addTeksti(docBase, getTextString(docBase, vuosiluokka.getLaajaalainenOsaaminen().getOtsikko()), "h5");
                            addLokalisoituteksti(docBase, vuosiluokka.getLaajaalainenOsaaminen().getTeksti(), "div");
                        }

                        // Tekstikappaleet
                        if (!ObjectUtils.isEmpty(vuosiluokka.getVapaatTekstit())) {
                            vuosiluokka.getVapaatTekstit().forEach(vt -> {
                                addTeksti(docBase, getTextString(docBase, vt.getNimi()), "h5");
                                addLokalisoituteksti(docBase, vt.getTeksti(), "div");
                            });
                            docBase.getGenerator().increaseNumber();
                        }

                        // Laaja-alaiset osaamiset
                        if (!ObjectUtils.isEmpty(vuosiluokka.getLaajaalaisetOsaamiset())) {
                            addHeader(docBase, messages.translate("laaja-alaisen-osaamisen-osa-alueet", docBase.getKieli()));
                            vuosiluokka.getLaajaalaisetOsaamiset().stream()
                                    .sorted(Comparator.comparing(lao -> docBase.getLaajaAlainenOsaaminen(lao.getLaajaalainenOsaaminen().getIdLong()).get().getNimi().get(docBase.getKieli())))
                                    .forEach(lao -> {
                                        Optional<LaajaalainenOsaaminenDto> perusteenLaoDto = docBase.getLaajaAlainenOsaaminen(lao.getLaajaalainenOsaaminen().getIdLong());
                                        if (perusteenLaoDto.isPresent()) {
                                            addLokalisoituteksti(docBase, perusteenLaoDto.get().getNimi(), "h5");
                                            addLokalisoituteksti(docBase, lao.getKuvaus(), "div");
                                        }
                                    });
                            docBase.getGenerator().increaseNumber();
                        }

                        // Paikallaisesti päätettävät asiat
                        if (!ObjectUtils.isEmpty(vuosiluokka.getPaikallisestiPaatettavatAsiat()) && vuosiluokka.getPaikallisestiPaatettavatAsiat().getTeksti() != null) {
                            addHeader(docBase, messages.translate("paikallisesti-paatettavat-asiat", docBase.getKieli()));
                            addLokalisoituteksti(docBase, vuosiluokka.getPaikallisestiPaatettavatAsiat().getTeksti(), "div");
                            docBase.getGenerator().increaseNumber();
                        }

                        // Vuosiluokan oppiaineet
                        if (!ObjectUtils.isEmpty(perusopetuksenPerusteenSisaltoDto.getOppiaineet())) {
                            addHeader(docBase, messages.translate("oppiaineet", docBase.getKieli()));
                            docBase.getGenerator().increaseDepth();

                            // Oppiaineet
                            perusopetuksenPerusteenSisaltoDto.getOppiaineet().stream()
                                    .filter(oppiaine -> oppiaine.getVuosiluokkakokonaisuus(vuosiluokka.getId()).isPresent())
                                    .filter(oppiaine -> oppiaine.getNimi().isPresent())
                                    .sorted(Comparator.comparing(oppiaine -> LokalisoituTekstiDto.getOrDefault(oppiaine.getNimiOrDefault(LokalisoituTekstiDto.of("")), docBase.getKieli(), "")))
                                    .sorted(Comparator.comparing(oppiaine -> oppiaine.getJnroOrDefault(99L)))
                                    .forEach(oppiaine -> {
                                        addHeader(docBase, getTextString(docBase, getOptionalValue(oppiaine.getNimi())));

                                        if (oppiaine.getTehtava().isPresent()) {
                                            addTeksti(docBase, getTextString(docBase, oppiaine.getTehtava().get().getOtsikko()), "h6");
                                            addTeksti(docBase, getTextString(docBase, oppiaine.getTehtava().get().getTeksti()), "div");
                                        }

                                        if (!CollectionUtils.isEmpty(oppiaine.getVapaatTekstit())) {
                                            oppiaine.getVapaatTekstit().forEach(vapaaTeksti -> {
                                                addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h6");
                                                addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "div");
                                            });
                                        }

                                        // Oppiaineen vuosiluokkakokonaisuus
                                        oppiaine.getVuosiluokkakokonaisuus(vuosiluokka.getId())
                                                .ifPresent(oppiaineenVuosiluokkaKokonaisuusDto -> addVuosiluokkakokonaisuus(docBase, oppiaine.getKohdealueet(), oppiaineenVuosiluokkaKokonaisuusDto));

                                        // Oppimäärät
                                        addOppimaarat(docBase, oppiaine.getOppimaarat(), vuosiluokka.getId());

                                        docBase.getGenerator().increaseNumber();
                                    });
                            docBase.getGenerator().decreaseDepth();
                            docBase.getGenerator().increaseNumber();
                        }
                        docBase.getGenerator().decreaseDepth();
                        docBase.getGenerator().increaseNumber();
                    });
        }
    }

    private void addOppimaarat(DokumenttiPeruste docBase, Set<OppiaineDto> oppimaarat, Long vuosiluokkaId) {
        if (!ObjectUtils.isEmpty(oppimaarat)) {
            oppimaarat.stream()
                    .filter(oppimaara -> oppimaara.getNimi().isPresent())
                    .sorted(Comparator.comparing(oppiaine -> LokalisoituTekstiDto.getOrDefault(oppiaine.getNimiOrDefault(LokalisoituTekstiDto.of("")), docBase.getKieli(), "")))
                    .sorted(Comparator.comparing(oppiaine -> oppiaine.getJnroOrDefault(99L)))
                    .forEach(oppimaara -> {
                        // Oppimäärän nimi
                        addTeksti(docBase, getTextString(docBase, getOptionalValue(oppimaara.getNimi())), "h5");
                        // Tekstikappale
                        oppimaara.getVapaatTekstit().forEach(vt -> {
                            addTeksti(docBase, getTextString(docBase, vt.getNimi()), "h6");
                            addLokalisoituteksti(docBase, vt.getTeksti(), "div");
                        });

                        // Oppimäärän vuosiluokkakokonaisuus
                        OppiaineenVuosiluokkaKokonaisuusDto vuosiluokkaKokonaisuus = oppimaara.getVuosiluokkakokonaisuudet().stream()
                                .filter(vlk -> vlk.getVuosiluokkaKokonaisuus() != null && vuosiluokkaId.equals(vlk.getVuosiluokkaKokonaisuus().getIdLong()))
                                .findFirst()
                                .orElse(null);

                        addVuosiluokkakokonaisuus(docBase, oppimaara.getKohdealueet(), vuosiluokkaKokonaisuus);
                    });
        }
    }

    private void addVuosiluokkakokonaisuus(DokumenttiPeruste docBase, Set<OpetuksenKohdealueDto> oppiaineenKohdealueet, OppiaineenVuosiluokkaKokonaisuusDto oppiaineenVuosiluokkaKokonaisuus) {
        if (oppiaineenVuosiluokkaKokonaisuus != null) {

            if (oppiaineenVuosiluokkaKokonaisuus.getTehtava().isPresent()) {
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getTehtava().get().getOtsikko()), "h6");
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getTehtava().get().getTeksti()), "div");
            }

            if (oppiaineenVuosiluokkaKokonaisuus.getTyotavat().isPresent()) {
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getTyotavat().get().getOtsikko()), "h6");
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getTyotavat().get().getTeksti()), "div");
            }

            if (oppiaineenVuosiluokkaKokonaisuus.getOhjaus().isPresent()) {
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getOhjaus().get().getOtsikko()), "h6");
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getOhjaus().get().getTeksti()), "div");
            }

            if (oppiaineenVuosiluokkaKokonaisuus.getArviointi().isPresent()) {
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getArviointi().get().getOtsikko()), "h6");
                addTeksti(docBase, getTextString(docBase, oppiaineenVuosiluokkaKokonaisuus.getArviointi().get().getTeksti()), "div");
            }

            // Tekstikappaleet
            oppiaineenVuosiluokkaKokonaisuus.getVapaatTekstit().forEach(vt -> {
                addTeksti(docBase, getTextString(docBase, vt.getNimi()), "h6");
                addTeksti(docBase, getTextString(docBase, vt.getTeksti()), "div");
            });

            oppiaineenVuosiluokkaKokonaisuus.getSisaltoalueet()
                    .stream()
                    .sorted(Comparator.comparing(sisaltoalue -> getTextString(docBase, sisaltoalue.getNimi().get())))
                    .forEach(sisaltoalue -> {
                addTeksti(docBase, getTextString(docBase, getOptionalValue(sisaltoalue.getNimi())), "h6");
                addTeksti(docBase, getTextString(docBase, getOptionalValue(sisaltoalue.getKuvaus())), "div");
            });

            // tavoitealueella ryhmittely puuttuu
            if (!ObjectUtils.isEmpty(oppiaineenVuosiluokkaKokonaisuus.getTavoitteet())) {
                addTeksti(docBase, messages.translate("opetuksen-tavoitteet", docBase.getKieli()), "h5");

                oppiaineenVuosiluokkaKokonaisuus.getTavoitteet().forEach(tavoite -> {
                    addTeksti(docBase, getTextString(docBase, tavoite.getTavoite().get()), "h5");

                    if (!ObjectUtils.isEmpty(tavoite.getOppiaineenTavoitteenOpetuksenTavoitteet())) {
                        addTeksti(docBase, messages.translate("opetuksen-tavoitteet", docBase.getKieli()), "h6");
                        tavoite.getOppiaineenTavoitteenOpetuksenTavoitteet().forEach(ot -> addTeksti(docBase, getTextString(docBase, ot.getTavoite().get()), "p"));
                    }

                    if (!tavoite.getKohdealueet().isEmpty()) {
                        OpetuksenKohdealueDto opetuksenKohdealueDto = oppiaineenKohdealueet.stream()
                                .filter(kohdealue -> kohdealue.getId().equals(tavoite.getKohdealueet().stream().findFirst().get().getIdLong())).findAny().get();
                        addTeksti(docBase, messages.translate("tavoitealue", docBase.getKieli()), "h6");
                        addTeksti(docBase, getTextString(docBase, opetuksenKohdealueDto.nimi.get()), "div");
                        addTeksti(docBase, "", "p");
                    }

                    if (tavoite.getTavoitteistaJohdetutOppimisenTavoitteet().isPresent()) {
                        addTeksti(docBase, messages.translate("tavoitteista-johdetut-oppimisen-tavoitteet", docBase.getKieli()), "h6");
                        addTeksti(docBase, getTextString(docBase, tavoite.getTavoitteistaJohdetutOppimisenTavoitteet().get()), "div");
                    }

                    if (!tavoite.getSisaltoalueet().isEmpty()) {
                        addTeksti(docBase, messages.translate("sisaltoalueet", docBase.getKieli()), "h6");

                        oppiaineenVuosiluokkaKokonaisuus.getSisaltoalueet().stream()
                                .filter(sisaltoalue -> tavoite.getSisaltoalueet().stream().map(Reference::getIdLong).collect(Collectors.toList()).contains(sisaltoalue.getId()))
                                .sorted(Comparator.comparing(sisaltoalue -> getTextString(docBase, sisaltoalue.getNimi().get())))
                                .forEach(sisaltoalue -> {
                            addTeksti(docBase, getTextString(docBase, sisaltoalue.getNimi().get()), "p");
                        });

                        addTeksti(docBase, "", "p");
                    }

                    if (!tavoite.getLaajattavoitteet().isEmpty()) {
                        addTeksti(docBase, messages.translate("laaja-alaisen-osaamisen-osa-alueet", docBase.getKieli()), "h6");

                        docBase.getPeruste().getPerusopetuksenPerusteenSisalto().getLaajaalaisetosaamiset().stream()
                                .filter(lao -> tavoite.getLaajattavoitteet().stream().map(Reference::getIdLong).collect(Collectors.toList()).contains(lao.getId()))
                                .sorted(Comparator.comparing(lao -> getTextString(docBase, lao.getNimi())))
                                .forEach(lao -> {
                                    addTeksti(docBase, getTextString(docBase, lao.getNimi()), "p");
                        });

                        addTeksti(docBase, "", "p");
                    }

                    if (tavoite.getArvioinninKuvaus().isPresent()) {
                        addTeksti(docBase, messages.translate("arvioinnin-kohde", docBase.getKieli()), "h6");
                        addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninKuvaus().get()), "div");
                    }

                    if (tavoite.getArvioinninOtsikko().isPresent()) {
                        addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninOtsikko().get()), "h6");
                    }

                    if (!tavoite.getArvioinninkohteet().isEmpty()) {
                        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
                        taulukko.addOtsikkosarakkeet(
                                messages.translate("arvosana", docBase.getKieli()),
                                messages.translate("osaamisen-kuvaus", docBase.getKieli())
                        );

                        tavoite.getArvioinninkohteet()
                                .stream()
                                .sorted(Comparator.comparing(arvioinninKohde -> arvioinninKohde.getArvosana().get()))
                                .forEach(arvioinninKohde -> {
                            DokumenttiRivi rivi = new DokumenttiRivi();
                            rivi.addSarake(
                                    messages.translate("osaamisen-kuvaus-arvosanalle-" + arvioinninKohde.getArvosana().get(), docBase.getKieli()),
                                    getTextString(docBase, arvioinninKohde.getOsaamisenKuvaus().orElse(LokalisoituTekstiDto.of(""))));
                            taulukko.addRivi(rivi);
                        });

                        taulukko.addToDokumentti(docBase);
                    }

                    if (tavoite.getVapaaTeksti().isPresent()) {
                        addTeksti(docBase, getTextString(docBase, tavoite.getVapaaTeksti().get()), "div");
                    }
                });
            }
        }
    }

    private String addSisaltoalueetList(DokumenttiPeruste docBase, List<KeskeinenSisaltoalueDto> sisaltoalueet, OpetuksenTavoiteDto tavoite) {
        StringJoiner joiner = new StringJoiner (", ");
        sisaltoalueet.forEach(sa -> tavoite.getSisaltoalueet().forEach(tavoiteSa -> {
            if (sa.getId().equals(tavoiteSa.getIdLong())) {
                String nimi = getTextString(docBase, getOptionalValue(sa.getNimi()));
                // TODO: otetaan tavoitteista ja laajiksista vain etutunnisteet eli S1, L1 jne. Voiko näihin luottaa?
                joiner.add(nimi.substring(0, 2));
            }
        }));
        return joiner.toString();
    }

    private String addLaajaAlaisetList(DokumenttiPeruste docBase, Set<Reference> laajattavoitteet) {
        StringJoiner joiner = new StringJoiner (", ");
        docBase.getPeruste().getPerusopetuksenPerusteenSisalto().getLaajaalaisetosaamiset().forEach(lao -> laajattavoitteet.forEach(laoRef -> {
            if (lao.getId().equals(laoRef.getIdLong())) {
                String nimi = getTextString(docBase, lao.getNimi());
                // TODO: otetaan tavoitteista ja laajiksista vain etutunnisteet eli S1, L1 jne. Voiko näihin luottaa?
                joiner.add(nimi.substring(0, 2));
            }
        }));
        return joiner.toString();
    }

    private void addPerusteenOsat(DokumenttiPeruste docBase, PerusteenOsaViiteDto.Laaja parent) {
        if (parent == null) {
            return;
        }
        for (PerusteenOsaViiteDto.Laaja lapsi : parent.getLapset()) {
            PerusteenOsaDto po = lapsi.getPerusteenOsa();
            if (po == null) {
                continue;
            }
            if (po instanceof TaiteenalaDto) {
                TaiteenalaDto taiteenala = (TaiteenalaDto) po;
                addTaiteenala(docBase, taiteenala);
            } else if (po instanceof OpintokokonaisuusDto) {
                OpintokokonaisuusDto opintokokonaisuus = (OpintokokonaisuusDto) po;
                addOpintokokonaisuus(docBase, opintokokonaisuus, po, lapsi);
            } else if (po instanceof TavoitesisaltoalueDto) {
                TavoitesisaltoalueDto tavoitesisaltoalue = (TavoitesisaltoalueDto) po;
                addTavoitesisaltoalue(docBase, tavoitesisaltoalue, po, lapsi);
            } else if (po instanceof KoulutuksenOsaDto) {
                KoulutuksenOsaDto koulutuksenOsa = (KoulutuksenOsaDto) po;
                addKoulutuksenOsa(docBase, koulutuksenOsa, po, lapsi);
            } else if (po instanceof TuvaLaajaAlainenOsaaminenDto) {
                TuvaLaajaAlainenOsaaminenDto tuvaLaajaAlainenOsaaminen = (TuvaLaajaAlainenOsaaminenDto) po;
                addTuvaLaajaAlainenOsaaminen(docBase, tuvaLaajaAlainenOsaaminen, po, lapsi);
            } else if (po instanceof KotoKielitaitotasoDto) {
                KotoKielitaitotasoDto kotoKielitaitotaso = (KotoKielitaitotasoDto) po;
                addKotoSisalto(docBase, kotoKielitaitotaso, po, lapsi);
            } else if (po instanceof KotoOpintoDto) {
                KotoOpintoDto kotoOpinto = (KotoOpintoDto) po;
                addKotoSisalto(docBase, kotoOpinto, po, lapsi);
            } else if (po instanceof KotoLaajaAlainenOsaaminenDto) {
                KotoLaajaAlainenOsaaminenDto kotoLao = (KotoLaajaAlainenOsaaminenDto) po;
                addKotoLaajaAlainenOsaaminen(docBase, kotoLao, po, lapsi);
            } else if (po instanceof TekstiKappaleDto) {
                TekstiKappaleDto tk = (TekstiKappaleDto) po;
                if (!tk.getLiite()) {
                    addTekstikappale(docBase, tk, po, lapsi);
                }
            }
        }
    }

    private void addTekstikappaleLiitteet(DokumenttiPeruste docBase, PerusteenOsaViiteDto.Laaja parent) {
        if (parent == null) {
            return;
        }
        for (PerusteenOsaViiteDto.Laaja lapsi : parent.getLapset()) {
            PerusteenOsaDto po = lapsi.getPerusteenOsa();
            if (po == null) {
                continue;
            }
            if (po instanceof TekstiKappaleDto) {
                TekstiKappaleDto tk = (TekstiKappaleDto) po;
                if (tk.getLiite()) {
                    addTekstikappale(docBase, tk, po, lapsi);
                }
            }

            addTekstikappaleLiitteet(docBase, lapsi);
        }
    }

    private void addTuvaLaajaAlainenOsaaminen(DokumenttiPeruste docBase, TuvaLaajaAlainenOsaaminenDto tuvaLaajaAlainenOsaaminen, PerusteenOsaDto po, PerusteenOsaViiteDto.Laaja lapsi) {

        KoodiDto nimiKoodiDto = tuvaLaajaAlainenOsaaminen.getNimiKoodi();
        if (nimiKoodiDto != null) {
            addHeader(docBase, getTextString(docBase, nimiKoodiDto.getNimi()));
        } else {
            addHeader(docBase, messages.translate("docgen.nimeton.laaja_alainenosaaminen", docBase.getKieli()));
        }

        String teksti = getTextString(docBase, tuvaLaajaAlainenOsaaminen.getTeksti());
        addTeksti(docBase, teksti, "div");

        docBase.getGenerator().increaseDepth();

        // Rekursiivisesti
        addPerusteenOsat(docBase, lapsi);

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addTekstikappale(DokumenttiPeruste docBase, TekstiKappaleDto tk, PerusteenOsaDto po, PerusteenOsaViiteDto.Laaja lapsi) {
        PerusteenOsaTunniste tunniste = po.getTunniste();
        if (tunniste != PerusteenOsaTunniste.NORMAALI
                && tunniste != PerusteenOsaTunniste.LAAJAALAINENOSAAMINEN
                && tunniste != PerusteenOsaTunniste.RAKENNE) {
            String nimi = getTextString(docBase, tk.getNimi());
            addHeader(docBase, nimi);

            String teksti = getTextString(docBase, tk.getTeksti());
            addTeksti(docBase, teksti, "div");

            docBase.getGenerator().increaseDepth();

            // Rekursiivisesti
            addPerusteenOsat(docBase, lapsi);

            docBase.getGenerator().decreaseDepth();
            docBase.getGenerator().increaseNumber();

        } else if (tunniste == PerusteenOsaTunniste.LAAJAALAINENOSAAMINEN && docBase.getPeruste().getAipeOpetuksenPerusteenSisalto() != null) {
            AIPEOpetuksenSisaltoDto aipeOpetuksenSisalto = docBase.getPeruste().getAipeOpetuksenPerusteenSisalto();

            List<LaajaalainenOsaaminenDto> laajaalaisetosaamiset = aipeOpetuksenSisalto.getLaajaalaisetosaamiset();
            if (laajaalaisetosaamiset.size() > 0) {
                addHeader(docBase, messages.translate("docgen.laaja_alaiset_osaamiset.title", docBase.getKieli()));

                laajaalaisetosaamiset.forEach(laajaalainenOsaaminen -> {
                    String nimi = getTextString(docBase, laajaalainenOsaaminen.getNimi());
                    addTeksti(docBase, nimi, "h5");

                    String teksti = getTextString(docBase, laajaalainenOsaaminen.getKuvaus());
                    addTeksti(docBase, teksti, "div");
                });
            }
            docBase.getGenerator().increaseNumber();
        }
    }

    private void addTaiteenala(DokumenttiPeruste docBase,
                               TaiteenalaDto taiteenala) {
        // Nimi
        LokalisoituTekstiDto nimi = taiteenala.getNimi();
        addHeader(docBase, getTextString(docBase, nimi));

        // Kuvaus
        String kuvaus = getTextString(docBase, taiteenala.getTeksti());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        // Koodi
        KoodiDto koodi = taiteenala.getKoodi();
        if (koodi != null) {
            addTeksti(docBase, messages.translate("docgen.taiteenala.koodi", docBase.getKieli()), "h5");
            addTeksti(docBase, koodi.getArvo(), "div");
        }

        // Aikuisten opetus
        addTaiteenalaSisalto(docBase, taiteenala.getAikuistenOpetus(), "docgen.taiteenala.aikuisten-opetus");

        // Kasvatus
        addTaiteenalaSisalto(docBase, taiteenala.getKasvatus(), "docgen.taiteenala.kasvatus");

        // Oppimisen arviointi
        addTaiteenalaSisalto(docBase, taiteenala.getOppimisenArviointiOpetuksessa(), "docgen.taiteenala.oppimisen-arvionti");

        // Teemaopinnot
        addTaiteenalaSisalto(docBase, taiteenala.getTeemaopinnot(), "docgen.taiteenala.teemaopinnot");

        // Työtavat opetuksessa
        addTaiteenalaSisalto(docBase, taiteenala.getTyotavatOpetuksessa(), "docgen.taiteenala.tyotavat");

        // Yhteiset opinnot
        addTaiteenalaSisalto(docBase, taiteenala.getYhteisetOpinnot(), "docgen.taiteenala.yhteiset-opinnot");

        taiteenala.getVapaatTekstit().forEach(vapaaTeksti -> {
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "div");
        });

        docBase.getGenerator().increaseNumber();
    }

    private void addOpintokokonaisuus(DokumenttiPeruste docBase,
                                      OpintokokonaisuusDto opintokokonaisuus,
                                      PerusteenOsaDto po,
                                      PerusteenOsaViiteDto.Laaja lapsi) {
        // Nimi
        KoodiDto nimiKoodiDto = opintokokonaisuus.getNimiKoodi();
        if (nimiKoodiDto != null) {
            String laajuusSuffix = ", " + opintokokonaisuus.getMinimilaajuus() + " " + messages.translate("docgen.laajuus.op", docBase.getKieli());
            addHeader(docBase, getTextString(docBase, nimiKoodiDto.getNimi()) + laajuusSuffix);
        } else {
            addHeader(docBase, messages.translate("docgen.opintokokonaisuus.nimeton-opintokokonaisuus", docBase.getKieli()));
        }

        // Kuvaus
        String kuvaus = getTextString(docBase, opintokokonaisuus.getKuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        addTeksti(docBase, messages.translate("docgen.opetuksen-tavoitteet.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getOpetuksenTavoiteOtsikko()), "h6");

        Element tavoitteetEl = docBase.getDocument().createElement("ul");

        opintokokonaisuus.getOpetuksenTavoitteet().forEach(tavoite -> {
            Element tavoiteEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, tavoite.getNimi());
            tavoiteEl.setTextContent(rivi);

            tavoitteetEl.appendChild(tavoiteEl);
        });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");
        addTeksti(docBase, messages.translate("docgen.opintokokonaisuus.opiskelijan-osaamisen-arvioinnin-kohteet", docBase.getKieli()), "h6");

        Element arvioinnitEl = docBase.getDocument().createElement("ul");
        opintokokonaisuus.getArvioinnit().forEach(arviointi -> {
            Element arviointiEl = docBase.getDocument().createElement("li");

            String rivi = getTextString(docBase, arviointi);
            arviointiEl.setTextContent(rivi);

            arvioinnitEl.appendChild(arviointiEl);
        });
        docBase.getBodyElement().appendChild(arvioinnitEl);
        docBase.getGenerator().increaseDepth();
        addPerusteenOsat(docBase, lapsi);
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addTavoitesisaltoalue(DokumenttiPeruste docBase, TavoitesisaltoalueDto tavoitesisaltoalue, PerusteenOsaDto po,
                                       PerusteenOsaViiteDto.Laaja lapsi) {
        // Nimi
        KoodiDto nimiKoodiDto = tavoitesisaltoalue.getNimiKoodi();
        if (nimiKoodiDto != null) {
            addHeader(docBase, getTextString(docBase, nimiKoodiDto.getNimi()));
        } else {
            addHeader(docBase, messages.translate("docgen.opintokokonaisuus.nimeton-tavoitesisaltoalue", docBase.getKieli()));
        }

        // Teksti
        String teksti = getTextString(docBase, tavoitesisaltoalue.getTeksti());
        if (!ObjectUtils.isEmpty(teksti)) {
            addTeksti(docBase, teksti, "div");
        }

        addTeksti(docBase, messages.translate("docgen.tavoitteet-ja-keskeiset-sisallot.title", docBase.getKieli()), "h5");

        DokumenttiTaulukko tavoitesisaltoalueTaulukko = new DokumenttiTaulukko();
        tavoitesisaltoalueTaulukko.addOtsikkosarakkeet(
                messages.translate("docgen.tavoitteet.title", docBase.getKieli()),
                messages.translate("docgen.keskeiset-sisaltoalueet.title", docBase.getKieli()));

        tavoitesisaltoalue.getTavoitealueet().forEach(tavoitealue -> {
            DokumenttiRivi rivi = new DokumenttiRivi();

            if (tavoitealue.getTavoiteAlueTyyppi().equals(TavoiteAlueTyyppi.OTSIKKO)) {
                rivi.addSarake(getTextString(docBase, tavoitealue.getOtsikko().getNimi()));
                rivi.setColspan(2);
                rivi.setTyyppi(DokumenttiRiviTyyppi.SUBHEADER);
            }

            if (tavoitealue.getTavoiteAlueTyyppi().equals(TavoiteAlueTyyppi.TAVOITESISALTOALUE)) {

                if (!CollectionUtils.isEmpty(tavoitealue.getTavoitteet())) {
                    StringBuffer sarake = new StringBuffer();
                    tavoitealue.getTavoitteet().forEach(tavoite -> {
                        sarake.append(tagTeksti(getTextString(docBase, tavoite.getNimi()), "div"));
                    });
                    rivi.addSarake(sarake.toString());
                }

                if (!CollectionUtils.isEmpty(tavoitealue.getKeskeisetSisaltoalueet())) {
                    StringBuffer sarake = new StringBuffer();
                    tavoitealue.getKeskeisetSisaltoalueet().forEach(keskeinenSisaltoalue -> {
                        sarake.append(tagTeksti(getTextString(docBase, keskeinenSisaltoalue), "div"));
                    });
                    rivi.addSarake(sarake.toString());
                }
            }
            tavoitesisaltoalueTaulukko.addRivi(rivi);
        });

        tavoitesisaltoalueTaulukko.addToDokumentti(docBase);

        docBase.getGenerator().increaseDepth();
        addPerusteenOsat(docBase, lapsi);
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addKoulutuksenOsa(DokumenttiPeruste docBase, KoulutuksenOsaDto koulutuksenOsa, PerusteenOsaDto po,
                                   PerusteenOsaViiteDto.Laaja lapsi) {

        Integer minimiLaajuus = koulutuksenOsa.getLaajuusMinimi() != null ? koulutuksenOsa.getLaajuusMinimi() : koulutuksenOsa.getLaajuusMaksimi() != null ? koulutuksenOsa.getLaajuusMaksimi() : 0;
        Integer maksimiLaajuus = koulutuksenOsa.getLaajuusMaksimi() != null ? koulutuksenOsa.getLaajuusMaksimi() : koulutuksenOsa.getLaajuusMinimi() != null ? koulutuksenOsa.getLaajuusMinimi() : 0;
        String nimi = getTextString(docBase, koulutuksenOsa.getNimi());
        if (koulutuksenOsa.getNimiKoodi() != null) {
            nimi = getTextString(docBase, koulutuksenOsa.getNimiKoodi().getNimi());
        }

        if (minimiLaajuus.compareTo(maksimiLaajuus) == 0) {
            String nimiSuffix = String.format(", %d %s", minimiLaajuus, messages.translate("docgen.laajuus.vk", docBase.getKieli()));
            addHeader(docBase, nimi + nimiSuffix);
        } else {
            String nimiSuffix = String.format(", %d - %d %s", minimiLaajuus, maksimiLaajuus, messages.translate("docgen.laajuus.vk", docBase.getKieli()));
            addHeader(docBase, nimi + nimiSuffix);
        }

        addTeksti(docBase, messages.translate("docgen.koulutustyyppi.title", docBase.getKieli()), "h5");
        if (koulutuksenOsa.getKoulutusOsanKoulutustyyppi() != null) {
            addTeksti(docBase, messages.translate("docgen.koulutuksenOsa.koulutustyyppi." + koulutuksenOsa.getKoulutusOsanKoulutustyyppi(), docBase.getKieli()), "div");
        }

        String kuvaus = getTextString(docBase, koulutuksenOsa.getKuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        addTeksti(docBase, messages.translate("docgen.opetuksen-tavoitteet.title", docBase.getKieli()), "h5");
        addTeksti(docBase, messages.translate("docgen.info.opiskelija", docBase.getKieli()), "h6");

        Element tavoitteetEl = docBase.getDocument().createElement("ul");

        koulutuksenOsa.getTavoitteet().forEach(tavoite -> {
            Element tavoiteEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, tavoite);
            tavoiteEl.setTextContent(rivi);
            tavoitteetEl.appendChild(tavoiteEl);
        });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        String laajaAlainenOsaaminenKuvaus = getTextString(docBase, koulutuksenOsa.getLaajaAlaisenOsaamisenKuvaus());
        if (!ObjectUtils.isEmpty(laajaAlainenOsaaminenKuvaus)) {
            addTeksti(docBase, messages.translate("docgen.laaja_alainen_osaaminen.title", docBase.getKieli()), "h5");
            addTeksti(docBase, laajaAlainenOsaaminenKuvaus, "div");
        }

        String keskeinenSisalto = getTextString(docBase, koulutuksenOsa.getKeskeinenSisalto());
        if (!ObjectUtils.isEmpty(keskeinenSisalto)) {
            addTeksti(docBase, messages.translate("docgen.keskeinen-sisalto.title", docBase.getKieli()), "h5");
            addTeksti(docBase, keskeinenSisalto, "div");
        }

        String arvioinninKuvaus = getTextString(docBase, koulutuksenOsa.getArvioinninKuvaus());
        if (!ObjectUtils.isEmpty(arvioinninKuvaus)) {
            addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");
            addTeksti(docBase, arvioinninKuvaus, "div");
        }

        docBase.getGenerator().increaseDepth();
        addPerusteenOsat(docBase, lapsi);
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addKotoSisalto(DokumenttiPeruste docBase, KotoSisalto kotoSisalto, PerusteenOsaDto po,
                                PerusteenOsaViiteDto.Laaja lapsi) {

        KoodiDto nimiKoodi = kotoSisalto.getNimiKoodi();
        if (nimiKoodi != null) {
            addHeader(docBase, getTextString(docBase, nimiKoodi.getNimi()));
        } else {
            addHeader(docBase, messages.translate("nimeton-sisalto", docBase.getKieli()));
        }

        String kuvaus = getTextString(docBase, kotoSisalto.getKuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        kotoSisalto.getTaitotasot().forEach(taitotaso -> {

            String taitotasoNimi = getTextString(docBase, taitotaso.getNimi().getNimi());
            if (Optional.ofNullable(taitotaso.getTyoelamaOpintoMinimiLaajuus()).orElse(0) > 0 && Optional.ofNullable(taitotaso.getTyoelamaOpintoMaksimiLaajuus()).orElse(0) > 0) {
                taitotasoNimi += ", " + taitotaso.getTyoelamaOpintoMinimiLaajuus() + " - " + taitotaso.getTyoelamaOpintoMaksimiLaajuus() + " " + messages.translate("docgen.laajuus.op", docBase.getKieli());
            }
            addTeksti(docBase, taitotasoNimi, "h5");

            String tavoitteet = getTextString(docBase, taitotaso.getTavoitteet());
            addH6Teksti(tavoitteet, "docgen.tavoitteet.title", docBase);

            String kielenkayttotarkoitus = getTextString(docBase, taitotaso.getKielenkayttotarkoitus());
            String aihealueet = getTextString(docBase, taitotaso.getAihealueet());
            String viestintataidot = getTextString(docBase, taitotaso.getViestintataidot());
            String opiskelijantaidot = getTextString(docBase, taitotaso.getOpiskelijantaidot());

            String opiskelijanTyoelamataidot = getTextString(docBase, taitotaso.getOpiskelijanTyoelamataidot());
            String suullinenVastaanottaminen = getTextString(docBase, taitotaso.getSuullinenVastaanottaminen());
            String suullinenTuottaminen = getTextString(docBase, taitotaso.getSuullinenTuottaminen());
            String vuorovaikutusJaMediaatio = getTextString(docBase, taitotaso.getVuorovaikutusJaMediaatio());

            if (!ObjectUtils.isEmpty(kielenkayttotarkoitus)
                    || !ObjectUtils.isEmpty(aihealueet)
                    || !ObjectUtils.isEmpty(viestintataidot)
                    || !ObjectUtils.isEmpty(opiskelijantaidot)
                    || !ObjectUtils.isEmpty(opiskelijanTyoelamataidot)
                    || !ObjectUtils.isEmpty(suullinenVastaanottaminen)
                    || !ObjectUtils.isEmpty(suullinenTuottaminen)
                    || !ObjectUtils.isEmpty(vuorovaikutusJaMediaatio)) {
                addTeksti(docBase, messages.translate("docgen.opiskelijan_osaaminen.title", docBase.getKieli()), "h5");
            }

            addH6Teksti(kielenkayttotarkoitus, "docgen.kielenkayttotarkoitus.title", "docgen.info.opiskelija", docBase);
            addH6Teksti(aihealueet, "docgen.aihealueet.title", "docgen.info.opiskelija", docBase);
            addH6Teksti(viestintataidot, "docgen.viestintataidot.title", "docgen.info.opiskelija", docBase);
            addH6Teksti(opiskelijantaidot, "docgen.opiskelijantaidot.title", "docgen.info.opiskelija", docBase);

            addH6Teksti(opiskelijanTyoelamataidot, null, "docgen.info.opiskelija", docBase);
            addH6Teksti(suullinenVastaanottaminen, "docgen.suullinen_vastaanottaminen.title", "docgen.info.opiskelija", docBase);
            addH6Teksti(suullinenTuottaminen, "docgen.suullinen_tuottaminen.title", "docgen.info.opiskelija", docBase);
            addH6Teksti(vuorovaikutusJaMediaatio, "docgen.vuorovaikutus_ja_mediaatio.title", "docgen.info.opiskelija", docBase);
        });

        docBase.getGenerator().increaseDepth();
        addPerusteenOsat(docBase, lapsi);
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addKotoLaajaAlainenOsaaminen(DokumenttiPeruste docBase, KotoLaajaAlainenOsaaminenDto kotoLao, PerusteenOsaDto po,
                                              PerusteenOsaViiteDto.Laaja lapsi) {

        addHeader(docBase, getTextString(docBase, kotoLao.getNimi()));

        String kuvaus = getTextString(docBase, kotoLao.getYleiskuvaus());
        if (!ObjectUtils.isEmpty(kuvaus)) {
            addTeksti(docBase, kuvaus, "div");
        }

        kotoLao.getOsaamisAlueet().forEach(osaamisenAlue -> {
            KoodiDto osaamisalueNimi = osaamisenAlue.getKoodi();
            addTeksti(docBase, getTextString(docBase, osaamisalueNimi.getNimi()), "h5");

            String osaamisalueKuvaus = getTextString(docBase, osaamisenAlue.getKuvaus());
            if (!ObjectUtils.isEmpty(osaamisalueKuvaus)) {
                addTeksti(docBase, osaamisalueKuvaus, "div");
            }
        });

        docBase.getGenerator().increaseDepth();
        addPerusteenOsat(docBase, lapsi);
        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addH6Teksti(String text, String translationKey, DokumenttiPeruste docBase) {
        addH6Teksti(text, translationKey, null, docBase);
    }

    private void addH6Teksti(String text, String headerTranslationKey, String subHeaderTranslationKey, DokumenttiPeruste docBase) {
        if (!ObjectUtils.isEmpty(text)) {
            if (!ObjectUtils.isEmpty(headerTranslationKey)) {
                addTeksti(docBase, messages.translate(headerTranslationKey, docBase.getKieli()), "h6");
            }
            if (!ObjectUtils.isEmpty(subHeaderTranslationKey)) {
                addTeksti(docBase, messages.translate(subHeaderTranslationKey, docBase.getKieli()), "p");
            }
            addTeksti(docBase, text, "div");
        }
    }

    private void addTaiteenalaSisalto(DokumenttiPeruste docBase, KevytTekstiKappaleDto tekstiKappale, String placeholder) {
        if (tekstiKappale != null) {
            String teksti = getTextString(docBase, tekstiKappale.getTeksti());
            if (!ObjectUtils.isEmpty(teksti)) {
                String otsikko = getTextString(docBase, tekstiKappale.getNimi());
                if (!ObjectUtils.isEmpty(otsikko)) {
                    addTeksti(docBase, otsikko, "h5");
                } else {
                    addTeksti(docBase,
                            messages.translate(placeholder, docBase.getKieli()), "h5");
                }
                addTeksti(docBase, teksti, "div");
            }
        }
    }

    private void addAmmattitaitovaatimukset(
            DokumenttiPeruste docBase,
            Ammattitaitovaatimukset2019Dto ammattitaitovaatimukset2019,
            List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimuksetLista,
            LokalisoituTekstiDto ammattitaitovaatimukset) {

        String ammattitaitovaatimuksetText = getTextString(docBase, ammattitaitovaatimukset);

        if (!ObjectUtils.isEmpty(ammattitaitovaatimuksetText) || !CollectionUtils.isEmpty(ammattitaitovaatimuksetLista) || ammattitaitovaatimukset2019 != null) {
            addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset.title", docBase.getKieli()), "h5");
        }

        if (ammattitaitovaatimukset2019 != null) {
            addAmmattitaitovaatimukset2019(docBase, ammattitaitovaatimukset2019);
            return;
        }

        if (!ObjectUtils.isEmpty(ammattitaitovaatimuksetText)) {
            addTeksti(docBase, ammattitaitovaatimuksetText, "div");
        }

        if (ammattitaitovaatimuksetLista != null) {
            ammattitaitovaatimuksetLista.forEach(ka -> {
                Element taulukko = docBase.getDocument().createElement("table");
                taulukko.setAttribute("border", "1");
                docBase.getBodyElement().appendChild(taulukko);
                Element tbody = docBase.getDocument().createElement("tbody");
                taulukko.appendChild(tbody);

                Element tr = docBase.getDocument().createElement("tr");
                tr.setAttribute("bgcolor", "#EEEEEE");
                tbody.appendChild(tr);

                Element th = docBase.getDocument().createElement("th");
                th.appendChild(newBoldElement(docBase.getDocument(),
                        getTextString(docBase, ka.getOtsikko())));
                tr.appendChild(th);

                ka.getVaatimuksenKohteet().forEach(kohde -> {
                    Element kohdeRivi = docBase.getDocument().createElement("tr");
                    tbody.appendChild(kohdeRivi);

                    Element kohdeSolu = docBase.getDocument().createElement("td");
                    kohdeSolu.appendChild(newBoldElement(docBase.getDocument(),
                            getTextString(docBase, kohde.getOtsikko())));
                    kohdeRivi.appendChild(kohdeSolu);

                    Element kohdeSelite = docBase.getDocument().createElement("p");
                    kohdeSelite.setTextContent(getTextString(docBase, kohde.getSelite()));
                    kohdeSolu.appendChild(kohdeSelite);

                    Element vaatimusLista = docBase.getDocument().createElement("ul");
                    kohdeSolu.appendChild(vaatimusLista);
                    kohde.getVaatimukset().forEach(vaatimus -> {
                        String ktaso = getTextString(docBase, vaatimus.getSelite());
                        if (vaatimus.getAmmattitaitovaatimusKoodi() != null
                                && !vaatimus.getAmmattitaitovaatimusKoodi().isEmpty()) {
                            ktaso += " (" + vaatimus.getAmmattitaitovaatimusKoodi() + ")";
                        }
                        Element vaatimusAlkio = docBase.getDocument().createElement("li");
                        vaatimusAlkio.setTextContent(ktaso);
                        vaatimusLista.appendChild(vaatimusAlkio);
                    });
                });
            });
        }
    }

    private void addAmmattitaitovaatimukset2019(DokumenttiPeruste docBase, Ammattitaitovaatimukset2019Dto ammattitaitovaatimukset2019) {
        if (ammattitaitovaatimukset2019 != null) {
            LokalisoituTekstiDto kohde = ammattitaitovaatimukset2019.getKohde();
            List<Ammattitaitovaatimus2019Dto> vaatimukset = ammattitaitovaatimukset2019.getVaatimukset();
            List<AmmattitaitovaatimustenKohdealue2019Dto> kohdealueet = ammattitaitovaatimukset2019.getKohdealueet();

            if (!ObjectUtils.isEmpty(vaatimukset) || !ObjectUtils.isEmpty(kohdealueet)) {
                if (kohde != null && !ObjectUtils.isEmpty(vaatimukset)) {
                    addTeksti(docBase, getTextString(docBase, kohde), "p");
                }

                Element listaEl = docBase.getDocument().createElement("ul");
                docBase.getBodyElement().appendChild(listaEl);

                vaatimukset.forEach(vaatimus -> {
                    Element vaatimusEl = docBase.getDocument().createElement("li");
                    String rivi = getTextString(docBase, vaatimus.getVaatimus());
                    vaatimusEl.setTextContent(rivi);
                    listaEl.appendChild(vaatimusEl);
                });

                kohdealueet.forEach(alue -> {
                    Element alueEl = docBase.getDocument().createElement("div");
                    docBase.getBodyElement().appendChild(alueEl);

                    LokalisoituTekstiDto kuvaus = alue.getKuvaus();
                    if (kuvaus != null) {
                        Element kuvausEl = docBase.getDocument().createElement("strong");
                        kuvausEl.setTextContent(getTextString(docBase, kuvaus));
                        alueEl.appendChild(kuvausEl);
                    }

                    if (!ObjectUtils.isEmpty(alue.getVaatimukset())) {

                        Element kohdeEl = docBase.getDocument().createElement("p");
                        if (kohde != null) {
                            kohdeEl.setTextContent(getTextString(docBase, kohde));
                        } else {
                            kohdeEl.setTextContent(messages.translate("docgen.info.opiskelija", docBase.getKieli()));
                        }
                        alueEl.appendChild(kohdeEl);

                        Element alueListaEl = docBase.getDocument().createElement("ul");
                        alue.getVaatimukset().forEach(vaatimus -> {
                            Element vaatimusEl = docBase.getDocument().createElement("li");
                            String rivi = getTextString(docBase, vaatimus.getVaatimus());
                            vaatimusEl.setTextContent(rivi);
                            alueListaEl.appendChild(vaatimusEl);
                        });
                        alueEl.appendChild(alueListaEl);
                    }
                });
            }
        }
    }

    private void addValmatelmaSisalto(DokumenttiPeruste docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        if (valmaTelmaSisalto == null) {
            return;
        }
        addValmaOsaamistavoitteet(docBase, valmaTelmaSisalto);
        addValmaArviointi(docBase, valmaTelmaSisalto);
    }

    private void addValmaOsaamistavoitteet(DokumenttiPeruste docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        if (valmaTelmaSisalto.getOsaamistavoite().size() > 0) {
            addTeksti(docBase, messages.translate("docgen.valma.osaamistavoitteet.title", docBase.getKieli()), "h5");
        }

        for (OsaamisenTavoiteDto osaamisenTavoite : valmaTelmaSisalto.getOsaamistavoite()) {
            if (osaamisenTavoite.getNimi() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getNimi()), "h6");
            }

            if (osaamisenTavoite.getKohde() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getKohde()), "div");
            }

            Element lista = docBase.getDocument().createElement("ul");
            docBase.getBodyElement().appendChild(lista);
            osaamisenTavoite.getTavoitteet().forEach(tavoite -> {
                Element alkio = docBase.getDocument().createElement("li");
                alkio.setTextContent(getTextString(docBase, tavoite));
                lista.appendChild(alkio);
            });

            if (osaamisenTavoite.getSelite() != null) {
                addTeksti(docBase, getTextString(docBase, osaamisenTavoite.getSelite()), "div");
            }
        }
    }

    private void addValmaArviointi(DokumenttiPeruste docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        if (valmaTelmaSisalto.getOsaamisenarviointi() != null || valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {
            addTeksti(docBase, messages.translate("docgen.valma.osaamisenarviointi.title", docBase.getKieli()), "h5");

            if (valmaTelmaSisalto.getOsaamisenarviointi() != null) {
                if (valmaTelmaSisalto.getOsaamisenarviointi().getKohde() != null) {
                    addTeksti(docBase,
                            getTextString(docBase, valmaTelmaSisalto.getOsaamisenarviointi().getKohde()),
                            "div");
                }

                Element lista = docBase.getDocument().createElement("ul");
                docBase.getBodyElement().appendChild(lista);
                valmaTelmaSisalto.getOsaamisenarviointi().getTavoitteet().forEach(tavoite -> {
                    Element alkio = docBase.getDocument().createElement("li");
                    alkio.setTextContent(getTextString(docBase, tavoite));
                    lista.appendChild(alkio);
                });
            }

            if (valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {
                addTeksti(docBase,
                        valmaTelmaSisalto.getOsaamisenarviointiTekstina().getTekstit().get(docBase.getKieli()),
                        "div");
            }
        }
    }

    private void addAmmattitaidonOsoittamistavat(DokumenttiPeruste docBase, TutkinnonOsaKaikkiDto osa) {
        String ammattitaidonOsoittamistavatText = getTextString(docBase, osa.getAmmattitaidonOsoittamistavat());
        if (ObjectUtils.isEmpty(ammattitaidonOsoittamistavatText)) {
            return;
        }

        addTeksti(docBase, messages.translate("docgen.ammattitaidon_osoittamistavat.title", docBase.getKieli()), "h5");
        addTeksti(docBase, ammattitaidonOsoittamistavatText, "div");
    }

    private void addVapaatTekstit(DokumenttiPeruste docBase, TutkinnonOsaKaikkiDto osa) {
        List<KevytTekstiKappaleDto> vapaatTekstit = osa.getVapaatTekstit();
        vapaatTekstit.forEach(vapaaTeksti -> {
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "div");
        });
    }

    private void addGeneerinenArviointi(DokumenttiPeruste docBase, GeneerinenArviointiasteikkoKaikkiDto geneerinenArviointiasteikko) {
        if (geneerinenArviointiasteikko != null) {
            addTeksti(docBase, messages.translate("docgen.geneerinen-arviointi.title", docBase.getKieli()), "h5");

            LokalisoituTekstiDto kohde = geneerinenArviointiasteikko.getKohde();
            List<GeneerisenArvioinninOsaamistasonKriteeriKaikkiDto> osaamistasonKriteerit = new ArrayList(geneerinenArviointiasteikko.getOsaamistasonKriteerit());

            if (osaamistasonKriteerit.size() == 1 && osaamistasonKriteerit.get(0).getKriteerit().isEmpty()) {
                addTeksti(docBase, getTextString(docBase, osaamistasonKriteerit.get(0).getOsaamistaso().getOtsikko()), "div");
                return;
            }

            Element taulukko = docBase.getDocument().createElement("table");
            taulukko.setAttribute("border", "1");
            docBase.getBodyElement().appendChild(taulukko);
            Element tbody = docBase.getDocument().createElement("tbody");
            taulukko.appendChild(tbody);

            // Nimi ja kohde
            {
                Element tr = docBase.getDocument().createElement("tr");
                tr.setAttribute("bgcolor", "#EEEEEE");
                tbody.appendChild(tr);

                Element th = docBase.getDocument().createElement("th");
                th.setAttribute("colspan", "4");
                Element kohdeEl = docBase.getDocument().createElement("p");
                kohdeEl.setTextContent(getTextString(docBase, kohde));
                th.appendChild(kohdeEl);
                tr.appendChild(th);
            }

            // Osaamistason kriteerit
            {
                osaamistasonKriteerit.stream()
                        .filter(ok -> ok.getOsaamistaso() != null && !ObjectUtils.isEmpty(ok.getKriteerit()))
                        .sorted(Comparator.comparing(k -> k.getOsaamistaso().getId()))
                        .forEach(osaamistasonKriteeri -> {
                            Element tr = docBase.getDocument().createElement("tr");
                            tbody.appendChild(tr);

                            Element kriteeriTaso = docBase.getDocument().createElement("td");
                            kriteeriTaso.setAttribute("colspan", "1");
                            kriteeriTaso.setTextContent(getTextString(docBase, osaamistasonKriteeri.getOsaamistaso().getOtsikko()));
                            tr.appendChild(kriteeriTaso);

                            Element kriteeriKriteerit = docBase.getDocument().createElement("td");
                            kriteeriKriteerit.setAttribute("colspan", "3");

                            Element kriteeriLista = docBase.getDocument().createElement("ul");
                            kriteeriKriteerit.appendChild(kriteeriLista);

                            osaamistasonKriteeri.getKriteerit().forEach(kriteeriKriteeri -> {
                                String kriteeriKriteeriText = getTextString(docBase, kriteeriKriteeri);
                                if (!ObjectUtils.isEmpty(kriteeriKriteeriText)) {
                                    addTeksti(docBase, kriteeriKriteeriText, "li", kriteeriLista);
                                }
                            });
                            kriteeriKriteerit.appendChild(kriteeriLista);
                            tr.appendChild(kriteeriKriteerit);
                        });
            }
        }
    }

    private void addArviointi(
            DokumenttiPeruste docBase,
            ArviointiDto arviointi,
            TutkinnonOsaTyyppi tyyppi) {
        if (arviointi == null) {
            return;
        }

        List<ArvioinninKohdealueDto> arvioinninKohdealueet = sanitizeList(arviointi.getArvioinninKohdealueet());
        if (tyyppi == TutkinnonOsaTyyppi.REFORMI_TUTKE2) {
            if (!arvioinninKohdealueet.isEmpty()) {
                ArvioinninKohdealueDto arvioinninKohdealue = arvioinninKohdealueet.get(0);
                if (arvioinninKohdealue.getArvioinninKohteet().isEmpty()) {
                    return;
                }
            }
        }

        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");

        String lisatietoteksti = getTextString(docBase, arviointi.getLisatiedot());
        if (!ObjectUtils.isEmpty(lisatietoteksti)) {
            addTeksti(docBase, lisatietoteksti, "div");
        }

        for (ArvioinninKohdealueDto ka : arvioinninKohdealueet) {
            List<ArvioinninKohdeDto> arvioinninKohteet = ka.getArvioinninKohteet();
            if (arvioinninKohteet == null) {
                continue;
            } else if (arvioinninKohteet.isEmpty()) {
                continue;
            }

            if (TutkinnonOsaTyyppi.NORMAALI.equals(tyyppi)) {
                String otsikkoTeksti = getTextString(docBase, ka.getOtsikko());
                addTeksti(docBase, otsikkoTeksti, "h6");
            }

            for (ArvioinninKohdeDto kohde : arvioinninKohteet) {
                LokalisoituTekstiDto otsikko = kohde.getOtsikko();
                LokalisoituTekstiDto selite = kohde.getSelite();

                Element taulukko = docBase.getDocument().createElement("table");
                taulukko.setAttribute("border", "1");
                docBase.getBodyElement().appendChild(taulukko);
                Element tbody = docBase.getDocument().createElement("tbody");
                taulukko.appendChild(tbody);

                if (otsikko != null && otsikko.getTekstit().containsKey(docBase.getKieli())) {
                    Element tr = docBase.getDocument().createElement("tr");
                    tr.setAttribute("bgcolor", "#EEEEEE");
                    tbody.appendChild(tr);

                    Element th = docBase.getDocument().createElement("th");
                    th.setAttribute("colspan", "4");
                    th.appendChild(newBoldElement(docBase.getDocument(), getTextString(docBase, otsikko)));
                    tr.appendChild(th);
                }

                if (selite != null && selite.getTekstit().containsKey(docBase.getKieli())) {
                    Element tr2 = docBase.getDocument().createElement("tr");
                    tbody.appendChild(tr2);

                    Element p = docBase.getDocument().createElement("p");
                    p.appendChild(docBase.getDocument().createTextNode(getTextString(docBase, selite)));

                    Element td = docBase.getDocument().createElement("td");
                    td.setAttribute("colspan", "4");
                    td.appendChild(p);
                    tr2.appendChild(td);
                }

                Set<OsaamistasonKriteeriDto> osaamistasonKriteerit = kohde.getOsaamistasonKriteerit();
                List<OsaamistasonKriteeriDto> kriteerilista = new ArrayList<>(osaamistasonKriteerit);

                kriteerilista.stream()
                        .sorted(Comparator.comparing(k -> k.getOsaamistaso().getId()))
                        .forEach(kriteeri -> {

                            String ktaso = getTextString(docBase, kriteeri.getOsaamistasoDto().getOtsikko());
                            Element kriteeriRivi = docBase.getDocument().createElement("tr");
                            tbody.appendChild(kriteeriRivi);

                            Element kriteeriTaso = docBase.getDocument().createElement("td");
                            kriteeriTaso.setAttribute("colspan", "1");
                            kriteeriTaso.setTextContent(ktaso);
                            kriteeriRivi.appendChild(kriteeriTaso);

                            Element kriteeriKriteerit = docBase.getDocument().createElement("td");
                            kriteeriKriteerit.setAttribute("colspan", "3");
                            kriteeriRivi.appendChild(kriteeriKriteerit);

                            Element kriteeriLista = docBase.getDocument().createElement("ul");
                            kriteeriKriteerit.appendChild(kriteeriLista);

                            kriteeri.getKriteerit().forEach(kriteeriKriteeri -> {
                                String kriteeriKriteeriText = getTextString(docBase, kriteeriKriteeri);
                                if (!ObjectUtils.isEmpty(kriteeriKriteeriText)) {
                                    addTeksti(docBase, kriteeriKriteeriText, "li", kriteeriLista);
                                }
                            });
                        });
            }
        }
    }

    private void addTavoitteet(DokumenttiPeruste docBase, TutkinnonOsaKaikkiDto osa) {
        String TavoitteetText = getTextString(docBase, osa.getTavoitteet());
        if (ObjectUtils.isEmpty(TavoitteetText)) {
            return;
        }

        addTeksti(docBase, messages.translate("docgen.tavoitteet.title", docBase.getKieli()), "h5");
        addTeksti(docBase, TavoitteetText, "div");
    }

    private void addTutke2Osat(DokumenttiPeruste docBase, TutkinnonOsaKaikkiDto osa) {
        List<OsaAlueKaikkiDto> osaAlueet = osa.getOsaAlueet();

        osaAlueet.stream()
                .filter(osaAlue -> sisaltaaDokumentinKielenSisallon(docBase, osaAlue.getNimi()))
                .forEach(osaAlue -> {

            String nimi = getTextString(docBase, osaAlue.getNimi());
            addTeksti(docBase, nimi, "h5");

            List<OsaamistavoiteLaajaDto> osaamistavoitteet = osaAlue.getOsaamistavoitteet();
            ValmaTelmaSisaltoDto valmatelma = osaAlue.getValmaTelmaSisalto();

            addValmatelmaSisalto(docBase, valmatelma);

            if (OsaAlueTyyppi.OSAALUE2020.equals(osaAlue.getTyyppi())) {
                boolean pakollistaSisaltoa = osaamistavoitteellaSisaltoa(osaAlue.getPakollisetOsaamistavoitteet(), docBase.getKieli());
                boolean valinnaistaSisaltoa = osaamistavoitteellaSisaltoa(osaAlue.getValinnaisetOsaamistavoitteet(), docBase.getKieli());

                if (pakollistaSisaltoa) {
                    String otsikko = messages.translate("docgen.tutke2.pakolliset_osaamistavoitteet.title", docBase.getKieli())
                            + getLaajuusSuffiksi(osaAlue.getPakollisetOsaamistavoitteet().getLaajuus(), docBase.getLaajuusYksikko(), docBase.getKieli());
                    addTeksti(docBase, otsikko, "h5");

                    addAmmattitaitovaatimukset2019(docBase, osaAlue.getPakollisetOsaamistavoitteet().getTavoitteet());
                }

                if (valinnaistaSisaltoa) {
                    String otsikko = messages.translate("docgen.tutke2.valinnaiset_osaamistavoitteet.title", docBase.getKieli())
                            + getLaajuusSuffiksi(osaAlue.getValinnaisetOsaamistavoitteet().getLaajuus(), docBase.getLaajuusYksikko(), docBase.getKieli());
                    addTeksti(docBase, otsikko, "h5");

                    addAmmattitaitovaatimukset2019(docBase, osaAlue.getValinnaisetOsaamistavoitteet().getTavoitteet());
                }

                if (pakollistaSisaltoa || valinnaistaSisaltoa) {
                    addGeneerinenArviointi(docBase, osaAlue.getArviointi());
                }

            } else {
                // Parita pakollinen ja valinnainen osaamistavoite
                Map<Long, Pair<OsaamistavoiteDto, OsaamistavoiteDto>> tavoiteParit = new LinkedHashMap<>();

                if (osaamistavoitteet != null) {
                    for (OsaamistavoiteLaajaDto tavoite : osaamistavoitteet) {
                        Long key = tavoite.isPakollinen()
                                ? tavoite.getId()
                                : (tavoite.getEsitieto() != null ? tavoite.getEsitieto().getId() : tavoite.getId());

                        if (tavoiteParit.containsKey(key)) {
                            Pair<OsaamistavoiteDto, OsaamistavoiteDto> pari = tavoiteParit.get(key);
                            pari = tavoite.isPakollinen()
                                    ? Pair.of(tavoite, pari.getSecond()) : Pair.of(pari.getFirst(), tavoite);
                            tavoiteParit.put(key, pari);

                        } else {
                            Pair<OsaamistavoiteDto, OsaamistavoiteDto> pari = tavoite.isPakollinen()
                                    ? Pair.of(tavoite, (OsaamistavoiteDto) null) : Pair
                                    .of((OsaamistavoiteDto) null, tavoite);
                            tavoiteParit.put(key, pari);
                        }
                    }
                }

                for (Pair<OsaamistavoiteDto, OsaamistavoiteDto> tavoitePari : tavoiteParit.values()) {
                    OsaamistavoiteLaajaDto pakollinen = (OsaamistavoiteLaajaDto) tavoitePari.getFirst();
                    OsaamistavoiteLaajaDto valinnainen = (OsaamistavoiteLaajaDto) tavoitePari.getSecond();

                    OsaamistavoiteDto otsikkoTavoite = pakollinen != null ? pakollinen : valinnainen;
                    if (otsikkoTavoite == null) {
                        continue;
                    }

                    if (osa.getTyyppi().equals(TutkinnonOsaTyyppi.TUTKE2)) {
                        String tavoitteenNimi = getTextString(docBase, otsikkoTavoite.getNimi());
                        addTeksti(docBase, tavoitteenNimi, "h6");
                    }

                    OsaamistavoiteLaajaDto[] tavoiteLista = new OsaamistavoiteLaajaDto[]{pakollinen, valinnainen};
                    for (OsaamistavoiteLaajaDto tavoite : tavoiteLista) {
                        if (tavoite == null) {
                            continue;
                        }

                        String otsikkoAvain = tavoite.isPakollinen() ? "docgen.tutke2.pakolliset_osaamistavoitteet.title"
                                : "docgen.tutke2.valinnaiset_osaamistavoitteet.title";
                        String otsikko = messages.translate(otsikkoAvain, docBase.getKieli())
                                + getLaajuusSuffiksi(tavoite.getLaajuus(), docBase.getLaajuusYksikko(), docBase.getKieli());
                        String tavoitteet = getTextString(docBase, tavoite.getTavoitteet());
                        ArviointiDto arviointi = tavoite.getArviointi();
                        LokalisoituTekstiDto tunnustaminen = tavoite.getTunnustaminen();
                        List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimukset = tavoite.getAmmattitaitovaatimuksetLista();

                        if (!ObjectUtils.isEmpty(tavoitteet) || tunnustaminen != null) {
                            addTeksti(docBase, otsikko, "h5");
                        } else {
                            continue;
                        }

                        if (!ObjectUtils.isEmpty(tavoitteet)) {
                            addTeksti(docBase, tavoitteet, "div");
                        }

                        addArviointi(docBase, arviointi, osa.getTyyppi());

                        if (tunnustaminen != null) {
                            addTeksti(docBase, messages.translate("docgen.tutke2.tunnustaminen.title", docBase.getKieli()), "h6");
                            addTeksti(docBase, getTextString(docBase, tunnustaminen), "div");
                        }

                        if (!ammattitaitovaatimukset.isEmpty()) {
                            addAmmattitaitovaatimukset(docBase, null, ammattitaitovaatimukset, null);
                        }

                    }
                }
                addGeneerinenArviointi(docBase, osaAlue.getArviointi());
            }
        });
    }

    private boolean osaamistavoitteellaSisaltoa(Osaamistavoite2020Dto tavoite, Kieli kieli) {
        if (tavoite == null) {
            return false;
        }
        return tavoite.getTavoitteet().getVaatimukset().stream()
                    .anyMatch(vaatimus -> vaatimus.getVaatimus().getTekstit().containsKey(kieli) && StringUtils.isNotEmpty(vaatimus.getVaatimus().getTekstit().get(kieli)))
                || tavoite.getTavoitteet().getKohdealueet().stream()
                .anyMatch(kohdeAlue -> kohdeAlue.getVaatimukset().stream()
                        .anyMatch(vaatimus -> vaatimus.getVaatimus() != null && vaatimus.getVaatimus().getTekstit().containsKey(kieli) && StringUtils.isNotEmpty(vaatimus.getVaatimus().getTekstit().get(kieli))));
    }

    private void addTekstiOsa(DokumenttiPeruste docBase, Optional<TekstiOsaDto> tekstiOsa) {
        if (tekstiOsa.isPresent()) {
            addTeksti(docBase, getTextString(docBase, tekstiOsa.get().getOtsikko()), "h5");
            addTeksti(docBase, getTextString(docBase, tekstiOsa.get().getTeksti()), "div");
        }
    }

    private void addOppiaine(DokumenttiPeruste docBase, AIPEVaiheDto vaihe, AIPEOppiaineLaajaDto oppiaine) {
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, getOptionalValue(oppiaine.getNimi())));
        if (oppiaine.getKoodi().isPresent() && oppiaine.getKoodi().get().getArvo() != null) {
            nimiBuilder.append(" (" + oppiaine.getKoodi().get().getArvo() + ")");
        }

        if (oppiaine.getNimi().isPresent()) {
            addHeader(docBase, nimiBuilder.toString());
        } else if (oppiaine.getOppiaine() == null) {
            addHeader(docBase, messages.translate("docgen.nimeton_oppiaine", docBase.getKieli()));
        } else {
            addHeader(docBase, messages.translate("docgen.nimeton_oppimaara", docBase.getKieli()));
        }

        docBase.getGenerator().increaseDepth();

        addTekstiOsa(docBase, oppiaine.getTehtava());
        addTekstiOsa(docBase, oppiaine.getTyotavat());
        addTekstiOsa(docBase, oppiaine.getOhjaus());
        addTekstiOsa(docBase, oppiaine.getArviointi());
        addTekstiOsa(docBase, oppiaine.getSisaltoalueinfo());

        if (oppiaine.getPakollinenKurssiKuvaus().isPresent()) {
            addTeksti(docBase, messages.translate("docgen.pakollinen_kurssi_kuvaus.title", docBase.getKieli()), "h5");
            addTeksti(docBase, getTextString(docBase, getOptionalValue(oppiaine.getPakollinenKurssiKuvaus())), "div");
        }

        if (oppiaine.getSyventavaKurssiKuvaus().isPresent()) {
            addTeksti(docBase, messages.translate("docgen.syventava_kurssi_kuvaus.title", docBase.getKieli()), "h5");
            addTeksti(docBase, getTextString(docBase, getOptionalValue(oppiaine.getSyventavaKurssiKuvaus())), "div");
        }

        if (oppiaine.getSoveltavaKurssiKuvaus().isPresent()) {
            addTeksti(docBase, messages.translate("docgen.soveltava_kurssi_kuvaus.title", docBase.getKieli()), "h5");
            addTeksti(docBase, getTextString(docBase, getOptionalValue(oppiaine.getSoveltavaKurssiKuvaus())), "div");
        }

        // Tavoitteet
        if (!oppiaine.getTavoitteet().isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.tavoitteet.title", docBase.getKieli()), "h5");
            addOppiaineTavoitteet(docBase, vaihe, oppiaine.getTavoitteet());
        }

        // Kurssit
        if (oppiaine.getKurssit().size() > 0) {
            addTeksti(docBase, messages.translate("docgen.kurssit.title", docBase.getKieli()), "h5");
            oppiaine.getKurssit().forEach(aipeKurssi -> addKurssi(docBase, oppiaine, aipeKurssi));
        }

        // Oppimäärät
        if (oppiaine.getOppimaarat().size() > 0) {
            oppiaine.getOppimaarat().forEach(aipeOppiaine -> addOppiaine(docBase, vaihe, aipeOppiaine));
        }

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }

    private void addOppiaineTavoitteet(DokumenttiPeruste docBase, AIPEVaiheDto vaihe, List<OpetuksenTavoiteDto> tavoiteet) {
        tavoiteet.forEach(tavoite -> {
            addTeksti(docBase, getTextString(docBase, tavoite.getTavoite().get()), "h5");

            if (tavoite.getTavoitteistaJohdetutOppimisenTavoitteet().isPresent()) {
                addTeksti(docBase, messages.translate("tavoitteista-johdetut-oppimisen-tavoitteet", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, tavoite.getTavoitteistaJohdetutOppimisenTavoitteet().get()), "div");
            }

            Optional<Reference> tavoitealue = tavoite.getKohdealueet().stream().findFirst();
            if (tavoitealue.isPresent()) {
                addTeksti(docBase, messages.translate("tavoitealue", docBase.getKieli()), "h6");
                Optional<OpetuksenKohdealueDto> opetuksentavoitealue = vaihe.getOpetuksenKohdealueet().stream().filter(kohdealue -> kohdealue.getId().equals(tavoitealue.get().getIdLong())).findFirst();
                opetuksentavoitealue.ifPresent(opetuksenKohdealueDto -> addTeksti(docBase, getTextString(docBase, opetuksenKohdealueDto.getNimi().get()), "div"));
            }

            if (!tavoite.getLaajattavoitteet().isEmpty()) {
                addTeksti(docBase, messages.translate("laaja-alainen-osaaminen", docBase.getKieli()), "h6");
                docBase.getPeruste().getAipeOpetuksenPerusteenSisalto().getLaajaalaisetosaamiset().stream()
                        .filter(lao -> tavoite.getLaajattavoitteet().stream().map(Reference::getIdLong).collect(Collectors.toList()).contains(lao.getId()))
                        .sorted(Comparator.comparing(lao -> getTextString(docBase, lao.getNimi())))
                        .forEach(lao -> {
                            addTeksti(docBase, getTextString(docBase, lao.getNimi()), "p");
                        });

                addTeksti(docBase, "", "p");
            }

            if (tavoite.getArvioinninKuvaus().isPresent()) {
                addTeksti(docBase, messages.translate("arvioinnin-kohde", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninKuvaus().get()), "div");
            }

            if (tavoite.getArvioinninOtsikko().isPresent()) {
                addTeksti(docBase, getTextString(docBase, tavoite.getArvioinninOtsikko().get()), "h6");
            }

            if (!tavoite.getArvioinninkohteet().isEmpty()) {
                DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
                taulukko.addOtsikkosarakkeet(
                        messages.translate("arvosana", docBase.getKieli()),
                        messages.translate("osaamisen-kuvaus", docBase.getKieli())
                );

                tavoite.getArvioinninkohteet()
                        .stream()
                        .sorted(Comparator.comparing(arvioinninKohde -> arvioinninKohde.getArvosana().get()))
                        .forEach(arvioinninKohde -> {
                            DokumenttiRivi rivi = new DokumenttiRivi();
                            rivi.addSarake(
                                    messages.translate("osaamisen-kuvaus-arvosanalle-" + arvioinninKohde.getArvosana().get(), docBase.getKieli()),
                                    getTextString(docBase, arvioinninKohde.getOsaamisenKuvaus().orElse(LokalisoituTekstiDto.of(""))));
                            taulukko.addRivi(rivi);
                        });

                taulukko.addToDokumentti(docBase);
            }

            if (tavoite.getVapaaTeksti().isPresent()) {
                addTeksti(docBase, getTextString(docBase, tavoite.getVapaaTeksti().get()), "div");
            }
        });
    }

    private void addKurssi(DokumenttiPeruste docBase, AIPEOppiaineLaajaDto oppiaine, AIPEKurssiDto kurssi) {
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, getOptionalValue(kurssi.getNimi())));
        if (kurssi.getKoodi() != null && kurssi.getKoodi().getUri() != null) {
            String uri = kurssi.getKoodi().getUri();
            String[] splitArray = uri.split("_");
            if (splitArray.length > 0) {
                nimiBuilder.append(" (");
                nimiBuilder.append(splitArray[splitArray.length - 1].toUpperCase());
                nimiBuilder.append(")");
            }
        }

        addTeksti(docBase, nimiBuilder.toString(), "h6");
        addTeksti(docBase, getTextString(docBase, getOptionalValue(kurssi.getKuvaus())), "div");

        // Liitetyt tavoitteet
        if (kurssi.getTavoitteet().size() > 0) {
            addTeksti(docBase, messages.translate("docgen.liitetyt_tavoitteet", docBase.getKieli()) + ":", "p");
            Element ul = docBase.getDocument().createElement("ul");
            docBase.getBodyElement().appendChild(ul);

            kurssi.getTavoitteet().forEach(kurssinTavoite -> {
                Optional<OpetuksenTavoiteDto> opetuksenTavoite = oppiaine.getTavoitteet().stream().filter(tavoite -> tavoite.getId().equals(kurssinTavoite.getIdLong())).findFirst();
                if (opetuksenTavoite.isPresent()) {
                    Element li = docBase.getDocument().createElement("li");
                    ul.appendChild(li);
                    li.setTextContent(getTextString(docBase, getOptionalValue(opetuksenTavoite.get().getTavoite())));
                }
            });
        }
    }

    private String getLaajuusSuffiksi(final BigDecimal laajuus, final LaajuusYksikko yksikko, final Kieli kieli) {
        StringBuilder laajuusBuilder = new StringBuilder();
        if (laajuus != null) {
            laajuusBuilder.append(", ");
            laajuusBuilder.append(laajuus.stripTrailingZeros().toPlainString());
            laajuusBuilder.append(" ");
            String yksikkoAvain;
            switch (yksikko) {
                case OPINTOVIIKKO:
                    yksikkoAvain = "docgen.laajuus.ov";
                    break;
                case OSAAMISPISTE:
                    yksikkoAvain = "docgen.laajuus.osp";
                    break;
                default:
                    throw new NotImplementedException("Tuntematon laajuusyksikko: " + yksikko);
            }
            laajuusBuilder.append(messages.translate(yksikkoAvain, kieli));
        }
        return laajuusBuilder.toString();
    }

    private String getLaajuusSuffiksi(final BigDecimal laajuus, final BigDecimal laajuusMaksimi, final LaajuusYksikko yksikko, final Kieli kieli) {
        StringBuilder laajuusBuilder = new StringBuilder();
        if (laajuus != null) {
            laajuusBuilder.append(", ");
            laajuusBuilder.append(laajuus.stripTrailingZeros().toPlainString());
            laajuusBuilder.append("-");
            laajuusBuilder.append(laajuusMaksimi.stripTrailingZeros().toPlainString());
            laajuusBuilder.append(" ");
            String yksikkoAvain;
            switch (yksikko) {
                case OPINTOVIIKKO:
                    yksikkoAvain = "docgen.laajuus.ov";
                    break;
                case OSAAMISPISTE:
                    yksikkoAvain = "docgen.laajuus.osp";
                    break;
                default:
                    throw new NotImplementedException("Tuntematon laajuusyksikko: " + yksikko);
            }
            laajuusBuilder.append(messages.translate(yksikkoAvain, kieli));
        }
        return laajuusBuilder.toString();
    }

    private String getOtsikko(DokumenttiPeruste docBase, TutkinnonOsaViiteSuppeaDto viite) {
        return getOtsikko(docBase, viite, true);
    }

    private String getOtsikko(DokumenttiPeruste docBase, TutkinnonOsaViiteSuppeaDto viite, boolean withKoodi) {
        TutkinnonOsaKaikkiDto osa = docBase.getTutkinnonOsa(viite.getTutkinnonOsa().getIdLong()).get();;
        StringBuilder otsikkoBuilder = new StringBuilder();
        otsikkoBuilder.append(getTextString(docBase, osa.getNimi()));

        BigDecimal laajuusMaksimi = viite.getLaajuusMaksimi();
        if (laajuusMaksimi != null) {
            otsikkoBuilder.append(getLaajuusSuffiksi(viite.getLaajuus(), laajuusMaksimi,
                    docBase.getLaajuusYksikko(), docBase.getKieli()));
        } else {
            otsikkoBuilder.append(getLaajuusSuffiksi(viite.getLaajuus(), docBase.getLaajuusYksikko(), docBase.getKieli()));
        }

        if (withKoodi) {
            String koodi = osa.getKoodiArvo();
            if (koodi != null) {
                otsikkoBuilder
                        .append(" (")
                        .append(koodi)
                        .append(")");
            }
        }

        return otsikkoBuilder.toString();
    }

    private String getKokoTeksti(MuodostumisSaantoDto saanto, Kieli kieli) {
        if (saanto == null || saanto.getKoko() == null) {
            return null;
        }

        MuodostumisSaantoDto.Koko koko = saanto.getKoko();
        Integer min = koko.getMinimi();
        Integer max = koko.getMaksimi();
        StringBuilder kokoBuilder = new StringBuilder();
        if (min != null) {
            kokoBuilder.append(min);
        }
        if (min != null && max != null && !min.equals(max)) {
            kokoBuilder.append("-");
        }
        if (max != null && !max.equals(min)) {
            kokoBuilder.append(max);
        }

        String yks = messages.translate("docgen.koko.kpl", kieli);
        kokoBuilder.append(" ");
        kokoBuilder.append(yks);
        return kokoBuilder.toString();
    }

    private String getLaajuusTeksti(MuodostumisSaantoDto saanto, LaajuusYksikko yksikko, Kieli kieli) {
        if (saanto == null || saanto.getLaajuus() == null) {
            return null;
        }

        MuodostumisSaantoDto.Laajuus laajuus = saanto.getLaajuus();
        Integer min = laajuus.getMinimi();
        Integer max = laajuus.getMaksimi();
        StringBuilder laajuusBuilder = new StringBuilder();
        if (min != null) {
            laajuusBuilder.append(min);
        }
        if (min != null && max != null && !min.equals(max)) {
            laajuusBuilder.append("-");
        }
        if (max != null && !max.equals(min)) {
            laajuusBuilder.append(max);
        }

        String yks = messages.translate("docgen.laajuus.ov", kieli);
        if (yksikko == LaajuusYksikko.OSAAMISPISTE) {
            yks = messages.translate("docgen.laajuus.osp", kieli);
        }

        laajuusBuilder.append(" ");
        laajuusBuilder.append(yks);
        return laajuusBuilder.toString();
    }

    private <T> T getOptionalValue(Optional<T> optional) {
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    private <T> List<T> sanitizeList(List<T> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }
}
