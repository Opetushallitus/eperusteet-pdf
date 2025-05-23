package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import fi.vm.sade.eperusteet.pdf.dto.PdfData;
import fi.vm.sade.eperusteet.pdf.dto.common.GeneratorData;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.OphHttpEntity;
import fi.vm.sade.javautils.http.OphHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static jakarta.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Service
@Profile("!test")
public class CommonExternalServiceImpl implements CommonExternalService{

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service.internal:'${fi.vm.sade.eperusteet.pdf.amosaa-service:''}}")
    private String amosaaServiceInternalUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service.internal:${fi.vm.sade.eperusteet.pdf.ylops-service:''}}")
    private String ylopsServiceInternalUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service.internal:${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}}")
    private String eperusteetServiceInternalUrl;

    private static final String EPERUSTEET_API = "/api/perusteet/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClientFactory restClientFactory;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Map<DokumenttiTyyppi, String> dokumenttiServiceUrls;
    private Map<DokumenttiTyyppi, String> dokumenttiServiceInternalUrls;

    @PostConstruct
    public void init() {
        dokumenttiServiceUrls = Map.of(
                DokumenttiTyyppi.KVLIITE, eperusteetServiceUrl,
                DokumenttiTyyppi.PERUSTE, eperusteetServiceUrl,
                DokumenttiTyyppi.AMOSAA, amosaaServiceUrl,
                DokumenttiTyyppi.YLOPS, ylopsServiceUrl
        );

        dokumenttiServiceInternalUrls = Map.of(
                DokumenttiTyyppi.KVLIITE, eperusteetServiceInternalUrl,
                DokumenttiTyyppi.PERUSTE, eperusteetServiceInternalUrl,
                DokumenttiTyyppi.AMOSAA, amosaaServiceInternalUrl,
                DokumenttiTyyppi.YLOPS, ylopsServiceInternalUrl
        );
    }

    @Override
    public InputStream getLiitetiedosto(Long id, UUID fileName, DokumenttiTyyppi tyyppi) {
        OphHttpClient client = restClientFactory.get(dokumenttiServiceUrls.get(tyyppi), true);
        String url = UriComponentsBuilder.fromUriString(getLiitetiedostoUrl(tyyppi)).build(id, fileName).toString();
        OphHttpRequest request = OphHttpRequest.Builder.get(url).build();

        List<byte[]> liite = new ArrayList<>();
        client.<byte[]>execute(request)
                .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                .with(error -> {
                    throw new RuntimeException("Virhe pdf:n lähetyksessä: " + error);
                })
                .expectedStatus(SC_OK, SC_CREATED)
                .consumeStreamWith(content -> {
                    try {
                        liite.add(content.readAllBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        return new ByteArrayInputStream(Objects.requireNonNull(liite.get(0)));
    }

    @Override
    public byte[] getDokumenttiKuva(GeneratorData generatorData, Kuvatyyppi kuvatyyppi) {
        OphHttpClient client = restClientFactory.get(dokumenttiServiceUrls.get(generatorData.getTyyppi()), true);
        String url = UriComponentsBuilder.fromUriString(getDokumenttiKuvaUrl(generatorData.getTyyppi()))
                .build(generatorData.uriParameters(kuvatyyppi))
                .toString();
        OphHttpRequest request = OphHttpRequest.Builder.get(url).build();

        List<byte[]> kuva = new ArrayList<>();
        client.<byte[]>execute(request)
                .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                .with(error -> {
                    throw new RuntimeException("Virhe pdf:n lähetyksessä: " + error);
                })
                .expectedStatus(SC_OK, SC_CREATED)
                .consumeStreamWith(content -> {
                    try {
                        kuva.add(content.readAllBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

        return kuva.get(0);
    }

    @Override
    public TermiDto getTermi(Long id, String avain, DokumenttiTyyppi tyyppi) {
        OphHttpClient client = restClientFactory.get(dokumenttiServiceUrls.get(tyyppi), true);
        String url = UriComponentsBuilder.fromUriString(getTermiUrl(tyyppi)).build(id, avain).toString();
        OphHttpRequest request = OphHttpRequest.Builder.get(url).build();

        return client.<TermiDto>execute(request)
                .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                .with(error -> {
                    throw new RuntimeException("Virhe pdf:n lähetyksessä: " + error);
                })
                .expectedStatus(SC_OK, SC_CREATED)
                .mapWith(text -> {
                    try {
                        return objectMapper.readValue(text, TermiDto.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null);
    }

    @Override
    public void postPdfData(byte[] pdfDataBytes, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        try {
            OphHttpClient client = restClientFactory.get(dokumenttiServiceUrls.get(tyyppi), true);
            String url = dokumenttiServiceInternalUrls.get(tyyppi) + "/api/dokumentit/pdf/data/" + dokumenttiId;
            PdfData pdfData = PdfData.of(Base64.getEncoder().encodeToString(pdfDataBytes));

            OphHttpRequest request = OphHttpRequest.Builder
                    .post(url)
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .setEntity(new OphHttpEntity.Builder()
                            .content(new ObjectMapper().writeValueAsString(pdfData))
                            .contentType(ContentType.APPLICATION_JSON)
                            .build())
                    .build();

            client.execute(request)
                    .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                    .with(error -> {
                        log.error("Virhe pdf:n lähetyksessä: " + error);
                        throw new RuntimeException("Virhe pdf:n lähetyksessä: " + error);
                    })
                    .expectedStatus(SC_OK, SC_CREATED)
                    .ignoreResponse();
            log.info("Dokumentti lähetetty palvelulle: {}", dokumenttiServiceUrls.get(tyyppi) + "/api/dokumentit/pdf/data/"+dokumenttiId);
        } catch (Exception e) {
            throw new ServiceException("PDF-dataa ei saatu lähetettyä: " + e.getMessage());
        }
    }

    @Override
    public void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        try {
            OphHttpClient client = restClientFactory.get(dokumenttiServiceUrls.get(tyyppi), true);
            String url = dokumenttiServiceInternalUrls.get(tyyppi) + "/api/dokumentit/pdf/tila/" + dokumenttiId;
            PdfData pdfData = new PdfData(null, tila.toString());

            OphHttpRequest request = OphHttpRequest.Builder
                    .post(url)
                    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .setEntity(new OphHttpEntity.Builder()
                            .content(new ObjectMapper().writeValueAsString(pdfData))
                            .contentType(ContentType.APPLICATION_JSON)
                            .build())
                    .build();

            client.execute(request)
                    .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                    .with(error -> {
                        log.error("Virhe tilan paivityksessa: " + error);
                        throw new RuntimeException("Virhe tilan paivityksessa: " + error);
                    })
                    .expectedStatus(SC_OK, SC_CREATED)
                    .ignoreResponse();

        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            throw new ServiceException("PDF-generoinnin tilaa ei saatu päivitettyä: " + e.getMessage());
        }
    }

    private String getTermiUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + EPERUSTEET_API + "{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.AMOSAA)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/koulutustoimijat/{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.YLOPS)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/opetussuunnitelmat/{id}/termi/{avain}";
        } else {
            throw new BusinessRuleViolationException("Termi-urlin valinta epäonnistui.");
        }
    }

    private String getLiitetiedostoUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + EPERUSTEET_API + "{id}/liitteet/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.AMOSAA)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.YLOPS)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else {
            throw new BusinessRuleViolationException("Liitetiedosto-urlin valinta epäonnistui.");
        }
    }

    private String getDokumenttiKuvaUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.AMOSAA)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/koulutustoimijat/{ktId}/opetussuunnitelmat/{opsId}/dokumentti/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else if (tyyppi.equals(DokumenttiTyyppi.YLOPS)) {
            return dokumenttiServiceInternalUrls.get(tyyppi) + "/api/dokumentit/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else {
            throw new BusinessRuleViolationException("Dokumenttikuva-urlin valinta epäonnistui.");
        }
    }
}
