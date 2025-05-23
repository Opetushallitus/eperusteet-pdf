package fi.vm.sade.eperusteet.pdf.service.ylops;

import fi.vm.sade.eperusteet.pdf.dto.common.LokalisoituTekstiDto;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiBase;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiRivi;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiTaulukko;
import fi.vm.sade.eperusteet.pdf.dto.dokumentti.DokumenttiYlops;
import fi.vm.sade.eperusteet.pdf.dto.enums.OppiaineValinnainenTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.LaajaalainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineLaajaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OppiaineenVuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.PerusopetuksenPerusteenSisaltoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.TekstiOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.VuosiluokkaKokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.KeskeinenSisaltoalueDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.LaajaalainenosaaminenDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetuksenKeskeinensisaltoalueDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetuksenTavoiteDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OppiaineenVuosiluokkaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OppiaineenVuosiluokkakokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsOppiaineExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsVuosiluokkakokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpsVuosiluokkakokonaisuusExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.VuosiluokkakokonaisuusDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiosaDto;
import fi.vm.sade.eperusteet.pdf.service.LocalizedMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeader;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addHeaderNoNumber;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addLokalisoituteksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTeksti;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.addTekstiosa;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.cleanHtml;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.getTextString;
import static fi.vm.sade.eperusteet.pdf.utils.DokumenttiUtils.hasLokalisoituteksti;

@Service
public class PerusopetusServiceImpl implements PerusopetusService {

    @Autowired
    private LocalizedMessagesService messages;

    @Override
    public void addVuosiluokkakokonaisuudet(DokumenttiYlops docBase) {
        Set<OpsVuosiluokkakokonaisuusExportDto> opsVlkset = docBase.getOps().getVuosiluokkakokonaisuudet();

        // Haetaan vuosiluokkkakokonaisuudet
        ArrayList<VuosiluokkakokonaisuusDto> vlkset = new ArrayList<>();
        for (OpsVuosiluokkakokonaisuusDto opsVlk : opsVlkset) {
            vlkset.add(opsVlk.getVuosiluokkakokonaisuus());
        }

        // Järjestetään aakkosjärjestykseen
        vlkset = vlkset.stream()
                .sorted(Comparator.comparing(vlk2 -> vlk2.getNimi().getTekstit().get(docBase.getKieli())))
                .collect(Collectors.toCollection(ArrayList::new));

        vlkset.forEach(vlk -> {
            String teksti = getTextString(docBase, vlk.getNimi());
            addHeader(docBase, !teksti.isEmpty() ? teksti : "Vuosiluokkakokonaisuuden otsikko puuttuu");

            PerusopetuksenPerusteenSisaltoDto poPerusteenSisaltoDto = docBase.getPeruste().getPerusopetuksenPerusteenSisalto();
            Map<UUID, LaajaalainenOsaaminenDto> laajaAlaisetOsaamisetMap = poPerusteenSisaltoDto.getLaajaalaisetosaamiset().stream().collect(Collectors.toMap(LaajaalainenOsaaminenDto::getTunniste, v -> v));
            Map<UUID, VuosiluokkaKokonaisuusDto> perusteenVlkMap = poPerusteenSisaltoDto.getVuosiluokkakokonaisuudet().stream().collect(Collectors.toMap(VuosiluokkaKokonaisuusDto::getTunniste, v -> v));
            Map<UUID, VuosiluokkakokonaisuusDto> pohjanVlkMap = docBase.getOps().getVuosiluokkakokonaisuudet().stream().map(OpsVuosiluokkakokonaisuusExportDto::getPohjanVuosiluokkakokonaisuus)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toMap(v -> UUID.fromString(v.getTunniste().toString()), v -> v));

            if (vlk.getTunniste().getId() != null) {
                Optional<VuosiluokkaKokonaisuusDto> optPerusteVlkDto = Optional.ofNullable(perusteenVlkMap.get(UUID.fromString(vlk.getTunniste().getId())));
                Optional<VuosiluokkakokonaisuusDto> optPohjanVlkDto = Optional.ofNullable(pohjanVlkMap.get(UUID.fromString(vlk.getTunniste().getId())));

                if (optPerusteVlkDto.isPresent()) {

                    VuosiluokkaKokonaisuusDto perusteVlk = optPerusteVlkDto.get();

                    // Vuosiluokkan sisältö
                    docBase.getGenerator().increaseDepth();

                    if (!CollectionUtils.isEmpty(perusteVlk.getVapaatTekstit())) {
                        perusteVlk.getVapaatTekstit().forEach(vapaaTeksti -> {
                            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
                            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "cite");

                            if (!CollectionUtils.isEmpty(vlk.getVapaatTekstit())) {
                                vlk.getVapaatTekstit().forEach(vt -> {
                                    if (vt.getPerusteenVapaaTekstiId().equals(vapaaTeksti.getId())) {
                                        addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");
                                        addTeksti(docBase, getTextString(docBase, vt.getPaikallinenTarkennus()), "div");
                                    }
                                });
                            }
                        });
                    }

                    // Vuosiluokkakokonaisuuden kohdat

                    addVlkYleisetOsiot(docBase, perusteVlk.getTehtava(), vlk.getTehtava(), optPohjanVlkDto.map(VuosiluokkakokonaisuusDto::getTehtava).orElse(null));
                    addVlkYleisetOsiot(docBase, perusteVlk.getSiirtymaEdellisesta(), vlk.getSiirtymaEdellisesta(), optPohjanVlkDto.map(VuosiluokkakokonaisuusDto::getSiirtymaEdellisesta).orElse(null));
                    addVlkYleisetOsiot(docBase, perusteVlk.getSiirtymaSeuraavaan(), vlk.getSiirtymaSeuraavaan(), optPohjanVlkDto.map(VuosiluokkakokonaisuusDto::getSiirtymaSeuraavaan).orElse(null));
                    addVlkYleisetOsiot(docBase, perusteVlk.getLaajaalainenOsaaminen(), vlk.getLaajaalainenosaaminen(), optPohjanVlkDto.map(VuosiluokkakokonaisuusDto::getLaajaalainenosaaminen).orElse(null));
                    addVlkLaajaalaisetOsaamisenAlueet(docBase, perusteVlk, vlk, optPohjanVlkDto, laajaAlaisetOsaamisetMap);

                    addOppiaineet(docBase, vlk);

                    docBase.getGenerator().decreaseDepth();

                    docBase.getGenerator().increaseNumber();
                }
            }
        });
    }

    private void addVlkYleisetOsiot(DokumenttiBase docBase,
                                    TekstiOsaDto perusteTekstiOsaDto,
                                    TekstiosaDto tekstiosa,
                                    TekstiosaDto pohjanTekstiOsa) {
        // Otsikko
        if (perusteTekstiOsaDto != null && perusteTekstiOsaDto.getOtsikko() != null) {
            addHeader(docBase,
                    getTextString(docBase, perusteTekstiOsaDto.getOtsikko()));

            // Perusteen teksi
            if (perusteTekstiOsaDto.getTeksti() != null) {
                addLokalisoituteksti(docBase, perusteTekstiOsaDto.getTeksti(), "cite");
            }

            if (pohjanTekstiOsa != null && pohjanTekstiOsa.getTeksti() != null) {
                addLokalisoituteksti(docBase, pohjanTekstiOsa.getTeksti(), "div");
            }

            // Opsin teksti
            if (tekstiosa != null && tekstiosa.getTeksti() != null) {
                addLokalisoituteksti(docBase, tekstiosa.getTeksti(), "div");
            }

            docBase.getGenerator().increaseNumber();
        }
    }

    private void addVlkLaajaalaisetOsaamisenAlueet(DokumenttiBase docBase,
                                                   VuosiluokkaKokonaisuusDto perusteVlk,
                                                   VuosiluokkakokonaisuusDto vlk,
                                                   Optional<VuosiluokkakokonaisuusDto> pohjanVlk,
                                                   Map<UUID, LaajaalainenOsaaminenDto> laajaAlaisetOsaamisetMap) {
        if (perusteVlk.getLaajaalaisetOsaamiset() != null) {

            addHeader(docBase, messages.translate("laaja-alaisen-osaamisen-alueet", docBase.getKieli()));

            List<VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto> perusteVlkLaajaalaisetOsaamiset = perusteVlk.getLaajaalaisetOsaamiset().stream()
                    .filter((lao -> lao.getLaajaalainenOsaaminen() != null))
                    .sorted(Comparator.comparing(lao -> laajaAlaisetOsaamisetMap.get(UUID.fromString(lao.getLaajaalainenOsaaminen().toString())).getNimi().get(docBase.getKieli())))
                    .collect(Collectors.toCollection(ArrayList::new));

            for (VuosiluokkaKokonaisuudenLaajaalainenOsaaminenDto perusteVlkLaajaalainenosaaminen : perusteVlkLaajaalaisetOsaamiset) {
                LaajaalainenOsaaminenDto perusteLaajaalainenosaaminenDto = laajaAlaisetOsaamisetMap.get(UUID.fromString(perusteVlkLaajaalainenosaaminen.getLaajaalainenOsaaminen().toString()));

                if (perusteLaajaalainenosaaminenDto != null) {
                    docBase.getGenerator().increaseDepth();
                    docBase.getGenerator().increaseDepth();
                    docBase.getGenerator().increaseDepth();
                    docBase.getGenerator().increaseDepth();

                    // otsikko
                    addHeader(docBase, getTextString(docBase, perusteLaajaalainenosaaminenDto.getNimi()));

                    Optional<LaajaalainenosaaminenDto> opsLaajaalainenosaaminen = vlk.getLaajaalaisetosaamiset().stream()
                            .filter((l -> perusteLaajaalainenosaaminenDto.getTunniste().equals(UUID.fromString(l.getLaajaalainenosaaminen().toString()))))
                            .findFirst();

                    // Perusteen osa
                    opsLaajaalainenosaaminen.ifPresent(laajaalainenosaaminen -> {
                        if (laajaalainenosaaminen.isNaytaPerusteenPaatasonLao()) {
                            addLokalisoituteksti(docBase, perusteLaajaalainenosaaminenDto.getKuvaus(), "cite");
                        }

                        if (laajaalainenosaaminen.isNaytaPerusteenVlkTarkennettuLao()) {
                            if(laajaalainenosaaminen.isNaytaPerusteenPaatasonLao()) {
                                addHeader(docBase, messages.translate("laaja-alaisen-osaamisen-alueen-vuosiluokkakokonaisuuden-kuvaus", docBase.getKieli()));
                            }
                            addLokalisoituteksti(docBase, perusteVlkLaajaalainenosaaminen.getKuvaus(), "cite");
                        }
                    });
                    
                    if (perusteVlkLaajaalainenosaaminen.getLaajaalainenOsaaminen() != null) {
                        Optional<LaajaalainenosaaminenDto> pohjanLao = pohjanVlk.flatMap(pVlk -> pVlk.getLaajaalaisetosaamiset().stream()
                                .filter(l -> l.getLaajaalainenosaaminen().equals(perusteVlkLaajaalainenosaaminen.getLaajaalainenOsaaminen()))
                                .findFirst());
                        Optional<LaajaalainenosaaminenDto> opsinLao = vlk.getLaajaalaisetosaamiset().stream()
                                .filter(l -> l.getLaajaalainenosaaminen().equals(perusteVlkLaajaalainenosaaminen.getLaajaalainenOsaaminen()))
                                .findFirst();

                        if (pohjanLao.isPresent() && (opsinLao.isEmpty()
                                || !getTextString(docBase.getKieli(), pohjanLao.get().getKuvaus()).equals(getTextString(docBase.getKieli(), opsinLao.get().getKuvaus())))) {
                            addLokalisoituteksti(docBase, pohjanLao.get().getKuvaus(), "div");
                        }

                        opsinLao.ifPresent(laajaalainenosaaminenDto -> addLokalisoituteksti(docBase, laajaalainenosaaminenDto.getKuvaus(), "div"));
                    }

                    docBase.getGenerator().decreaseDepth();
                    docBase.getGenerator().decreaseDepth();
                    docBase.getGenerator().decreaseDepth();
                    docBase.getGenerator().decreaseDepth();
                }
            }

            docBase.getGenerator().increaseNumber();
        }
    }

    private void addOppiaineet(DokumenttiYlops docBase, VuosiluokkakokonaisuusDto vlk) {
        if (docBase.getOps() != null && docBase.getOps().getOppiaineet() != null) {
            addHeader(docBase, messages.translate("oppiaineet", docBase.getKieli()));

            Set<OpsOppiaineExportDto> oppiaineet = docBase.getOps().getOppiaineet();

            List<OpsOppiaineExportDto> oppiaineetAsc = oppiaineet.stream()
                    .filter(oa -> oa.getOppiaine() != null
                            && oa.getOppiaine().getNimi() != null
                            && oa.getOppiaine().getNimi().getTekstit() != null
                            && oa.getOppiaine().getNimi().getTekstit().get(docBase.getKieli()) != null)
                    .sorted(Comparator.comparing(o -> o.getOppiaine().getNimi().get(docBase.getKieli())))
                    .sorted(Comparator.comparing(o -> o.getJnro() != null ? o.getJnro() : Integer.MAX_VALUE))
                    .collect(Collectors.toCollection(ArrayList::new));

            docBase.getGenerator().increaseDepth();

            // Oppiaineet akkosjärjestyksessä
            for (OpsOppiaineExportDto opsOppiaine : oppiaineetAsc) {
                OppiaineExportDto oppiaine = opsOppiaine.getOppiaine();

                Set<OppiaineenVuosiluokkakokonaisuusDto> oaVlkset = oppiaine.getVuosiluokkakokonaisuudet();

                UUID tunniste = oppiaine.getTunniste();

                OppiaineLaajaDto perusteOppiaineDto = null;
                OppiaineenVuosiluokkaKokonaisuusDto perusteOaVlkDto = null;
                OppiaineenVuosiluokkakokonaisuusDto oaVlk = null;
                OppiaineenVuosiluokkakokonaisuusDto oaPohjanVlk = new OppiaineenVuosiluokkakokonaisuusDto();

                Optional<OppiaineenVuosiluokkakokonaisuusDto> optOaVlk = oaVlkset.stream()
                        .filter(o -> o.getVuosiluokkakokonaisuus().getId().equals(vlk.getTunniste().getId()))
                        .findFirst();

                if (oppiaine.getPohjanOppiaine() != null) {
                    oaPohjanVlk = oppiaine.getPohjanOppiaine()
                            .getVuosiluokkakokonaisuus(vlk.getTunniste().getId())
                            .orElse(new OppiaineenVuosiluokkakokonaisuusDto());
                }

                if (optOaVlk.isPresent()) {
                    oaVlk = optOaVlk.get();
                    Optional<OppiaineLaajaDto> optPerusteOppiaineDto = docBase.getPeruste().getPerusopetuksenPerusteenSisalto().getOppiaine(tunniste);
                    if (optPerusteOppiaineDto.isPresent()) {
                        perusteOppiaineDto = optPerusteOppiaineDto.get();
                        Optional<VuosiluokkaKokonaisuusDto> perusteenVlk = docBase.getPerusteVlk(UUID.fromString(oaVlk.getVuosiluokkakokonaisuus().getId()));
                        if (perusteenVlk.isPresent()) {
                            Optional<OppiaineenVuosiluokkaKokonaisuusDto> optPerusteOaVlkDto =
                                    perusteOppiaineDto.getVuosiluokkakokonaisuus(perusteenVlk.get().getTunniste());
                            if (optPerusteOaVlkDto.isPresent()) {
                                perusteOaVlkDto = optPerusteOaVlkDto.get();
                            }
                        }
                    }
                }

                // Oppiaine nimi
                if (oppiaine.isKoosteinen() || optOaVlk.isPresent()) {
                    addHeader(docBase, getTextString(docBase, oppiaine.getNimi()), false);

                    docBase.getGenerator().increaseDepth();
                    docBase.getGenerator().increaseDepth();

                    // Tehtävä
                    addOppiaineTehtava(docBase, oppiaine, perusteOppiaineDto);

                    if (perusteOppiaineDto != null && !CollectionUtils.isEmpty(perusteOppiaineDto.getVapaatTekstit())) {
                        perusteOppiaineDto.getVapaatTekstit().forEach(vapaaTeksti -> {
                            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getNimi()), "h5");
                            addTeksti(docBase, getTextString(docBase, vapaaTeksti.getTeksti()), "cite");

                            if (!CollectionUtils.isEmpty(oppiaine.getVapaatTekstit())) {
                                oppiaine.getVapaatTekstit().forEach(vt -> {
                                    if (vt.getPerusteenVapaaTekstiId().equals(vapaaTeksti.getId())) {
                                        addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");
                                        addTeksti(docBase, getTextString(docBase, vt.getPaikallinenTarkennus()), "div");
                                    }
                                });
                            }
                        });
                    }

                    // Oppiaineen vuosiluokkakokonaiuuden kohtaiset
                    addOppiaineVuosiluokkakokonaisuus(docBase, perusteOaVlkDto, oaVlk, oaPohjanVlk, !OppiaineValinnainenTyyppi.EI_MAARITETTY.equals(oppiaine.getValinnainenTyyppi()));

                    docBase.getGenerator().decreaseDepth();

                    // Oppimäärät
                    Set<OppiaineExportDto> oppimaarat = oppiaine.getOppimaarat();
                    if (oppimaarat != null) {

                        Set<OppiaineDto> perusteOppimaarat = null;
                        if (perusteOppiaineDto != null) {
                            perusteOppimaarat = perusteOppiaineDto.getOppimaarat();
                        }

                        addOppimaarat(docBase, perusteOppimaarat, oppiaine, oppimaarat, vlk);
                    }

                    docBase.getGenerator().decreaseDepth();
                    docBase.getGenerator().increaseNumber();
                }
            }

            docBase.getGenerator().decreaseDepth();
            docBase.getGenerator().increaseNumber();
        }
    }

    private void addOppiaineTehtava(DokumenttiBase docBase, OppiaineExportDto oppiaine, OppiaineLaajaDto perusteOppiaineDto) {
        if (perusteOppiaineDto != null) {
            TekstiOsaDto tehtava = perusteOppiaineDto.getTehtava().orElse(null);
            if (tehtava != null) {
                addHeaderNoNumber(docBase, getTextString(docBase, tehtava.getOtsikko()));
                addLokalisoituteksti(docBase, tehtava.getTeksti(), "cite");
            }
        }

        addTekstiosa(docBase, Optional.ofNullable(oppiaine.getPohjanOppiaine()).map(OppiaineExportDto::getTehtava).map(TekstiosaDto::getTeksti).orElse(null), oppiaine.getTehtava(), "div");
    }

    private void addOppiaineVuosiluokkakokonaisuus(DokumenttiBase docBase,
                                                   OppiaineenVuosiluokkaKokonaisuusDto perusteOaVlkDto,
                                                   OppiaineenVuosiluokkakokonaisuusDto oaVlkDto,
                                                   OppiaineenVuosiluokkakokonaisuusDto pohjanVlkDto,
                                                   boolean isValinnainen) {

        if (oaVlkDto == null) {
            return;
        }

        if (perusteOaVlkDto != null) {
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTehtava(), pohjanVlkDto.getTehtava(), perusteOaVlkDto.getTehtava().orElse(null));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getYleistavoitteet(), pohjanVlkDto.getYleistavoitteet(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTyotavat(), pohjanVlkDto.getTyotavat(), perusteOaVlkDto.getTyotavat().orElse(null));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getOhjaus(), pohjanVlkDto.getOhjaus(), perusteOaVlkDto.getOhjaus().orElse(null));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getArviointi(), pohjanVlkDto.getArviointi(), perusteOaVlkDto.getArviointi().orElse(null));

            if (!CollectionUtils.isEmpty(perusteOaVlkDto.getVapaatTekstit())) {
                perusteOaVlkDto.getVapaatTekstit().forEach(vt -> {
                    addTeksti(docBase, getTextString(docBase, vt.getNimi()), "h6");
                    addTeksti(docBase, getTextString(docBase, vt.getTeksti()), "div");
                });
            }

            addOppiaineYleisetOsiot(docBase, oaVlkDto.getSisaltoalueinfo(), null, perusteOaVlkDto.getSisaltoalueinfo().orElse(null));
            addTavoitteetJaSisaltoalueet(docBase, perusteOaVlkDto, oaVlkDto, pohjanVlkDto);
        } else if (isValinnainen) {
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTehtava(), pohjanVlkDto.getTehtava(), null, messages.translate("valinnaisen-tehtava", docBase.getKieli()));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTyotavat(), pohjanVlkDto.getTyotavat(), null, messages.translate("oppiaine-tyotavat", docBase.getKieli()));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getOhjaus(), pohjanVlkDto.getOhjaus(), null, messages.translate("oppiaine-ohjaus", docBase.getKieli()));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getArviointi(), pohjanVlkDto.getArviointi(), null, messages.translate("docgen.arviointi.title", docBase.getKieli()));
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTavoitteistaJohdetutOppimisenTavoitteet(), pohjanVlkDto.getTavoitteistaJohdetutOppimisenTavoitteet(), null, messages.translate("docgen.tavoitteista-johdetut-oppimisen-tavoitteet.title", docBase.getKieli()));
            addTavoitteetJaSisaltoalueet(docBase, null, oaVlkDto, pohjanVlkDto);
        } else {
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTehtava(), pohjanVlkDto.getTehtava(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getYleistavoitteet(), pohjanVlkDto.getYleistavoitteet(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTyotavat(), pohjanVlkDto.getTyotavat(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getOhjaus(), pohjanVlkDto.getOhjaus(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getArviointi(), pohjanVlkDto.getArviointi(), null);
            addOppiaineYleisetOsiot(docBase, oaVlkDto.getTavoitteistaJohdetutOppimisenTavoitteet(), pohjanVlkDto.getTavoitteistaJohdetutOppimisenTavoitteet(), null);
            addTavoitteetJaSisaltoalueet(docBase, null, oaVlkDto, pohjanVlkDto);
        }
    }

    private void addTavoitteetJaSisaltoalueet(DokumenttiBase docBase,
                                              OppiaineenVuosiluokkaKokonaisuusDto perusteOaVlkDto,
                                              OppiaineenVuosiluokkakokonaisuusDto oaVlkDto,
                                              OppiaineenVuosiluokkakokonaisuusDto pohjanVlkDto) {

        // Tavoitteet vuosiluokittain
        if (oaVlkDto.getVuosiluokat() != null) {
            ArrayList<OppiaineenVuosiluokkaDto> vuosiluokat = oaVlkDto.getVuosiluokat().stream()
                    .sorted(Comparator.comparing(el -> el.getVuosiluokka().toString()))
                    .collect(Collectors.toCollection(ArrayList::new));
            // Vuosiluokka otsikko
            vuosiluokat.stream()
                    .filter(oaVuosiluokka -> oaVuosiluokka.getVuosiluokka() != null)
                    .forEach(oaVuosiluokka -> {
                        // Vuosiluokka otsikko
                        addHeaderNoNumber(docBase, messages.translate(oaVuosiluokka.getVuosiluokka() + "-luokka", docBase.getKieli()));

                        OppiaineenVuosiluokkaDto pohjanVuosiluokka = Optional.ofNullable(pohjanVlkDto.getVuosiluokat()).orElse(Collections.emptySet())
                                .stream().filter(pVuosiluokka -> pVuosiluokka.getVuosiluokka().equals(oaVuosiluokka.getVuosiluokka())).findFirst().orElse(null);

                        addVuosiluokanTavoitteetJaKeskeisetsisallot(docBase, oaVuosiluokka, pohjanVuosiluokka, perusteOaVlkDto);
                    });
        }
    }

    private void addVuosiluokanTavoitteetJaKeskeisetsisallot(DokumenttiBase docBase,
                                                             OppiaineenVuosiluokkaDto oaVuosiluokka,
                                                             OppiaineenVuosiluokkaDto pohjaOppiaineenVuosiluokka,
                                                             OppiaineenVuosiluokkaKokonaisuusDto perusteOaVlkDto) {
        if (oaVuosiluokka.getTavoitteet() != null && !oaVuosiluokka.getTavoitteet().isEmpty()) {

            addTeksti(docBase, messages.translate("docgen.vuosiluokan-tavoitteet-ja-keskeiset-sisallot", docBase.getKieli()), "tavoitteet-otsikko");

            for (OpetuksenTavoiteDto opetuksentavoite : oaVuosiluokka.getTavoitteet()) {

                // Opsin tavoitetta vastaava perusteen tavoite
                fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OpetuksenTavoiteDto perusteOpetuksentavoiteDto = null;
                if (perusteOaVlkDto != null) {
                    List<fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OpetuksenTavoiteDto> perusteTavoitteet = perusteOaVlkDto.getTavoitteet();
                    Optional<fi.vm.sade.eperusteet.pdf.dto.eperusteet.yl.OpetuksenTavoiteDto> optPerusteOpetuksentavoiteDto = perusteTavoitteet.stream()
                            .filter((o) -> o.getTunniste().equals(opetuksentavoite.getTunniste()))
                            .findFirst();
                    if (optPerusteOpetuksentavoiteDto.isPresent()) {
                        perusteOpetuksentavoiteDto = optPerusteOpetuksentavoiteDto.get();
                    }
                }

                // Tavoitteen otsikko
                if (perusteOpetuksentavoiteDto != null) {
                    addLokalisoituteksti(docBase, perusteOpetuksentavoiteDto.getTavoite().orElse(null), "h5");

                    if (!ObjectUtils.isEmpty(getTextString(docBase, opetuksentavoite.getTavoite()))) {
                        addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");
                        addLokalisoituteksti(docBase, opetuksentavoite.getTavoite(), "div");
                    }

                    if (!ObjectUtils.isEmpty(perusteOpetuksentavoiteDto.getOppiaineenTavoitteenOpetuksenTavoitteet())) {
                        addTeksti(docBase, messages.translate("opetuksen-tavoitteet", docBase.getKieli()), "h6");
                        perusteOpetuksentavoiteDto.getOppiaineenTavoitteenOpetuksenTavoitteet().forEach(ot -> addTeksti(docBase, getTextString(docBase, ot.getTavoite().get()), "p"));
                    }

                    if (!ObjectUtils.isEmpty(perusteOpetuksentavoiteDto.getTavoitteistaJohdetutOppimisenTavoitteet())
                            && perusteOpetuksentavoiteDto.getTavoitteistaJohdetutOppimisenTavoitteet().isPresent()) {
                        addTeksti(docBase, messages.translate("tavoitteista-johdetut-oppimisen-tavoitteet", docBase.getKieli()), "h6");
                        addTeksti(docBase, getTextString(docBase, perusteOpetuksentavoiteDto.getTavoitteistaJohdetutOppimisenTavoitteet().get()), "div");
                    }

                    if (!ObjectUtils.isEmpty(perusteOpetuksentavoiteDto.getArvioinninKuvaus())
                            && perusteOpetuksentavoiteDto.getArvioinninKuvaus().isPresent()) {
                        addTeksti(docBase, messages.translate("arvioinnin-kohde", docBase.getKieli()), "h6");
                        addLokalisoituteksti(docBase, perusteOpetuksentavoiteDto.getArvioinninKuvaus().get(), "div");
                    }

                    // Tavoitteen arviointi
                    DokumenttiTaulukko taulukko = new DokumenttiTaulukko();
                    taulukko.addOtsikko(messages.translate("arviointi-vuosiluokan-paatteeksi", docBase.getKieli()));

                    if (perusteOpetuksentavoiteDto.getArvioinninkohteet().size() == 1
                            && perusteOpetuksentavoiteDto.getArvioinninkohteet().stream().findFirst().get().getHyvanOsaamisenKuvaus() != null) {

                        taulukko.addOtsikkoSarake(messages.translate("arvioinnin-kohde", docBase.getKieli()));
                        taulukko.addOtsikkoSarake(messages.translate("arvion-hyva-osaaminen", docBase.getKieli()));

                        perusteOpetuksentavoiteDto.getArvioinninkohteet().forEach(perusteenTavoitteenArviointi -> {
                            DokumenttiRivi rivi = new DokumenttiRivi();
                            String kohde = "";
                            if (perusteenTavoitteenArviointi.getArvioinninKohde() != null
                                    && perusteenTavoitteenArviointi.getArvioinninKohde().isPresent()
                                    && perusteenTavoitteenArviointi.getArvioinninKohde().get().get(docBase.getKieli()) != null) {
                                kohde = cleanHtml(perusteenTavoitteenArviointi.getArvioinninKohde().get().get(docBase.getKieli()));
                            }
                            rivi.addSarake(kohde);
                            String kuvaus = "";
                            if (perusteenTavoitteenArviointi.getHyvanOsaamisenKuvaus() != null
                                    && perusteenTavoitteenArviointi.getHyvanOsaamisenKuvaus().isPresent()
                                    && perusteenTavoitteenArviointi.getHyvanOsaamisenKuvaus().get().get(docBase.getKieli()) != null) {
                                kuvaus = cleanHtml(perusteenTavoitteenArviointi.getHyvanOsaamisenKuvaus().get().get(docBase.getKieli()));
                            }
                            rivi.addSarake(kuvaus);
                            taulukko.addRivi(rivi);
                        });
                    } else {
                        taulukko.addOtsikkoSarake(messages.translate("osaamisen-kuvaus", docBase.getKieli()));
                        taulukko.addOtsikkoSarake(messages.translate("arvion-kuvaus", docBase.getKieli()));

                        perusteOpetuksentavoiteDto.getArvioinninkohteet()
                                .stream().sorted(Comparator.comparing(arv -> arv.getArvosana() != null && arv.getArvosana().isPresent() ? arv.getArvosana().get() : 0))
                                .forEach(perusteenTavoitteenArviointi -> {
                                    DokumenttiRivi rivi = new DokumenttiRivi();
                                    String kohde = "";
                                    if (perusteenTavoitteenArviointi.getArvosana().isPresent()) {
                                        kohde = messages.translate("osaamisen-kuvaus-arvosanalle-" + perusteenTavoitteenArviointi.getArvosana().get(), docBase.getKieli());
                                    }
                                    rivi.addSarake(kohde);

                                    String kuvaus = "";
                                    if (perusteenTavoitteenArviointi.getOsaamisenKuvaus() != null
                                            && perusteenTavoitteenArviointi.getOsaamisenKuvaus().isPresent()
                                            && perusteenTavoitteenArviointi.getOsaamisenKuvaus().get().get(docBase.getKieli()) != null) {
                                        kuvaus = cleanHtml(perusteenTavoitteenArviointi.getOsaamisenKuvaus().get().get(docBase.getKieli()));
                                    }
                                    rivi.addSarake(kuvaus);
                                    taulukko.addRivi(rivi);
                                });
                    }

                    taulukko.addToDokumentti(docBase);
                } else {
                    addLokalisoituteksti(docBase, opetuksentavoite.getTavoite(), "h5");
                }

                if (perusteOaVlkDto != null && perusteOaVlkDto.getSisaltoalueet() != null) {
                    perusteOaVlkDto.getSisaltoalueet()
                            .stream()
                            .filter(perusteenKeskeinenSisaltoalue -> {
                                Optional<OpetuksenKeskeinensisaltoalueDto> opetuksenKeskeinenSisaltoalue = opetuksentavoite.getSisaltoalueet()
                                        .stream()
                                        .filter(sisaltoalue -> sisaltoalue.getSisaltoalueet().getTunniste().equals(perusteenKeskeinenSisaltoalue.getTunniste()))
                                        .findFirst();
                                Optional<KeskeinenSisaltoalueDto> keskeinenSisaltoalue = oaVuosiluokka.getSisaltoalueet()
                                        .stream()
                                        .filter(sisaltoalue -> sisaltoalue.getTunniste().equals(perusteenKeskeinenSisaltoalue.getTunniste()))
                                        .sorted(Comparator.comparing(KeskeinenSisaltoalueDto::getId))
                                        .findFirst();
                                return opetuksenKeskeinenSisaltoalue.isPresent()
                                        && keskeinenSisaltoalue.isPresent()
                                        && (keskeinenSisaltoalue.get().getPiilotettu() == null || !keskeinenSisaltoalue.get().getPiilotettu());
                            })
                            .sorted(Comparator.comparing(perusteenKeskeinenSisaltoalue -> perusteenKeskeinenSisaltoalue.getNimi().get().get(docBase.getKieli())))
                            .forEach(perusteenKeskeinenSisaltoalue -> {

                                addLokalisoituteksti(docBase, perusteenKeskeinenSisaltoalue.getNimi().orElse(null), "h6");
                                addLokalisoituteksti(docBase, perusteenKeskeinenSisaltoalue.getKuvaus().orElse(null), "cite");

                                Optional<OpetuksenKeskeinensisaltoalueDto> opetuksenKeskeinenSisaltoalue = opetuksentavoite.getSisaltoalueet()
                                        .stream()
                                        .filter(sisaltoalue -> sisaltoalue.getSisaltoalueet().getTunniste().equals(perusteenKeskeinenSisaltoalue.getTunniste()))
                                        .sorted(Comparator.comparing(OpetuksenKeskeinensisaltoalueDto::getId))
                                        .findFirst();

                                if (opetuksenKeskeinenSisaltoalue.isPresent()) {
                                    OpetuksenKeskeinensisaltoalueDto sisaltoalue = opetuksenKeskeinenSisaltoalue.get();
                                    if (hasLokalisoituteksti(docBase, sisaltoalue.getOmaKuvaus()) || hasLokalisoituteksti(docBase, sisaltoalue.getSisaltoalueet().getKuvaus())) {
                                        addTeksti(docBase, messages.translate("paikallinen-tarkennus", docBase.getKieli()), "h6");

                                        if (hasLokalisoituteksti(docBase, sisaltoalue.getOmaKuvaus())) {
                                            addLokalisoituteksti(docBase, sisaltoalue.getOmaKuvaus(), "div");
                                        } else if (hasLokalisoituteksti(docBase, sisaltoalue.getSisaltoalueet().getKuvaus())) {
                                            addLokalisoituteksti(docBase, sisaltoalue.getSisaltoalueet().getKuvaus(), "div");
                                        }
                                    }

                                }
                            });
                } else if(!CollectionUtils.isEmpty(opetuksentavoite.getSisaltoalueet())) {

                    if (pohjaOppiaineenVuosiluokka != null) {
                        pohjaOppiaineenVuosiluokka.getTavoitteet().stream().filter(tavoite -> tavoite.getTunniste().equals(opetuksentavoite.getTunniste())).findFirst().ifPresent(pohjaOppiaineenVuosiluokanTavoite -> {
                            pohjaOppiaineenVuosiluokanTavoite.getSisaltoalueet().stream()
                                    .filter(sisaltoalue -> sisaltoalue.getSisaltoalueet() != null)
                                    .forEach(sisaltoalue -> {
                                        addLokalisoituteksti(docBase, sisaltoalue.getSisaltoalueet().getKuvaus(), "cite");
                                    });
                        });
                    }

                    opetuksentavoite.getSisaltoalueet().stream()
                            .filter(sisaltoalue -> sisaltoalue.getSisaltoalueet() != null)
                            .forEach(sisaltoalue -> {
                                addLokalisoituteksti(docBase, sisaltoalue.getSisaltoalueet().getKuvaus(), "div");
                            });
                }
            }
        }
    }

    private void addOppiaineYleisetOsiot(DokumenttiBase docBase, TekstiosaDto tekstiosa, TekstiosaDto pohjanTekstiosa, TekstiOsaDto perusteTekstiOsaDto) {
        addOppiaineYleisetOsiot(docBase, tekstiosa, pohjanTekstiosa, perusteTekstiOsaDto, null);
    }

    private void addOppiaineYleisetOsiot(DokumenttiBase docBase, TekstiosaDto tekstiosa, TekstiosaDto pohjanTekstiosa, TekstiOsaDto perusteTekstiOsaDto, String valinnaisenOtsikko) {
        if (tekstiosa != null) {
            LokalisoituTekstiDto otsikko = tekstiosa.getOtsikko();
            if (otsikko != null) {
                addHeaderNoNumber(docBase, getTextString(docBase, otsikko));
            } else if (perusteTekstiOsaDto != null) {
                addHeaderNoNumber(docBase, getTextString(docBase, perusteTekstiOsaDto.getOtsikko()));
                addLokalisoituteksti(docBase, perusteTekstiOsaDto.getTeksti(), "cite");
            }

            if (!ObjectUtils.isEmpty(valinnaisenOtsikko)) {
                addHeaderNoNumber(docBase, valinnaisenOtsikko);
            }

            if (pohjanTekstiosa != null && pohjanTekstiosa.getTeksti() != null) {
                addLokalisoituteksti(docBase, pohjanTekstiosa.getTeksti(), "div");
            }

            if (!ObjectUtils.isEmpty(tekstiosa.getTeksti())) {
                addLokalisoituteksti(docBase, tekstiosa.getTeksti(), "div");
            }
        }
    }

    private void addOppimaarat(DokumenttiYlops docBase, Set<OppiaineDto> perusteOppimaarat, OppiaineExportDto oppiaine,
                               Set<OppiaineExportDto> oppimaarat, VuosiluokkakokonaisuusDto vlk) {
        if (oppimaarat != null) {
            for (OppiaineExportDto oppimaara : oppimaarat) {
                OppiaineDto perusteOppiaineDto = null;
                if (perusteOppimaarat != null) {
                    Optional<OppiaineDto> optPerusteOppimaara = perusteOppimaarat.stream()
                            .filter(perusteOppiaine -> perusteOppiaine.getTunniste().equals(oppimaara.getTunniste()))
                            .findFirst();

                    if (optPerusteOppimaara.isPresent()) {
                        perusteOppiaineDto = optPerusteOppimaara.get();
                    }
                }

                // Jos on koosteinen oppimäärä ja oppimäärälle ei löydy perustetta
                // perusteen oppiaineesta, käytetään opsin perusteen oppiainetta
                if (oppiaine.isKoosteinen() && perusteOppiaineDto == null) {
                    Optional<OppiaineDto> optPerusteOppiaineDto = docBase.getPeruste().getPerusopetuksenPerusteenSisalto()
                            .getOppimaara(oppimaara.getTunniste());
                    if (optPerusteOppiaineDto.isPresent()) {
                        perusteOppiaineDto = optPerusteOppiaineDto.get();
                    }
                }

                addOppimaara(docBase, perusteOppiaineDto, oppimaara, vlk);
            }
        }
    }

    private void addOppimaara(DokumenttiYlops docBase, OppiaineDto perusteOppiaineDto,
                              OppiaineExportDto oppiaine, VuosiluokkakokonaisuusDto vlk) {
        Optional<OppiaineenVuosiluokkakokonaisuusDto> optOaVlk
                = oppiaine.getVuosiluokkakokonaisuus(vlk.getTunniste().getId());
        OppiaineenVuosiluokkakokonaisuusDto oaPohjanVlk = new OppiaineenVuosiluokkakokonaisuusDto();
        OpsVuosiluokkakokonaisuusDto opsVuosiluokkakokonaisuus = docBase.getOps().getVuosiluokkakokonaisuudet().stream()
                .filter(opsv -> opsv.getVuosiluokkakokonaisuus().getTunniste().getId().equals(vlk.getTunniste().getId())).findFirst().orElse(null);

        if (oppiaine.getPohjanOppiaine() != null) {
            oaPohjanVlk = oppiaine.getPohjanOppiaine()
                    .getVuosiluokkakokonaisuus(vlk.getTunniste().getId())
                    .orElse(new OppiaineenVuosiluokkakokonaisuusDto());
        }

        if (!optOaVlk.isPresent()) {
            return;
        }

        if (optOaVlk.get().getPiilotettu() != null && optOaVlk.get().getPiilotettu()) {
            return;
        }

        if (opsVuosiluokkakokonaisuus != null
                && opsVuosiluokkakokonaisuus.getLisatieto() != null
                && opsVuosiluokkakokonaisuus.getLisatieto().getPiilotetutOppiaineet().contains(oppiaine.getId())) {
            return;
        }

        // Oppimäärä otsikko
        addHeader(docBase, getTextString(docBase, oppiaine.getNimi()), false);

        docBase.getGenerator().increaseDepth();

        TekstiOsaDto perusteTehtavaDto = null;
        if (perusteOppiaineDto != null && perusteOppiaineDto.getTehtava() != null) {
            perusteTehtavaDto = perusteOppiaineDto.getTehtava();
        }

        // Tehtävä
        addOppiaineYleisetOsiot(
                docBase,
                oppiaine.getTehtava(),
                oppiaine.getPohjanOppiaine() != null ? oppiaine.getPohjanOppiaine().getTehtava() : null,
                perusteTehtavaDto);

        // Peruste
        OppiaineenVuosiluokkaKokonaisuusDto perusteOaVlkDto = null;

        if (perusteOppiaineDto != null) {
            Optional<OppiaineenVuosiluokkaKokonaisuusDto> optPerusteOaVlkDto = perusteOppiaineDto.getVuosiluokkakokonaisuus(vlk.getTunniste().getId());
            if (optPerusteOaVlkDto.isPresent()) {
                perusteOaVlkDto = optPerusteOaVlkDto.get();
            }
        }

        // Oppimäärän vuosiluokkakokonaiuuden kohtaiset
        addOppiaineVuosiluokkakokonaisuus(docBase, perusteOaVlkDto, optOaVlk.get(), oaPohjanVlk, !OppiaineValinnainenTyyppi.EI_MAARITETTY.equals(oppiaine.getValinnainenTyyppi()));

        docBase.getGenerator().decreaseDepth();
        docBase.getGenerator().increaseNumber();
    }
}
