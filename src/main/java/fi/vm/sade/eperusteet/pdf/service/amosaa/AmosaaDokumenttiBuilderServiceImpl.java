package fi.vm.sade.eperusteet.pdf.service.amosaa;

import com.fasterxml.jackson.core.JsonProcessingException;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OsaamisenArvioinninToteutussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.SuorituspolkuRiviDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.CachedPerusteBaseDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimuksenKohdeDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimuksenKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.AmmattitaitovaatimusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KotoTaitotasoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.KoulutuksenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.OmaOsaAlueExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.OmaTutkinnonosaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.OpintokokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SuorituspolkuExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TekstiKappaleJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TekstiosaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TutkinnonosaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.TuvaLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoMetadataDto;
import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.MuodostumisSaantoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.OsaamistasoDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneModuuliDto;
import fi.vm.sade.eperusteet.pdf.dto.common.RakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiAmosaa;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.OmaOsaAlueTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.OpintokokonaisuusTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Suoritustapakoodi;
import fi.vm.sade.eperusteet.pdf.dto.enums.TutkinnonOsaTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerinenArviointiasteikkoKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.GeneerisenArvioinninOsaamistasonKriteeriKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.OsaamistasonKriteeriDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.ammattitaitovaatimukset.AmmattitaitovaatimusKohdealueetDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArvioinninKohdeDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArvioinninKohdealueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArviointiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteenOsaViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.SuoritustapaLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Ammattitaitovaatimukset2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.Ammattitaitovaatimus2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.AmmattitaitovaatimustenKohdealue2019Dto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaAlueKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.OsaamisenTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.TutkinnonOsaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonosa.ValmaTelmaSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.TutkinnonOsaViiteSuppeaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoKielitaitotasoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoLaajaAlainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoLaajaAlaisenOsaamisenAlueDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoOpintoDto;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import fi.vm.sade.eperusteet.pdf.service.external.AmosaaService;
import fi.vm.sade.eperusteet.pdf.service.external.KoodistoClient;
import fi.vm.sade.eperusteet.pdf.utils.CharapterNumberGenerator;
import fi.vm.sade.eperusteet.pdf.utils.CollectionUtil;
import fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fi.vm.sade.eperusteet.pdf.dto.enums.RakenneModuuliRooli.VIRTUAALINEN;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.selectLaajuusYksikkoMessage;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.sisaltaaDokumentinKielenSisallon;

@Slf4j
@Service
public class AmosaaDokumenttiBuilderServiceImpl implements AmosaaDokumenttiBuilderService {

    private static final DecimalFormat df2 = new DecimalFormat("0.##");

    @Autowired
    private LocalizedMessagesService messages;

    @Autowired
    private KoodistoClient koodistoClient;

    @Autowired
    private AmosaaService amosaaService;

    @Autowired
    private DokumenttiUtilService dokumenttiUtilService;

    @Autowired
    private MapperFacade mapper;

    @Override
    public Document generateXML(OpetussuunnitelmaKaikkiDto ops, GeneratorData generatorData) throws ParserConfigurationException, JsonProcessingException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Luodaan XHTML pohja
        Element rootElement = doc.createElement("html");
        rootElement.setAttribute("lang", generatorData.getKieli().toString());
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
        DokumenttiAmosaa docBase = new DokumenttiAmosaa();
        docBase.setDocument(doc);
        docBase.setHeadElement(headElement);
        docBase.setBodyElement(bodyElement);
        docBase.setGenerator(new CharapterNumberGenerator());
        docBase.setKieli(generatorData.getKieli());
        docBase.setGeneratorData(generatorData);
        docBase.setOpetussuunnitelma(ops);

        if (ops.getPeruste() != null) {
            PerusteKaikkiDto perusteKaikkiDto = amosaaService.getPerusteKaikkiDto(ops.getPeruste().getId());
            docBase.setPeruste(perusteKaikkiDto);
            docBase.getTutkinnonOsienPerusteet().add(perusteKaikkiDto);
        }

        docBase.getTutkinnonOsienPerusteet().addAll(
                CollectionUtil.treeToStream(ops.getSisalto(), SisaltoViiteExportDto::getLapset)
                    .filter(sv -> sv.getPeruste() != null)
                    .map(sv -> sv.getPeruste().getId())
                    .map(cachedPerusteId -> amosaaService.getPerusteKaikkiDto(cachedPerusteId))
                    .collect(Collectors.toList()));

        // Kansilehti & Infosivu
        addMetaPages(docBase);

        // Sisältöelementit
        addTekstit(docBase);

        // Alaviitteet
        buildFootnotes(docBase);

        // Kuvat
        dokumenttiUtilService.buildImages(docBase, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.kansikuva, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.ylatunniste, generatorData);
        dokumenttiUtilService.buildKuva(docBase, Kuvatyyppi.alatunniste, generatorData);

        return doc;
    }

    private void addMetaPages(DokumenttiAmosaa docBase) {
        OpetussuunnitelmaDto ops = docBase.getOpetussuunnitelma();

        Element title = docBase.getDocument().createElement("title");
        String nimi = getTextString(docBase, ops.getNimi());
        title.appendChild(docBase.getDocument().createTextNode(nimi));
        docBase.getHeadElement().appendChild(title);

        // Kuvaus
        String kuvaus = getTextString(docBase, ops.getKuvaus());
        if (kuvaus != null && kuvaus.length() != 0) {
            Element description = docBase.getDocument().createElement("description");
            addTeksti(docBase, kuvaus, "div", description);
            docBase.getHeadElement().appendChild(description);
        }

        if (ops.getPeruste() != null) {
            String perusteNimi = getTextString(docBase, ops.getPeruste().getNimi());
            if (perusteNimi != null) {
                Element perusteNimiEl = docBase.getDocument().createElement("meta");
                perusteNimiEl.setAttribute("name", "perusteNimi");
                perusteNimiEl.setAttribute("content", perusteNimi);
                perusteNimiEl.setAttribute("translate", messages.translate("perusteen-nimi", docBase.getKieli()));
                docBase.getHeadElement().appendChild(perusteNimiEl);
            }
        }

        // Päätösnumero
        String paatosnumero = ops.getPaatosnumero();
        if (paatosnumero != null) {
            Element paatosnumeroEl = docBase.getDocument().createElement("meta");
            paatosnumeroEl.setAttribute("name", "paatosnumero");
            paatosnumeroEl.setAttribute("content", paatosnumero);
            paatosnumeroEl.setAttribute("translate", messages.translate("paatosnumero", docBase.getKieli()));
            docBase.getHeadElement().appendChild(paatosnumeroEl);
        }

        // Hyväksyjä
        String hyvaksyja = ops.getHyvaksyja();
        if (hyvaksyja != null) {
            Element hyvaksyjaEl = docBase.getDocument().createElement("meta");
            hyvaksyjaEl.setAttribute("name", "hyvaksyja");
            hyvaksyjaEl.setAttribute("content", hyvaksyja);
            hyvaksyjaEl.setAttribute("translate", messages.translate("hyvaksyja", docBase.getKieli()));
            docBase.getHeadElement().appendChild(hyvaksyjaEl);
        }

        // Päätöspäivämäärä
        Date paatospaivamaara = ops.getPaatospaivamaara();
        if (paatospaivamaara != null) {
            Element paatospaivamaaraEl = docBase.getDocument().createElement("meta");
            paatospaivamaaraEl.setAttribute("name", "paatospaivamaara");
            String paatospaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(paatospaivamaara);
            paatospaivamaaraEl.setAttribute("content", paatospaivamaaraText);
            paatospaivamaaraEl.setAttribute("translate", messages.translate("paatospaivamaara", docBase.getKieli()));
            docBase.getHeadElement().appendChild(paatospaivamaaraEl);
        }

        // Voimaantulopäivämäärä
        Date voimaantulopaivamaara = ops.getVoimaantulo();
        if (voimaantulopaivamaara != null) {
            Element voimaantulopaivamaaraEl = docBase.getDocument().createElement("meta");
            voimaantulopaivamaaraEl.setAttribute("name", "voimaantulopaivamaara");
            String voimaantulopaivamaaraText = new SimpleDateFormat("d.M.yyyy").format(voimaantulopaivamaara);
            voimaantulopaivamaaraEl.setAttribute("content", voimaantulopaivamaaraText);
            voimaantulopaivamaaraEl.setAttribute("translate", messages.translate("voimaantulopaivamaara", docBase.getKieli()));
            docBase.getHeadElement().appendChild(voimaantulopaivamaaraEl);
        }

        Element pdfluotu = docBase.getDocument().createElement("meta");
        pdfluotu.setAttribute("name", "pdfluotu");
        pdfluotu.setAttribute("content", new SimpleDateFormat("d.M.yyyy").format(new Date()));
        pdfluotu.setAttribute("translate", messages.translate("docgen.pdf-luotu", docBase.getKieli()));
        docBase.getHeadElement().appendChild(pdfluotu);

        if (!CollectionUtils.isEmpty(ops.getOsaamisenArvioinninToteutussuunnitelmat())) {
            Element oatit = docBase.getDocument().createElement("oats");
            addTeksti(docBase, messages.translate("osaamisen-arvioinnin-toteutussuunnitelma", docBase.getKieli()), "h6", oatit);

            for (OsaamisenArvioinninToteutussuunnitelmaDto oat : ops.getOsaamisenArvioinninToteutussuunnitelmat()) {
                Element oatElement = docBase.getDocument().createElement("span");
                if (oat.getOatOpetussuunnitelma() != null) {
                    addTeksti(docBase, getTextString(docBase, oat.getOatOpetussuunnitelma().getNimi()), "span", oatElement);
                } else if(oat.getUrl() != null && oat.getUrl().get(docBase.getKieli()) != null) {
                    Element oatUrl = docBase.getDocument().createElement("a");
                    oatUrl.setTextContent(getTextString(docBase, oat.getNimi()));
                    oatUrl.setAttribute("href", oat.getUrl().get(docBase.getKieli()));
                    oatElement.appendChild(oatUrl);
                }
                oatit.appendChild(oatElement);
                oatit.appendChild(docBase.getDocument().createElement("br"));
            }

            docBase.getHeadElement().appendChild(oatit);
        }
    }

    private void addTekstit(DokumenttiAmosaa docBase) {

        SisaltoViiteExportDto tekstit = docBase.getOpetussuunnitelma().getSisalto();
        if (tekstit != null) {
            addSisaltoviite(docBase, tekstit);
        }
    }

    private void addSisaltoviite(DokumenttiAmosaa docBase, SisaltoViiteExportDto parent) {

        for (SisaltoViiteExportDto sisaltoViiteDto : parent.getLapset()) {

            if (sisaltoViiteDto == null || sisaltoViiteDto.getNimi() == null) {
                return;
            }

            if (sisaltoViiteDto.getTyyppi().equals(SisaltoTyyppi.SUORITUSPOLUT) && CollectionUtils.isEmpty(sisaltoViiteDto.getLapset())) {
                continue;
            }

            TekstiKappaleJulkinenDto tekstiKappale = sisaltoViiteDto.getTekstiKappale();
            StringBuilder otsikkoBuilder = new StringBuilder(getSisaltoViiteOtsikko(docBase, sisaltoViiteDto));

            if (sisaltoViiteDto.getTyyppi().equals(SisaltoTyyppi.TUTKINNONOSA)
                    && sisaltoViiteDto.getTosa() != null
                    && docBase.getOpetussuunnitelma().getTutkinnonOsat().stream().anyMatch(tosaviite -> tosaviite.getId().equals(sisaltoViiteDto.getId()))) {
                TutkinnonosaExportDto tosa = docBase.getOpetussuunnitelma().getTutkinnonOsat().stream().filter(tosaviite -> tosaviite.getId().equals(sisaltoViiteDto.getId())).findFirst().get().getTosa();
                switch (tosa.getTyyppi()) {
                    case OMA:
                        if (tosa.getOmatutkinnonosa() == null
                                || tosa.getOmatutkinnonosa().getKoodi() == null) {
                            break;
                        }
                        String koodi = tosa.getOmatutkinnonosa().getKoodi();

                        otsikkoBuilder
                                .append(" (")
                                .append(koodi)
                                .append(")");
                        break;
                    case PERUSTEESTA:
                        Long perusteenTutkinnonosaId = tosa.getPerusteentutkinnonosa();
                        if (perusteenTutkinnonosaId == null || CollectionUtils.isEmpty(docBase.getTutkinnonOsienPerusteet())) {
                            break;

                        }
                        Optional<TutkinnonOsaKaikkiDto> perusteTosa = docBase.getTutkinnonOsienPerusteet().stream()
                                .map(PerusteKaikkiDto::getTutkinnonOsat)
                                .flatMap(Collection::stream)
                                .filter(dto -> dto.getId().equals(perusteenTutkinnonosaId))
                                .findAny();

                        perusteTosa.ifPresent(dto -> otsikkoBuilder
                                        .append(" (")
                                        .append(dto.getKoodiArvo())
                                        .append(")"));

                        break;
                    default:
                        break;
                }
            }

            if (sisaltoViiteDto.getTyyppi().equals(SisaltoTyyppi.OPINTOKOKONAISUUS)
                    && sisaltoViiteDto.getOpintokokonaisuus() != null
                    && sisaltoViiteDto.getOpintokokonaisuus().getLaajuus() != null && sisaltoViiteDto.getOpintokokonaisuus().getLaajuus().compareTo(BigDecimal.ZERO) > 0
                    && sisaltoViiteDto.getOpintokokonaisuus().getLaajuusYksikko() != null) {
                otsikkoBuilder.append(", " + df2.format(sisaltoViiteDto.getOpintokokonaisuus().getLaajuus()) + " " + messages.translate(selectLaajuusYksikkoMessage(sisaltoViiteDto.getOpintokokonaisuus().getLaajuusYksikko()), docBase.getKieli()));
            }

            if (sisaltoViiteDto.getTyyppi().equals(SisaltoTyyppi.KOULUTUKSENOSA)) {
                Integer minimilaajuus = sisaltoViiteDto.getKoulutuksenosa().getLaajuusMinimi();
                Integer maksimiLAajuus = sisaltoViiteDto.getKoulutuksenosa().getLaajuusMaksimi();
                if (sisaltoViiteDto.getPerusteenOsaId() != null) {
                    Optional<PerusteenOsaViiteDto.Laaja> perusteenOsaViite = docBase.getPerusteenOsa(sisaltoViiteDto.getPerusteenOsaId());
                    if (perusteenOsaViite.isPresent()) {
                        fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto perusteenOsaDto = (fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto)perusteenOsaViite.get().getPerusteenOsa();
                        minimilaajuus = perusteenOsaDto.getLaajuusMinimi();
                        maksimiLAajuus = perusteenOsaDto.getLaajuusMaksimi();
                    }
                }

                otsikkoBuilder.append(", " + minimilaajuus + " - " + maksimiLAajuus + " " + messages.translate("docgen.laajuus.viikkoa", docBase.getKieli()));
            }

            DokumenttiUtils.addHeader(docBase, otsikkoBuilder.toString());

            if (sisaltoViiteDto.isNaytaPerusteenTeksti() && sisaltoViiteDto.getPerusteteksti() != null && sisaltoViiteDto.getPerusteteksti() != null) {
                DokumenttiUtils.addLokalisoituteksti(docBase, sisaltoViiteDto.getPerusteteksti(), "div");
            }

            if (sisaltoViiteDto.isNaytaPohjanTeksti() && sisaltoViiteDto.getPohjanTekstikappale() != null && sisaltoViiteDto.getPohjanTekstikappale().getTeksti() != null) {
                DokumenttiUtils.addLokalisoituteksti(docBase, sisaltoViiteDto.getPohjanTekstikappale().getTeksti(), "div");
            }

            if (tekstiKappale.getTeksti() != null) {
                DokumenttiUtils.addLokalisoituteksti(docBase, tekstiKappale.getTeksti(), "div");
            } else {
                addTeksti(docBase, "", "p"); // Sivutuksen kannalta välttämätön
            }

            switch (sisaltoViiteDto.getTyyppi()) {
                case OSASUORITUSPOLKU:
                case SUORITUSPOLKU:
                    addSuorituspolku(docBase, sisaltoViiteDto, otsikkoBuilder.toString());
                    break;
                case TUTKINNONOSA:
                    addTutkinnonosa(docBase, sisaltoViiteDto);
                    break;
                case OPINTOKOKONAISUUS:
                    addOpintokokonaisuus(docBase, sisaltoViiteDto);
                    break;
                case LAAJAALAINENOSAAMINEN:
                    addLaajaalainenOsaaminen(docBase, sisaltoViiteDto);
                    break;
                case KOULUTUKSENOSA:
                    addKoulutuksenosa(docBase, sisaltoViiteDto);
                    break;
                case KOTO_LAAJAALAINENOSAAMINEN:
                    addKotoLaajaAlainenOsaaminen(docBase, sisaltoViiteDto);
                    break;
                case KOTO_KIELITAITOTASO:
                    addKotoKielitaitotaso(docBase, sisaltoViiteDto);
                    break;
                case KOTO_OPINTO:
                    addKotoOpinto(docBase, sisaltoViiteDto);
                    break;
                default:
                    break;
            }

            docBase.getGenerator().increaseDepth();

            // Rekursiivisesti
            addSisaltoviite(docBase, sisaltoViiteDto);

            docBase.getGenerator().decreaseDepth();

            docBase.getGenerator().increaseNumber();
        }
    }

    private String getSisaltoViiteOtsikko(DokumenttiAmosaa docBase, SisaltoViiteExportDto sisaltoViiteDto) {
        if (sisaltoViiteDto.getTyyppi().equals(SisaltoTyyppi.SUORITUSPOLUT)) {
            return messages.translate("opiskelupolut", docBase.getKieli());
        }

        return getTextString(docBase, sisaltoViiteDto.getNimi());
    }

    private void addSuorituspolku(DokumenttiAmosaa docBase, SisaltoViiteExportDto viite, String suorituspolkuNimi) {
        SuorituspolkuExportDto suorituspolku = viite.getSuorituspolku();
        Map<UUID, SuorituspolkuRiviDto> suorituspolkuMap = new HashMap<>();

        if (suorituspolku != null) {
            suorituspolku.getRivit().forEach(rivi -> {
                if (rivi.getPiilotettu() != null && rivi.getPiilotettu()) {
                    suorituspolkuMap.put(rivi.getRakennemoduuli(), rivi);
                }
            });
        }

        PerusteKaikkiDto peruste = docBase.getPeruste();

        if (peruste != null) {
            peruste.getSuoritustavat().stream()
                    .filter(suoritustapaLaajaDto -> suoritustapaLaajaDto.getSuoritustapakoodi().equals(Suoritustapakoodi.OPS)
                            || suoritustapaLaajaDto.getSuoritustapakoodi().equals(Suoritustapakoodi.REFORMI))
                    .findAny()
                    .filter(suoritustapaLaajaDto -> suoritustapaLaajaDto.getRakenne() != null)
                    .ifPresent(suoritustapaLaajaDto -> {

                        // Luodaan muodostumistaulukko
                        Element taulukko = docBase.getDocument().createElement("table");
                        taulukko.setAttribute("border", "1");
                        Element otsikko = docBase.getDocument().createElement("caption");
                        otsikko.setTextContent(suorituspolkuNimi);
                        taulukko.appendChild(otsikko);
                        docBase.getBodyElement().appendChild(taulukko);
                        Element tbody = docBase.getDocument().createElement("tbody");
                        taulukko.appendChild(tbody);

                        suoritustapaLaajaDto.getRakenne().getOsat().stream()
                                .filter(dto -> dto.getTunniste() != null && !suorituspolkuMap.containsKey(dto.getTunniste()))
                                .filter(dto -> dto instanceof RakenneModuuliDto)
                                .map(dto -> (RakenneModuuliDto) dto)
                                .forEach(rakenneModuuliDto -> addSuorituspolkuOsa(
                                        docBase, rakenneModuuliDto, tbody, 1, suoritustapaLaajaDto, suorituspolkuMap));
                    });
        }
    }

    private void addSuorituspolkuOsa(DokumenttiAmosaa docBase,
                                     RakenneModuuliDto rakenneModuuliDto,
                                     Element tbody,
                                     int depth,
                                     SuoritustapaLaajaDto suoritustapaLaajaDto,
                                     Map<UUID, SuorituspolkuRiviDto> suorituspolkuMap) {

        Element tr = docBase.getDocument().createElement("tr");
        tr.setAttribute("bgcolor", "#F1F2F3");
        if (rakenneModuuliDto.getOsaamisala() != null) {
            tr.setAttribute("bgcolor", "#F3FAFD");
        }
        tbody.appendChild(tr);
        Element td = docBase.getDocument().createElement("td");
        tr.appendChild(td);
        td.setAttribute("class", "td" + depth);

        // Nimi
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, rakenneModuuliDto.getNimi()));
        if (rakenneModuuliDto.getOsaamisala() != null
                && rakenneModuuliDto.getOsaamisala().getOsaamisalakoodiArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(rakenneModuuliDto.getOsaamisala().getOsaamisalakoodiArvo());
            nimiBuilder.append(")");
        }
        addMuodostumisSaanto(docBase, rakenneModuuliDto.getMuodostumisSaanto(), nimiBuilder);
        addTeksti(docBase, nimiBuilder.toString(), "p", td);

        // Kuvaus
        if (rakenneModuuliDto.getKuvaus() != null) {
            addTeksti(docBase, getTextString(docBase, rakenneModuuliDto.getKuvaus()), "em", td);
        }

        rakenneModuuliDto.getOsat().forEach(lapsi -> {
            // Piilotettuja ei generoida PDF:ään
            if (suorituspolkuMap.containsKey(lapsi.getTunniste())) {
                SuorituspolkuRiviDto rivi = suorituspolkuMap.get(lapsi.getTunniste());
                if (rivi.getPiilotettu() != null && rivi.getPiilotettu()) {
                    return;
                }
            }

            if (lapsi instanceof RakenneModuuliDto) {
                RakenneModuuliDto lapsiDto = (RakenneModuuliDto) lapsi;
                addSuorituspolkuOsa(docBase, lapsiDto, tbody, depth + 1, suoritustapaLaajaDto, suorituspolkuMap);
            } else if (lapsi instanceof RakenneOsaDto) {
                RakenneOsaDto lapsiDto = (RakenneOsaDto) lapsi;
                if (lapsiDto.getTutkinnonOsaViite() != null) {
                    suoritustapaLaajaDto.getTutkinnonOsat().stream()
                            .filter(dto -> dto.getId().equals(lapsiDto.getTutkinnonOsaViite().getIdLong()))
                            .findAny()
                            .ifPresent(dto -> {
                                PerusteKaikkiDto peruste = docBase.getPeruste();
                                if (peruste != null) {
                                    peruste.getTutkinnonOsat().stream()
                                            .filter(tutkinnonOsaDto -> tutkinnonOsaDto.getId().equals(dto.getTutkinnonOsa().getIdLong()))
                                            .findAny()
                                            .ifPresent(tutkinnonOsaKaikkiDto -> addSuorituspolunTutkinnonOsa(
                                                    docBase, tutkinnonOsaKaikkiDto, dto, tbody, depth + 1));
                                }
                            });
                }
            }
        });

        // Lisätään tutkinnossa määritettävä rakenne osan aliosat
        if (VIRTUAALINEN.equals(rakenneModuuliDto.getRooli())) {
            if (suorituspolkuMap.containsKey(rakenneModuuliDto.getTunniste())) {
                SuorituspolkuRiviDto rivi = suorituspolkuMap.get(rakenneModuuliDto.getTunniste());
                Set<String> koodit = rivi.getKoodit();
                if (koodit != null) {
                    koodit.forEach(koodi -> {
                        KoodistoKoodiDto koodistoKoodiDto = koodistoClient.getByUri(koodi);
                        if (koodistoKoodiDto != null) {
                            addSuorituspolunKoodiOsa(docBase, koodistoKoodiDto, tbody, depth + 1);
                        }
                    });
                }
            }
        }
    }

    private void addMuodostumisSaanto(DokumenttiAmosaa docBase,
                                      MuodostumisSaantoDto muodostumisSaantoDto,
                                      StringBuilder builder) {
        if (muodostumisSaantoDto != null && muodostumisSaantoDto.getLaajuus() != null) {
            builder.append(" ");
            if (muodostumisSaantoDto.getLaajuus().getMinimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMaksimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMinimi()
                    .equals(muodostumisSaantoDto.getLaajuus().getMaksimi())) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMinimi() != null
                    && muodostumisSaantoDto.getLaajuus().getMaksimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
                builder.append("-");
                builder.append(muodostumisSaantoDto.getLaajuus().getMaksimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMinimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMinimi());
            } else if (muodostumisSaantoDto.getLaajuus().getMaksimi() != null) {
                builder.append(muodostumisSaantoDto.getLaajuus().getMaksimi());
            }

            if (muodostumisSaantoDto.getLaajuus().getYksikko() != null) {
                builder.append(" ");
                builder.append(muodostumisSaantoDto.getLaajuus().getYksikko());
            } else {
                builder.append(" ").append(messages.translate("docgen.laajuus.osp", docBase.getKieli()));
            }
        }
    }

    private void addSuorituspolunKoodiOsa(
            DokumenttiBase docBase,
            KoodistoKoodiDto koodistoKoodiDto,
            Element tbody,
            int depth
    ) {
        if (koodistoKoodiDto != null && koodistoKoodiDto.getMetadata() != null) {
            KoodistoMetadataDto[] metadata = koodistoKoodiDto.getMetadata();
            for (KoodistoMetadataDto metadataDto : metadata) {

                // Valitaan dokumentin kieli
                if (docBase.getKieli().toString().equals(metadataDto.getKieli().toLowerCase())) {

                    Element tr = docBase.getDocument().createElement("tr");
                    tbody.appendChild(tr);
                    Element td = docBase.getDocument().createElement("td");
                    tr.appendChild(td);
                    td.setAttribute("class", "td" + depth);

                    // Nimi
                    StringBuilder nimiBuilder = new StringBuilder();
                    nimiBuilder.append(metadataDto.getNimi());
                    if (koodistoKoodiDto.getKoodiArvo() != null) {
                        nimiBuilder.append(" (");
                        nimiBuilder.append(koodistoKoodiDto.getKoodiArvo());
                        nimiBuilder.append(")");
                    }

                    addTeksti(docBase, nimiBuilder.toString(), "p", td);

                }
            }
        }
    }

    private void addSuorituspolunTutkinnonOsa(DokumenttiBase docBase,
                                              TutkinnonOsaKaikkiDto tutkinnonOsaKaikkiDto,
                                              TutkinnonOsaViiteSuppeaDto tutkinnonOsaViiteDto,
                                              Element tbody,
                                              int depth) {
        Element tr = docBase.getDocument().createElement("tr");
        tbody.appendChild(tr);
        Element td = docBase.getDocument().createElement("td");
        tr.appendChild(td);
        td.setAttribute("class", "td" + String.valueOf(depth));

        // Nimi
        StringBuilder nimiBuilder = new StringBuilder();
        nimiBuilder.append(getTextString(docBase, tutkinnonOsaKaikkiDto.getNimi()));
        if (tutkinnonOsaKaikkiDto.getKoodiArvo() != null) {
            nimiBuilder.append(" (");
            nimiBuilder.append(tutkinnonOsaKaikkiDto.getKoodiArvo());
            nimiBuilder.append(")");
        }
        if (tutkinnonOsaViiteDto.getLaajuus() != null) {
            nimiBuilder.append(" ");
            if (tutkinnonOsaViiteDto.getLaajuus().stripTrailingZeros().scale() <= 0) {
                nimiBuilder.append(String.valueOf(tutkinnonOsaViiteDto.getLaajuus().intValue()));
            } else {
                nimiBuilder.append(tutkinnonOsaViiteDto.getLaajuus().toString());
            }
            nimiBuilder.append(" osp");
        }
        addTeksti(docBase, nimiBuilder.toString(), "p", td);
    }

    private void addTutkinnonosa(DokumenttiAmosaa docBase, SisaltoViiteExportDto sisaltoViiteDto) {
        TutkinnonosaExportDto tutkinnonOsa = docBase.getOpetussuunnitelma().getTutkinnonOsat().stream()
                .filter(tosaviite -> tosaviite.getTosa().getId().equals(sisaltoViiteDto.getTosa().getId())).findFirst().get().getTosa();

        switch (tutkinnonOsa.getTyyppi()) {
            case OMA:
                if (tutkinnonOsa.getOmatutkinnonosa() != null) {
                    addOmaTutkinnonOsa(docBase, tutkinnonOsa.getOmatutkinnonosa());
                }
                break;
            case PERUSTEESTA:
                if (tutkinnonOsa.getPerusteentutkinnonosa() != null) {
                    Optional<PerusteKaikkiDto> perusteKaikkiDto = docBase.getTutkinnonOsienPerusteet().stream()
                            .filter(peruste -> peruste.getTutkinnonOsat().stream()
                                    .anyMatch(dto -> dto.getId().equals(tutkinnonOsa.getPerusteentutkinnonosa())))
                            .findFirst();
                    addPerusteenTutkinnonOsa(docBase, tutkinnonOsa.getPerusteentutkinnonosa(), perusteKaikkiDto);
                }
                break;
            default:
                break;
        }

        // Tutkinnon osan vapaat tekstit
        tutkinnonOsa.getVapaat().forEach(vapaaTeksti -> {
            addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h5");
            addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
        });

        // Osaamisen osoittaminen
        if (tutkinnonOsa.getOsaamisenOsoittaminen() != null) {
            addTeksti(docBase, messages.translate("docgen.osaamisen-osoittaminen", docBase.getKieli()), "h5");
            addLokalisoituteksti(docBase, tutkinnonOsa.getOsaamisenOsoittaminen(), "div");
        }

        // Toteutukset
        if (tutkinnonOsa.getToteutukset().size() > 0) {
            addTeksti(docBase, messages.translate("docgen.toteutukset", docBase.getKieli()), "h5");

            tutkinnonOsa.getToteutukset().forEach(toteutus -> {
                addLokalisoituteksti(docBase, toteutus.getOtsikko(), "h6");
                boolean toteutuksellaSisaltoa = false;

                Element toteutusTaulukko = docBase.getDocument().createElement("table");
                toteutusTaulukko.setAttribute("border", "1");

                Element kooditTr = docBase.getDocument().createElement("tr");
                Element kooditTd = docBase.getDocument().createElement("th");
                kooditTr.appendChild(kooditTd);
                Element koodit = docBase.getDocument().createElement("div");
                kooditTd.appendChild(koodit);

                for (String koodiUri : toteutus.getKoodit()) {
                    KoodistoKoodiDto koodistoKoodiDto = koodistoClient.getByUri(koodiUri);
                    KoodistoMetadataDto[] metadata = koodistoKoodiDto.getMetadata();
                    for (KoodistoMetadataDto metadataDto : metadata) {
                        // Valitaan haluttu kieli
                        if (metadataDto.getKieli().toLowerCase().equals(docBase.getKieli().toString())) {
                            addTeksti(docBase,
                                    metadataDto.getNimi() + " (" + koodistoKoodiDto.getKoodiArvo() + ")",
                                    "p",
                                    koodit);
                            toteutuksellaSisaltoa = true;
                        }

                    }
                }

                if (toteutuksellaSisaltoa) {
                    DokumenttiTaulukko.addRow(docBase, toteutusTaulukko,
                            messages.translate("docgen.liitetyt-koodit", docBase.getKieli()), true);
                    toteutusTaulukko.appendChild(kooditTr);
                }

                TekstiosaDto tavatjaymparisto = toteutus.getTavatjaymparisto();
                if (tavatjaymparisto != null && tavatjaymparisto.getTeksti() != null) {
                    addTeksti(docBase, messages.translate("docgen.osaamisen-hankkiminen", docBase.getKieli()), "h6");
                    addLokalisoituteksti(docBase, tavatjaymparisto.getTeksti(), "div");
                    toteutuksellaSisaltoa = true;
                }

                TekstiosaDto arvioinnista = toteutus.getArvioinnista();
                if (arvioinnista != null && arvioinnista.getTeksti() != null) {
                    addTeksti(docBase, messages.translate("docgen.osaamisen-osoittaminen", docBase.getKieli()), "h6");
                    addLokalisoituteksti(docBase, arvioinnista.getTeksti(), "div");
                    toteutuksellaSisaltoa = true;
                }

                // Lisätään toteutuksen taulukko jos on sisältöä
                if (toteutuksellaSisaltoa) {
                    docBase.getBodyElement().appendChild(toteutusTaulukko);
                }

                toteutus.getVapaat().forEach(vapaaTeksti -> {
                    addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h6");
                    addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
                });
            });
        }

        if (!CollectionUtils.isEmpty(sisaltoViiteDto.getOsaAlueet())) {
            docBase.getGenerator().increaseDepth();
            sisaltoViiteDto.getOsaAlueet().stream()
                    .filter(osaAlue -> sisaltaaDokumentinKielenSisallon(docBase, osaAlue.getNimi()))
                    .sorted(Comparator.comparingInt(OmaOsaAlueExportDto::sort) )
                    .forEach(osaAlue -> {
                PerusteKaikkiDto tosaPeruste = docBase.getTutkinnonOsienPerusteet().stream()
                                                        .filter(peruste -> peruste.getId().equals(Optional.ofNullable(sisaltoViiteDto.getPeruste()).map(CachedPerusteBaseDto::getPerusteId).orElse(0L)))
                                                        .findAny().orElse(docBase.getPeruste());
                addOmaOsaAlue(docBase, osaAlue, tosaPeruste);
                docBase.getGenerator().increaseNumber();
            });
            docBase.getGenerator().decreaseDepth();
        }
    }

    private void addOmaOsaAlue(DokumenttiAmosaa docBase, OmaOsaAlueExportDto omaOsaAlueDto, PerusteKaikkiDto peruste) {
        StringBuilder otsikko = new StringBuilder(getTextString(docBase, omaOsaAlueDto.getNimi()));
        if (omaOsaAlueDto.getKoodiArvo() != null)  {
            otsikko.append(" (" + omaOsaAlueDto.getKoodiArvo().toUpperCase() + ")");
        }
        addHeader(docBase, otsikko.toString());

        if (omaOsaAlueDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, messages.translate("koulutuksen-jarjestajan-tarkennus", docBase.getKieli()), "h5");
            addTeksti(docBase, getTextString(docBase, omaOsaAlueDto.getPaikallinenTarkennus()), "div");
        }

        if (omaOsaAlueDto.getVapaat() != null) {
            omaOsaAlueDto.getVapaat().forEach(vapaaTeksti -> {
                addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h5");
                addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
            });
        }

        if (!CollectionUtils.isEmpty(omaOsaAlueDto.getToteutukset())) {
            addTeksti(docBase, messages.translate("docgen.koulutuksen-jarjestajan-toteutus", docBase.getKieli()), "h5");

            omaOsaAlueDto.getToteutukset().forEach(toteutus -> {
                addLokalisoituteksti(docBase, toteutus.getOtsikko(), "h6");

                Element toteutusTaulukko = docBase.getDocument().createElement("table");
                toteutusTaulukko.setAttribute("border", "1");

                Element kooditTr = docBase.getDocument().createElement("tr");
                Element kooditTd = docBase.getDocument().createElement("th");
                kooditTr.appendChild(kooditTd);
                Element koodit = docBase.getDocument().createElement("div");
                kooditTd.appendChild(koodit);

                TekstiosaDto tavatjaymparisto = toteutus.getTavatjaymparisto();
                if (tavatjaymparisto != null && tavatjaymparisto.getTeksti() != null) {
                    addTeksti(docBase, messages.translate("docgen.osaamisen-hankkiminen", docBase.getKieli()), "h6");
                    addLokalisoituteksti(docBase, tavatjaymparisto.getTeksti(), "div");
                }

                TekstiosaDto arvioinnista = toteutus.getArvioinnista();
                if (arvioinnista != null && arvioinnista.getTeksti() != null) {
                    addTeksti(docBase, messages.translate("docgen.osaamisen-osoittaminen", docBase.getKieli()), "h6");
                    addLokalisoituteksti(docBase, arvioinnista.getTeksti(), "div");
                }

                docBase.getBodyElement().appendChild(toteutusTaulukko);

                toteutus.getVapaat().forEach(vapaaTeksti -> {
                    addLokalisoituteksti(docBase, vapaaTeksti.getNimi(), "h6");
                    addLokalisoituteksti(docBase, vapaaTeksti.getTeksti(), "div");
                });
            });
        }

        StringBuilder sisaltoOtsikko = new StringBuilder();
        OsaAlueKaikkiDto perusteenOsaAlue = null;

        if (omaOsaAlueDto.getPerusteenOsaAlueId() != null) {
            perusteenOsaAlue = peruste.getTutkinnonOsat().stream()
                    .map(TutkinnonOsaKaikkiDto::getOsaAlueet)
                    .flatMap(Collection::stream)
                    .filter(posaAlue -> posaAlue.getId().equals(omaOsaAlueDto.getPerusteenOsaAlueId()))
                    .findFirst().orElse(null);

            if (perusteenOsaAlue == null) {
                return;
            }
        }

        if (perusteenOsaAlue != null) {
            addTeksti(docBase, messages.translate("docgen.perusteen-sisalto", docBase.getKieli()), "h5");
            if (omaOsaAlueDto.getTyyppi().equals(OmaOsaAlueTyyppi.PAKOLLINEN)) {
                sisaltoOtsikko.append(messages.translate("docgen.tutke2.pakolliset_osaamistavoitteet.title", docBase.getKieli()));
                sisaltoOtsikko.append(", " + perusteenOsaAlue.getPakollisetOsaamistavoitteet().getLaajuus());
            }

            if (omaOsaAlueDto.getTyyppi().equals(OmaOsaAlueTyyppi.VALINNAINEN)) {
                sisaltoOtsikko.append(messages.translate("docgen.tutke2.valinnaiset_osaamistavoitteet.title", docBase.getKieli()));
                sisaltoOtsikko.append(", " + perusteenOsaAlue.getValinnaisetOsaamistavoitteet().getLaajuus());
            }
        } else {
            addTeksti(docBase, messages.translate("docgen.sisalto", docBase.getKieli()), "h5");
            sisaltoOtsikko.append(messages.translate("docgen.osaamistavoitteet", docBase.getKieli()));
            sisaltoOtsikko.append(", " + omaOsaAlueDto.getLaajuus());
        }

        sisaltoOtsikko.append(" " + messages.translate("docgen.laajuus.osp", docBase.getKieli()));
        addTeksti(docBase, sisaltoOtsikko.toString(), "h6");

        if (perusteenOsaAlue != null) {

            if (omaOsaAlueDto.getTyyppi().equals(OmaOsaAlueTyyppi.PAKOLLINEN)) {
                addAmmattitaitovaatimukset2019(docBase, perusteenOsaAlue.getPakollisetOsaamistavoitteet().getTavoitteet());
            }

            if (omaOsaAlueDto.getTyyppi().equals(OmaOsaAlueTyyppi.VALINNAINEN)) {
                addAmmattitaitovaatimukset2019(docBase, perusteenOsaAlue.getValinnaisetOsaamistavoitteet().getTavoitteet());
            }

            addGeneerinenArviointi(docBase, mapper.map(perusteenOsaAlue.getArviointi(), GeneerinenArviointiasteikkoKaikkiDto.class));
        } else {
            addAmmattitaitovaatimukset2019(docBase, mapper.map(omaOsaAlueDto.getOsaamistavoitteet(), Ammattitaitovaatimukset2019Dto.class));
            addGeneerinenArviointi(docBase, omaOsaAlueDto.getGeneerinenArviointiasteikko());
        }
    }

    private void addGeneerinenArviointi(DokumenttiBase docBase, GeneerinenArviointiasteikkoKaikkiDto arviointi) {
        if (arviointi != null) {
            addTeksti(docBase, messages.translate("docgen.valma.osaamisenarviointi.title", docBase.getKieli()), "h5");

            List<GeneerisenArvioinninOsaamistasonKriteeriKaikkiDto> osaamistasonKriteerit = new ArrayList(arviointi.getOsaamistasonKriteerit());
            if (osaamistasonKriteerit.size() == 1 && osaamistasonKriteerit.get(0).getKriteerit().isEmpty()) {
                addTeksti(docBase, getTextString(docBase, osaamistasonKriteerit.get(0).getOsaamistaso().getOtsikko()), "div");
                return;
            }

            LokalisoituTekstiDto kohde = arviointi.getKohde();

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
                // EP-1996
                // th.appendChild(newBoldElement(docBase.getDocument(), getTextString(docBase, nimi)));
                Element kohdeEl = docBase.getDocument().createElement("p");
                kohdeEl.setTextContent(getTextString(docBase, kohde));
                th.appendChild(kohdeEl);
                tr.appendChild(th);
            }

            // Osaamistason kriteerit
            {
                arviointi.getOsaamistasonKriteerit().stream()
                        .filter(ok -> ok.getOsaamistaso() != null && !ObjectUtils.isEmpty(ok.getKriteerit()))
                        .sorted(Comparator.comparing(k -> k.getOsaamistaso().getId()))
                        .forEach(osaamistasonKriteeri -> {
                            Element tr = docBase.getDocument().createElement("tr");
                            tbody.appendChild(tr);

                            Element kriteeriTaso = docBase.getDocument().createElement("td");
                            kriteeriTaso.setAttribute("colspan", "1");
                            kriteeriTaso.setTextContent(getTextString(docBase,
                                    osaamistasonKriteeri.getOsaamistaso().getOtsikko()));
                            tr.appendChild(kriteeriTaso);

                            Element kriteeriKriteerit = docBase.getDocument().createElement("td");
                            kriteeriKriteerit.setAttribute("colspan", "3");

                            Element kriteeriLista = docBase.getDocument().createElement("ul");
                            kriteeriKriteerit.appendChild(kriteeriLista);

                            osaamistasonKriteeri.getKriteerit().forEach(kriteeriKriteeri -> {
                                String kriteeriKriteeriText = getTextString(docBase, kriteeriKriteeri);
                                if (org.apache.commons.lang.StringUtils.isNotEmpty(kriteeriKriteeriText)) {
                                    addTeksti(docBase, kriteeriKriteeriText, "li", kriteeriLista);
                                }
                            });
                            kriteeriKriteerit.appendChild(kriteeriLista);
                            tr.appendChild(kriteeriKriteerit);
                        });
            }
        }
    }


    private void addOmaTutkinnonOsa(DokumenttiBase docBase, OmaTutkinnonosaExportDto omaTutkinnonosa) {

        // Laajuus
        if (omaTutkinnonosa.getLaajuus() != null) {
            addTeksti(docBase, messages.translate("docgen.laajuus", docBase.getKieli()), "h5");
            addTeksti(docBase, omaTutkinnonosa.getLaajuus().toString(), "div");
        }

        // Koodi
        if (omaTutkinnonosa.getKoodi() != null) {
            addTeksti(docBase, messages.translate("docgen.koodi", docBase.getKieli()), "h5");
            addTeksti(docBase, omaTutkinnonosa.getKoodi(), "div");
        }

        // Tavoitteet
        if (omaTutkinnonosa.getTavoitteet() != null) {
            addTeksti(docBase, messages.translate("docgen.tavoitteet", docBase.getKieli()), "h5");
            addLokalisoituteksti(docBase, omaTutkinnonosa.getTavoitteet(), "div");
        }


        // Ammattitaitovaatimukset
        if (omaTutkinnonosa.getAmmattitaitovaatimuksetLista().size() > 0 || omaTutkinnonosa.getAmmattitaitovaatimukset() != null) {
            addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");

            if (omaTutkinnonosa.getAmmattitaitovaatimukset() != null) {
                addAmmattitaitovaatimukset2019(docBase, mapper.map(omaTutkinnonosa.getAmmattitaitovaatimukset(), Ammattitaitovaatimukset2019Dto.class));
            }

            if (omaTutkinnonosa.getAmmattitaitovaatimuksetLista().size() > 0) {
                omaTutkinnonosa.getAmmattitaitovaatimuksetLista()
                        .forEach(ammattitaitovaatimuksenKohdealue -> addAmmattitaitovaatimuksenKohdealue(docBase, ammattitaitovaatimuksenKohdealue));
            }
        }

        // Arviointi
        if (omaTutkinnonosa.getArviointi() != null) {
            addTeksti(docBase, messages.translate("docgen.arviointi", docBase.getKieli()), "h5");
            ArviointiDto arviointi = mapper.map(omaTutkinnonosa.getArviointi(), ArviointiDto.class);
            arviointi.getArvioinninKohdealueet()
                    .forEach(arvioinninKohdealue -> addArvioinninKohdealue(docBase, arvioinninKohdealue));
        }

        if (omaTutkinnonosa.getGeneerinenArviointiasteikko() != null) {
            addGeneerinenArviointi(docBase, omaTutkinnonosa.getGeneerinenArviointiasteikko());
        }

        // Ammattitaidon osoittamistavat
        if (omaTutkinnonosa.getAmmattitaidonOsoittamistavat() != null) {
            addTeksti(docBase, messages.translate("docgen.ammattitaidon-osoittamistavat", docBase.getKieli()), "h5");
            addLokalisoituteksti(docBase, omaTutkinnonosa.getAmmattitaidonOsoittamistavat(), "div");
        }
    }

    private void addAmmattitaitovaatimukset2019(DokumenttiBase docBase, Ammattitaitovaatimukset2019Dto ammattitaitovaatimukset2019) {
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

    private void addAmmattitaitovaatimuksenKohdealue(DokumenttiBase docBase,
                                                     AmmattitaitovaatimuksenKohdealueDto ammattitaitovaatimuksenKohdealue) {
        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
        addVaatimuksenKohteet(docBase, ammattitaitovaatimuksenKohdealue.getVaatimuksenKohteet(), taulukko);
        taulukko.addToDokumentti(docBase);
    }

    private void addVaatimuksenKohteet(DokumenttiBase docBase,
                                       List<AmmattitaitovaatimuksenKohdeDto> vaatimuksenKohteet,
                                       DokumenttiTaulukko taulukko) {
        StringBuilder vaatimuksenKohteetBuilder = new StringBuilder();
        vaatimuksenKohteet.forEach(ammattitaitovaatimuksenKohde -> {
            if (ammattitaitovaatimuksenKohde.getOtsikko() != null) {
                vaatimuksenKohteetBuilder.append("<h6>");
                vaatimuksenKohteetBuilder.append(getTextString(docBase, ammattitaitovaatimuksenKohde.getOtsikko()));
                vaatimuksenKohteetBuilder.append("</h6>");
            }

            if (ammattitaitovaatimuksenKohde.getSelite() != null) {
                vaatimuksenKohteetBuilder.append("<p>");
                vaatimuksenKohteetBuilder.append(ammattitaitovaatimuksenKohde.getSelite());
                vaatimuksenKohteetBuilder.append("</p>");
            }

            addVaatimukset(docBase, ammattitaitovaatimuksenKohde.getVaatimukset(), vaatimuksenKohteetBuilder);
        });

        DokumenttiRivi rivi = new DokumenttiRivi();
        rivi.addSarake(vaatimuksenKohteetBuilder.toString());
        taulukko.addRivi(rivi);
    }

    private void addVaatimukset(DokumenttiBase docBase,
                                List<AmmattitaitovaatimusDto> ammattitaitovaatimukset,
                                StringBuilder vaatimuksenKohteetBuilder) {
        vaatimuksenKohteetBuilder.append("<ul>");
        ammattitaitovaatimukset.forEach(vaatimus -> {
            vaatimuksenKohteetBuilder.append("<li>");
            vaatimuksenKohteetBuilder.append(getTextString(docBase, vaatimus.getSelite()));
            if (vaatimus.getAmmattitaitovaatimusKoodi() != null) {
                vaatimuksenKohteetBuilder.append(" (");
                vaatimuksenKohteetBuilder.append(vaatimus.getAmmattitaitovaatimusKoodi());
                vaatimuksenKohteetBuilder.append(")");
            }
            vaatimuksenKohteetBuilder.append("</li>");
        });
        vaatimuksenKohteetBuilder.append("</ul>");
    }

    private void addArvioinninKohdealue(DokumenttiBase docBase, ArvioinninKohdealueDto arvioinninKohdealue) {
        DokumenttiTaulukko arviointiTaulukko = new DokumenttiTaulukko();

        if (!getTextString(docBase, arvioinninKohdealue.getOtsikko()).equals("automaattinen")) {
            arviointiTaulukko.addOtsikkoSarake(getTextString(docBase, arvioinninKohdealue.getOtsikko()));
        }
        addArvioinninKohteet(docBase, arvioinninKohdealue.getArvioinninKohteet(), arviointiTaulukko);

        arviointiTaulukko.addToDokumentti(docBase);
    }

    private void addArvioinninKohteet(DokumenttiBase docBase, List<ArvioinninKohdeDto> arvioinninKohteet, DokumenttiTaulukko arviointiTaulukko) {
        StringBuilder arvioinninKohteetBuilder = new StringBuilder();

        arvioinninKohteet.forEach(arvioinninKohde -> {
            if (arvioinninKohde.getOtsikko() != null) {
                arvioinninKohteetBuilder.append("<h6>");
                arvioinninKohteetBuilder.append(getTextString(docBase, arvioinninKohde.getOtsikko()));
                arvioinninKohteetBuilder.append("</h6>");
            }

            if (arvioinninKohde.getSelite() != null) {
                arvioinninKohteetBuilder.append("<p>");
                arvioinninKohteetBuilder.append(getTextString(docBase, arvioinninKohde.getSelite()));
                arvioinninKohteetBuilder.append("</p>");
            }

            // Add kohteen taulukko
            addOsaamistasonKriteerit(docBase, arvioinninKohde.getOsaamistasonKriteerit(), arvioinninKohteetBuilder);
        });


        DokumenttiRivi rivi = new DokumenttiRivi();
        rivi.addSarake(arvioinninKohteetBuilder.toString());
        arviointiTaulukko.addRivi(rivi);
    }

    private void addOsaamistasonKriteerit(DokumenttiBase docBase,
                                          Set<OsaamistasonKriteeriDto> osaamistasonKriteerit,
                                          StringBuilder arvioinninKohteetBuilder) {
        DokumenttiTaulukko taulukko = new DokumenttiTaulukko();

        osaamistasonKriteerit.stream()
                .filter(o -> o.getOsaamistaso() != null)
                .sorted(Comparator.comparing(o -> o.getOsaamistaso().getId()))
                .forEach(osaamistasonKriteeri -> {
                    DokumenttiRivi rivi = new DokumenttiRivi();

                    if (Optional.ofNullable(osaamistasonKriteeri.getOsaamistasoDto()).map(OsaamistasoDto::getOtsikko).isPresent()) {
                        rivi.addSarake(getTextString(docBase, osaamistasonKriteeri.getOsaamistasoDto().getOtsikko()));
                    }

                    StringBuilder kriteeritBuilder = new StringBuilder();
                    kriteeritBuilder.append("<ul>");
                    osaamistasonKriteeri.getKriteerit().forEach(kriteeri -> {
                        kriteeritBuilder.append("<li>");
                        kriteeritBuilder.append(getTextString(docBase, kriteeri));
                        kriteeritBuilder.append("</li>");
                    });
                    kriteeritBuilder.append("</ul>");
                    rivi.addSarake(kriteeritBuilder.toString());

                    taulukko.addRivi(rivi);
                });

        arvioinninKohteetBuilder.append(taulukko.toString());
    }

    private void addPerusteenTutkinnonOsa(DokumenttiAmosaa docBase, Long perusteenTutkinnonosaId, Optional<PerusteKaikkiDto> peruste) {
        if (peruste.isEmpty()) {
            return;
        }

        Optional<TutkinnonOsaKaikkiDto> optPerusteenTutkinnonosa = peruste.get().getTutkinnonOsat().stream()
                .filter(dto -> dto.getId().equals(perusteenTutkinnonosaId))
                .findFirst();

        if (optPerusteenTutkinnonosa.isPresent()) {
            TutkinnonOsaKaikkiDto perusteenTutkinnonosa = optPerusteenTutkinnonosa.get();

            addLokalisoituteksti(docBase, perusteenTutkinnonosa.getKuvaus(), "div");

            if (perusteenTutkinnonosa.getTyyppi().equals(TutkinnonOsaTyyppi.NORMAALI)) {

                // Tavoitteet
                if (perusteenTutkinnonosa.getTavoitteet() != null) {
                    addTeksti(docBase, messages.translate("docgen.tavoitteet", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getTavoitteet(), "div");
                }

                // Ammattitaitovaatimukset
                if (perusteenTutkinnonosa.getAmmattitaitovaatimukset2019() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
                    addAmmattitaitovaatimukset2019(docBase, perusteenTutkinnonosa.getAmmattitaitovaatimukset2019());
                } else if (perusteenTutkinnonosa.getAmmattitaitovaatimukset() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getAmmattitaitovaatimukset(), "div");
                } else if (!CollectionUtils.isEmpty(perusteenTutkinnonosa.getAmmattitaitovaatimuksetLista())) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaitovaatimukset", docBase.getKieli()), "h5");
                    perusteenTutkinnonosa.getAmmattitaitovaatimuksetLista().forEach(dto -> addAmmattitaitovaatimuksenKohdealue(docBase, mapper.map(dto, AmmattitaitovaatimuksenKohdealueDto.class)));
                }

                // Arviointi
                if (perusteenTutkinnonosa.getArviointi() != null && !CollectionUtils.isEmpty(perusteenTutkinnonosa.getArviointi().getArvioinninKohdealueet())) {
                    addTeksti(docBase, messages.translate("docgen.arviointi", docBase.getKieli()), "h5");
                    fi.vm.sade.eperusteet.pdf.dto.eperusteet.arviointi.ArviointiDto arviointiDto = perusteenTutkinnonosa.getArviointi();

                    arviointiDto.getArvioinninKohdealueet().forEach(arvioinninKohdealue -> {
                        arvioinninKohdealue.getArvioinninKohteet().forEach(arvioinninKohde -> {
                            if (arvioinninKohde.getArviointiAsteikko() != null && arvioinninKohde.getArviointiAsteikkoDto() == null) {
                                ArviointiAsteikkoDto arviointiAsteikkoDto = amosaaService.getArviointiasteikko(arvioinninKohde.getArviointiAsteikko().getIdLong());
                                arvioinninKohde.setArviointiAsteikkoDto(arviointiAsteikkoDto);

                                arvioinninKohde.getOsaamistasonKriteerit().forEach(osaamistasonKriteeri -> {
                                    if (osaamistasonKriteeri.getOsaamistaso() != null && osaamistasonKriteeri.getOsaamistasoDto() == null) {
                                        Optional<OsaamistasoDto> optOsaamistaso = arviointiAsteikkoDto.getOsaamistasot().stream()
                                                .filter(o -> o.getId().equals(osaamistasonKriteeri.getOsaamistaso().getIdLong()))
                                                .findFirst();
                                        optOsaamistaso.ifPresent(osaamistasonKriteeri::setOsaamistasoDto);
                                    }
                                });
                            }
                        });

                        addArvioinninKohdealue(docBase, arvioinninKohdealue);
                    });
                }

                addGeneerinenArviointi(docBase, perusteenTutkinnonosa.getGeneerinenArviointiasteikko());

                // Ammattitaidon osoittamistavat
                if (perusteenTutkinnonosa.getAmmattitaidonOsoittamistavat() != null) {
                    addTeksti(docBase, messages.translate("docgen.ammattitaidon-osoittamistavat", docBase.getKieli()), "h5");
                    addLokalisoituteksti(docBase, perusteenTutkinnonosa.getAmmattitaidonOsoittamistavat(), "div");
                }

                // Valma/Telma-sisältö
                if (perusteenTutkinnonosa.getValmaTelmaSisalto() != null) {
                    addValmatelmaSisalto(docBase, perusteenTutkinnonosa.getValmaTelmaSisalto());
                }

            } else if (TutkinnonOsaTyyppi.isTutke(perusteenTutkinnonosa.getTyyppi())) {
                if (!ObjectUtils.isEmpty(perusteenTutkinnonosa.getOsaAlueet())) {
                    perusteenTutkinnonosa.getOsaAlueet().stream()
                            .filter(osaAlue -> osaAlue.getNimi() != null && (!ObjectUtils.isEmpty(osaAlue.getOsaamistavoitteet()) || osaAlue.getValmaTelmaSisalto() != null))
                            .forEach(osaAlue -> {

                                // Nimi
                                addTeksti(docBase, getTextString(docBase, osaAlue.getNimi()), "h5");

                                // Kuvaus
                                if (osaAlue.getKuvaus() != null) {
                                    addTeksti(docBase, getTextString(docBase, osaAlue.getKuvaus()), "div");
                                }

                                // Osaamistavoitteet
                                if (!ObjectUtils.isEmpty(osaAlue.getOsaamistavoitteet())) {
                                    osaAlue.getOsaamistavoitteet().forEach(osaamistavoite -> {

                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getNimi()), "h5");

                                        String otsikkoAvain = osaamistavoite.isPakollinen() ? "docgen.tutke2.pakolliset_osaamistavoitteet.title"
                                                : "docgen.tutke2.valinnaiset_osaamistavoitteet.title";
                                        String otsikko = messages.translate(otsikkoAvain, docBase.getKieli());
                                        addTeksti(docBase, otsikko, "h5");

                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getTavoitteet()), "div");

                                        // Arviointi
                                        addTeksti(docBase, messages.translate("docgen.tutke2.arvioinnin_kohteet.title", docBase.getKieli()), "h6");
                                        ArviointiDto arviointi = osaamistavoite.getArviointi();
                                        arviointi.getArvioinninKohdealueet().forEach(dto -> addArvioinninKohdealue(docBase, dto));

                                        // Tunnustaminen
                                        addTeksti(docBase, messages.translate("docgen.tutke2.tunnustaminen.title", docBase.getKieli()), "h6");
                                        addTeksti(docBase, getTextString(docBase, osaamistavoite.getTunnustaminen()), "div");

                                        // Ammattitaitovaatimukset
                                        List<AmmattitaitovaatimusKohdealueetDto> ammattitaitovaatimukset = osaamistavoite.getAmmattitaitovaatimuksetLista();
                                        ammattitaitovaatimukset.forEach(dto -> addAmmattitaitovaatimuksenKohdealue(docBase, mapper.map(dto, AmmattitaitovaatimuksenKohdealueDto.class)));
                                    });
                                }

                                if (osaAlue.getValmaTelmaSisalto() != null) {
                                    addValmatelmaSisalto(docBase, osaAlue.getValmaTelmaSisalto());
                                }
                            });
                }
            }
        }
    }

    private void addOpintokokonaisuus(DokumenttiBase docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        OpintokokonaisuusDto opintokokonaisuus = sisaltoViiteExportDto.getOpintokokonaisuus();

        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getKuvaus()), "div");

        String tavoiteTeksti = OpintokokonaisuusTyyppi.OMA.equals(opintokokonaisuus.getTyyppi()) ? "docgen.osaamistavoitteet" : "docgen.opetuksen-tavoitteet.title";
        addTeksti(docBase, messages.translate(tavoiteTeksti, docBase.getKieli()), "h5");

        if (opintokokonaisuus.getTavoitteidenKuvaus() != null) {
            addTeksti(docBase, messages.translate("docgen.tavoitteiden-kuvaus", docBase.getKieli()), "h6");
            addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getTavoitteidenKuvaus()), "div");
        }

        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getOpetuksenTavoiteOtsikko()), "h6");
        Element tavoitteetEl = docBase.getDocument().createElement("ul");

        opintokokonaisuus.getTavoitteet().forEach(tavoite -> {
            Element tavoiteEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, tavoite.getTavoite());
            tavoiteEl.setTextContent(rivi);
            tavoitteetEl.appendChild(tavoiteEl);
        });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        addTeksti(docBase, messages.translate("docgen.keskeiset-sisallot.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getKeskeisetSisallot()), "div");

        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");

        if (opintokokonaisuus.getArvioinninKuvaus() != null) {
            addTeksti(docBase, messages.translate("docgen.arvioinnin-kuvaus", docBase.getKieli()), "h6");
            addTeksti(docBase, getTextString(docBase, opintokokonaisuus.getArvioinninKuvaus()), "div");
        }

        addTeksti(docBase, messages.translate("docgen.osaamisen-arvioinnin-kriteerit", docBase.getKieli()), "h6");
        Element arvioinnitEl = docBase.getDocument().createElement("ul");

        opintokokonaisuus.getArvioinnit().forEach(arviointi -> {
            Element arviointiEl = docBase.getDocument().createElement("li");
            String rivi = getTextString(docBase, arviointi.getArviointi());
            arviointiEl.setTextContent(rivi);
            arvioinnitEl.appendChild(arviointiEl);
        });
        docBase.getBodyElement().appendChild(arvioinnitEl);
    }

    private void addLaajaalainenOsaaminen(DokumenttiBase docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        TuvaLaajaAlainenOsaaminenDto tuvaLaajaAlainenOsaaminenDto = sisaltoViiteExportDto.getTuvaLaajaAlainenOsaaminen();
        addTeksti(docBase, getTextString(docBase, tuvaLaajaAlainenOsaaminenDto.getTeksti()), "div");
    }

    private void addKoulutuksenosa(DokumenttiBase docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        Optional<PerusteenOsaViiteDto.Laaja> perusteenOsaViite = docBase.getPerusteenOsa(sisaltoViiteExportDto.getPerusteenOsaId());
        fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto perusteenOsaDto = null;
        if (perusteenOsaViite.isPresent()) {
            perusteenOsaDto = (fi.vm.sade.eperusteet.pdf.dto.eperusteet.tuva.KoulutuksenOsaDto)perusteenOsaViite.get().getPerusteenOsa();
        }
        KoulutuksenOsaDto koulutuksenOsaDto = sisaltoViiteExportDto.getKoulutuksenosa();
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto != null ? perusteenOsaDto.getKuvaus() : koulutuksenOsaDto.getKuvaus()), "div");

        addTeksti(docBase, messages.translate("docgen.tavoitteet.title", docBase.getKieli()), "h5");
        Element tavoitteetEl = docBase.getDocument().createElement("ul");
        Stream.concat(
                Stream.of(perusteenOsaDto != null ? perusteenOsaDto.getTavoitteet() : koulutuksenOsaDto.getTavoitteet()),
                Stream.of(koulutuksenOsaDto.getPaikallinenTarkennus() != null ? koulutuksenOsaDto.getPaikallinenTarkennus().getTavoitteet() : Collections.<LokalisoituTekstiDto>emptyList()))
                .flatMap(Collection::stream)
                .forEach(tavoite -> {
                    Element tavoiteEl = docBase.getDocument().createElement("li");
                    String rivi = getTextString(docBase, tavoite);
                    tavoiteEl.setTextContent(rivi);
                    tavoitteetEl.appendChild(tavoiteEl);
                });
        docBase.getBodyElement().appendChild(tavoitteetEl);

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getTavoitteetKuvaus()), "div");
        }

        if (!koulutuksenOsaDto.getPaikallinenTarkennus().getLaajaalaisetosaamiset().isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.laaja-alainen-osaaminen.title", docBase.getKieli()), "h5");
            koulutuksenOsaDto.getPaikallinenTarkennus().getLaajaalaisetosaamiset().forEach(lao -> {
                addTeksti(docBase, getTextString(docBase, lao.getNimi()), "h6");
                addTeksti(docBase, getTextString(docBase, lao.getLaajaAlaisenOsaamisenKuvaus()), "div");
            });
        }


        addTeksti(docBase, messages.translate("docgen.keskeinen-sisalto.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto != null ? perusteenOsaDto.getKeskeinenSisalto() : koulutuksenOsaDto.getKeskeinenSisalto()), "div");
        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getKeskeinenSisalto()), "div");
        }

        addTeksti(docBase, messages.translate("docgen.arviointi.title", docBase.getKieli()), "h5");
        addTeksti(docBase, getTextString(docBase, perusteenOsaDto != null ? perusteenOsaDto.getArvioinninKuvaus() : koulutuksenOsaDto.getArvioinninKuvaus()), "div");
        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null) {
            addTeksti(docBase, getTextString(docBase, koulutuksenOsaDto.getPaikallinenTarkennus().getArvioinninKuvaus()), "div");
        }

        if (koulutuksenOsaDto.getPaikallinenTarkennus() != null && !CollectionUtils.isEmpty(koulutuksenOsaDto.getPaikallinenTarkennus().getKoulutuksenJarjestajat())) {
            addTeksti(docBase, messages.translate("docgen.koulutuksen-jarjestajat.title", docBase.getKieli()), "h5");

            koulutuksenOsaDto.getPaikallinenTarkennus().getKoulutuksenJarjestajat().forEach(koulutuksenJarjestajaDto -> {
                addTeksti(docBase, getTextString(docBase, koulutuksenJarjestajaDto.getNimi()), "h5");

                addTeksti(docBase, messages.translate("docgen.toteutusuunnitelman-tai-koulutuksen-jarjestajan-verkkosivut.title", docBase.getKieli()), "h6");

                Element linkkiDiv = docBase.getDocument().createElement("div");
                Element koulutuksenjarjestajanUrl = docBase.getDocument().createElement("a");
                koulutuksenjarjestajanUrl.setTextContent(getTextString(docBase, koulutuksenJarjestajaDto.getUrl()));
                koulutuksenjarjestajanUrl.setAttribute("href", getTextString(docBase, koulutuksenJarjestajaDto.getUrl()));
                linkkiDiv.appendChild(koulutuksenjarjestajanUrl);
                docBase.getBodyElement().appendChild(linkkiDiv);

                addTeksti(docBase, messages.translate("docgen.kaytannon-toteutus.title", docBase.getKieli()), "h6");
                addTeksti(docBase, getTextString(docBase, koulutuksenJarjestajaDto.getKuvaus()), "div");
            });
        }

    }

    private void addKotoLaajaAlainenOsaaminen(DokumenttiBase docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        Optional<PerusteenOsaViiteDto.Laaja> perusteenOsaViite = docBase.getPerusteenOsa(sisaltoViiteExportDto.getPerusteenOsaId());

        if (perusteenOsaViite.isPresent()) {
            KotoLaajaAlainenOsaaminenDto perusteenOsaDto = (KotoLaajaAlainenOsaaminenDto)perusteenOsaViite.get().getPerusteenOsa();
            addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getYleiskuvaus()), "div");

            perusteenOsaDto.getOsaamisAlueet().forEach(osaamisalue -> {
                addTeksti(docBase, getTextString(docBase, osaamisalue.getKoodi().getNimi()), "h6");
                addTeksti(docBase, getTextString(docBase, osaamisalue.getKuvaus()), "div");
            });

            addTeksti(docBase, messages.translate("docgen.laaja-alaisen-osaamisen-paikallinen-tarkennus", docBase.getKieli()), "h6");
            addTeksti(docBase, getTextString(docBase, sisaltoViiteExportDto.getKotoLaajaAlainenOsaaminen().getTeksti()), "div");
        }
    }

    private void addKotoKielitaitotaso(DokumenttiAmosaa docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        Optional<PerusteenOsaViiteDto.Laaja> perusteenOsaViite = docBase.getPerusteenOsa(sisaltoViiteExportDto.getPerusteenOsaId());
        if (perusteenOsaViite.isPresent()) {
            KotoKielitaitotasoDto perusteenOsaDto = (KotoKielitaitotasoDto) perusteenOsaViite.get().getPerusteenOsa();
            addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getKuvaus()), "div");

            Map<String, KotoTaitotasoDto> taitotasoMap = sisaltoViiteExportDto.getKotoKielitaitotaso().getTaitotasot().stream().collect(Collectors.toMap((KotoTaitotasoDto::getKoodiUri), (taitotaso -> taitotaso)));
            addKotoTaitotasot(docBase, taitotasoMap, perusteenOsaDto.getTaitotasot(), "docgen.tavoitteet.title");

            addKotoTaitotasoLaajaAlaisetOsaamiset(docBase, sisaltoViiteExportDto.getKotoKielitaitotaso().getLaajaAlaisetOsaamiset());
        }
    }

    private void addKotoOpinto(DokumenttiAmosaa docBase, SisaltoViiteExportDto sisaltoViiteExportDto) {
        Optional<PerusteenOsaViiteDto.Laaja> perusteenOsaViite = docBase.getPerusteenOsa(sisaltoViiteExportDto.getPerusteenOsaId());
        if (perusteenOsaViite.isPresent()) {
            KotoOpintoDto perusteenOsaDto = (KotoOpintoDto) perusteenOsaViite.get().getPerusteenOsa();
            addTeksti(docBase, getTextString(docBase, perusteenOsaDto.getKuvaus()), "div");

            Map<String, KotoTaitotasoDto> taitotasoMap = sisaltoViiteExportDto.getKotoOpinto().getTaitotasot().stream().collect(Collectors.toMap((KotoTaitotasoDto::getKoodiUri), (taitotaso -> taitotaso)));
            addKotoTaitotasot(docBase, taitotasoMap, perusteenOsaDto.getTaitotasot(), "docgen.tavoitteet-ja-sisallot.title");

            addKotoTaitotasoLaajaAlaisetOsaamiset(docBase, sisaltoViiteExportDto.getKotoOpinto().getLaajaAlaisetOsaamiset());
        }
    }

    private void addKotoTaitotasoLaajaAlaisetOsaamiset(DokumenttiAmosaa docBase, List<KotoTaitotasoLaajaAlainenOsaaminenDto> laajaAlaisetOsaamiset) {
        List<SisaltoViiteExportDto> kotoLaot = CollectionUtil.treeToStream(docBase.getOpetussuunnitelma().getSisalto(), SisaltoViiteExportDto::getLapset)
                .filter(viite -> viite.getKotoLaajaAlainenOsaaminen() != null)
                .collect(Collectors.toList());

        Map<String, KotoLaajaAlaisenOsaamisenAlueDto> perusteenLaot = kotoLaot.stream()
                .map(laoViite -> docBase.getPerusteenOsa(laoViite.getPerusteenOsaId()).get())
                .map(perusteenOsaDto -> ((KotoLaajaAlainenOsaaminenDto) perusteenOsaDto.getPerusteenOsa()))
                .map(KotoLaajaAlainenOsaaminenDto::getOsaamisAlueet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap((lao -> lao.getKoodi().getUri()), (lao -> lao)));

        if (!laajaAlaisetOsaamiset.isEmpty()) {
            addTeksti(docBase, messages.translate("docgen.laaja-alainen-osaaminen.title", docBase.getKieli()), "h5");
        }

        laajaAlaisetOsaamiset.forEach(lao -> {
            KotoLaajaAlaisenOsaamisenAlueDto perusteenLao = perusteenLaot.get(lao.getKoodiUri());
            addTeksti(docBase, getTextString(docBase, perusteenLao.getKoodi().getNimi()), "h6");
            addTeksti(docBase, getTextString(docBase, perusteenLao.getKuvaus()), "div");

            addTeksti(docBase, getTextString(docBase, lao.getTeksti()), "div");
        });
    }

    private void addKotoTaitotasot(DokumenttiBase docBase, Map<String, KotoTaitotasoDto> taitotasoMap, List<fi.vm.sade.eperusteet.pdf.dto.eperusteet.vst.KotoTaitotasoDto> taitotasot, String tavoiteTitle) {

        taitotasot.forEach(taitotaso -> {

            addTeksti(docBase, getTextString(docBase, taitotaso.getNimi().getNimi()), "h5");

            String tavoitteet = getTextString(docBase, taitotaso.getTavoitteet());
            addTextWithTopic(tavoitteet, tavoiteTitle, docBase);

            if (taitotasoMap.get(taitotaso.getNimi().getUri()) != null) {
                KotoTaitotasoDto opsTaitotaso = taitotasoMap.get(taitotaso.getNimi().getUri());

                if (opsTaitotaso.getTavoiteTarkennus() != null) {
                    String tavoiteTarkennus = getTextString(docBase, opsTaitotaso.getTavoiteTarkennus());
                    addTextWithTopic(tavoiteTarkennus, "docgen.tavoitteiden-paikallinen-tarkennus.title", docBase);
                }

                if (opsTaitotaso.getSisaltoTarkennus() != null) {
                    String sisaltoTarkennus = getTextString(docBase, opsTaitotaso.getSisaltoTarkennus());
                    addTextWithTopic(sisaltoTarkennus, "docgen.sisaltojen-paikallinen-tarkennus.title", docBase);
                }
            }

            String kielenkayttotarkoitus = getTextString(docBase, taitotaso.getKielenkayttotarkoitus());
            String aihealueet = getTextString(docBase, taitotaso.getAihealueet());
            String viestintataidot = getTextString(docBase, taitotaso.getViestintataidot());
            String opiskelijantaidot = getTextString(docBase, taitotaso.getOpiskelijantaidot());

            String opiskelijanTyoelamataidot = getTextString(docBase, taitotaso.getOpiskelijanTyoelamataidot());
            String suullinenVastaanottaminen = getTextString(docBase, taitotaso.getSuullinenVastaanottaminen());
            String suullinenTuottaminen = getTextString(docBase, taitotaso.getSuullinenTuottaminen());
            String vuorovaikutusJaMediaatio = getTextString(docBase, taitotaso.getVuorovaikutusJaMediaatio());

            if (StringUtils.isNotEmpty(kielenkayttotarkoitus)
                    || StringUtils.isNotEmpty(aihealueet)
                    || StringUtils.isNotEmpty(viestintataidot)
                    || StringUtils.isNotEmpty(opiskelijantaidot)
                    || StringUtils.isNotEmpty(opiskelijanTyoelamataidot)
                    || StringUtils.isNotEmpty(suullinenVastaanottaminen)
                    || StringUtils.isNotEmpty(suullinenTuottaminen)
                    || StringUtils.isNotEmpty(vuorovaikutusJaMediaatio)) {
                addTeksti(docBase, messages.translate("docgen.keskeiset-sisallot.title", docBase.getKieli()), "h5");
            }

            addTextWithTopic(kielenkayttotarkoitus, "docgen.kielenkayttotarkoitus.title", docBase);
            addTextWithTopic(aihealueet, "docgen.aihealueet.title", docBase);
            addTextWithTopic(viestintataidot, "docgen.viestintataidot.title", docBase);
            addTextWithTopic(opiskelijantaidot, "docgen.opiskelijantaidot.title", docBase);

            addTextWithTopic(opiskelijanTyoelamataidot, "docgen.opiskelijan_tyoelamataidot.title", docBase);
            addTextWithTopic(suullinenVastaanottaminen, "docgen.suullinen_vastaanottaminen.title", docBase);
            addTextWithTopic(suullinenTuottaminen, "docgen.suullinen_tuottaminen.title", docBase);
            addTextWithTopic(vuorovaikutusJaMediaatio, "docgen.vuorovaikutus_ja_mediaatio.title", docBase);
        });
    }

    private void addTextWithTopic(String text, String translationKey, DokumenttiBase docBase) {
        if (StringUtils.isNotEmpty(text)) {
            addTeksti(docBase, messages.translate(translationKey, docBase.getKieli()), "h6");
            addTeksti(docBase, text, "div");
        }
    }

    private void addValmatelmaSisalto(DokumenttiBase docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        addValmaOsaamistavoitteet(docBase, valmaTelmaSisalto.getOsaamistavoite());
        addValmaArviointi(docBase, valmaTelmaSisalto);
    }

    private void addValmaOsaamistavoitteet(DokumenttiBase docBase, List<OsaamisenTavoiteDto> OsaamisenTavoiteet) {
        if (ObjectUtils.isEmpty(OsaamisenTavoiteet)) {
            return;
        }

        addTeksti(docBase, messages.translate("docgen.valma.osaamistavoitteet.title", docBase.getKieli()), "h5");

        for (OsaamisenTavoiteDto osaamisenTavoite : OsaamisenTavoiteet) {
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

    private void addValmaArviointi(DokumenttiBase docBase, ValmaTelmaSisaltoDto valmaTelmaSisalto) {
        if (valmaTelmaSisalto.getOsaamisenarviointi() != null
                || valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {

            addTeksti(docBase, messages.translate("docgen.valma.osaamisenarviointi.title", docBase.getKieli()), "h5");

            if (valmaTelmaSisalto.getOsaamisenarviointi() != null) {
                if (valmaTelmaSisalto.getOsaamisenarviointi().getKohde() != null) {
                    addTeksti(docBase,
                            getTextString(docBase, valmaTelmaSisalto.getOsaamisenarviointi().getKohde()),
                            "div");
                }

                if (!ObjectUtils.isEmpty(valmaTelmaSisalto.getOsaamisenarviointi().getTavoitteet())) {
                    Element lista = docBase.getDocument().createElement("ul");
                    docBase.getBodyElement().appendChild(lista);

                    valmaTelmaSisalto.getOsaamisenarviointi().getTavoitteet().forEach(tavoite -> {
                        Element alkio = docBase.getDocument().createElement("li");
                        alkio.setTextContent(getTextString(docBase, tavoite));
                        lista.appendChild(alkio);
                    });
                }
            }

            if (valmaTelmaSisalto.getOsaamisenarviointiTekstina() != null) {
                addTeksti(docBase,
                        valmaTelmaSisalto.getOsaamisenarviointiTekstina().getTekstit().get(docBase.getKieli()),
                        "div");
            }
        }
    }

    private void buildFootnotes(DokumenttiAmosaa docBase) {
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

                    if (docBase.getOpetussuunnitelma() != null && docBase.getOpetussuunnitelma().getId() != null) {
                        TermiDto termi = dokumenttiUtilService.getTermiFromExternalService(docBase.getOpetussuunnitelma().getKoulutustoimija().getId(), avain, docBase.getGeneratorData().getTyyppi());

                        if (termi != null && termi.getAlaviite() != null && termi.getAlaviite() && termi.getSelitys() != null) {
                            element.setAttribute("number", String.valueOf(noteNumber));

                            LokalisoituTekstiDto tekstiDto = termi.getSelitys();
                            String selitys = getTextString(docBase, tekstiDto).replaceAll("<[^>]+>", "");
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
}
