package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Throwables;
import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.OphHttpEntity;
import fi.vm.sade.javautils.http.OphHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Slf4j
@Service
public class CommonExternalServiceImpl implements CommonExternalService{

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    private static final String AMOSAA_API = "/api/amosaa/";
    private static final String EPERUSTEET_API = "/api/perusteet/";

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    @Lazy
    private DokumenttiUtilService dokumenttiUtilService;

    @Autowired
    HttpEntity httpEntity;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private RestClientFactory restClientFactory;

    @PostConstruct
    protected void init() {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public InputStream getLiitetiedosto(Long id, UUID fileName, DokumenttiTyyppi tyyppi) {
        try {
            ResponseEntity<byte[]> response = dokumenttiUtilService.createRestTemplateWithImageConversionSupport().exchange(getLiitetiedostoUrl(tyyppi),
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    id,
                    fileName);
            return new ByteArrayInputStream(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            throw new ServiceException("Liitetiedostoa ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi kuvatyyppi, Kieli kieli, DokumenttiTyyppi dokumenttityyppi, Long ktId) {
        ResponseEntity<byte[]> response;
        try {
            response = dokumenttiUtilService.createRestTemplateWithImageConversionSupport().exchange(getDokumenttiKuvaUrl(dokumenttityyppi, ktId, opsId),
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    kuvatyyppi,
                    kieli);
        } catch (Exception e) {
            throw new ServiceException("Dokumenttikuvaa ei saatu haettua: " + e.getMessage());
        }
        if (response.getBody() == null) {
            throw new ServiceException("Dokumenttikuvaa ei löytynyt");
        } else {
            return response.getBody();
        }
    }

    @Override
    public TermiDto getTermi(Long id, String avain, DokumenttiTyyppi dokumenttityyppi) {
        try {
            ResponseEntity<TermiDto> response = restTemplate.exchange(getTermiUrl(dokumenttityyppi),
                    HttpMethod.GET,
                    httpEntity,
                    TermiDto.class,
                    id,
                    avain);
            return response.getBody();
        } catch (Exception e) {
            throw new ServiceException("Termiä ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public void postPdfData(byte[] pdfData, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        try {
            OphHttpClient client = restClientFactory.get(getDokumenttiApiBaseUrl(tyyppi), true);

            String url = getDokumenttiApiBaseUrl(tyyppi)
                    + "/api/dokumentit/pdf/data/" + dokumenttiId;

            OphHttpRequest request = OphHttpRequest.Builder
                    .post(url)
                    .addHeader("Content-Type", "application/json;charset=UTF-8")
                    .setEntity(new OphHttpEntity.Builder()
                            .content(Base64.getEncoder().encodeToString(pdfData))
                            .contentType(ContentType.TEXT_PLAIN)
                            .build())
                    .build();

            client.execute(request)
                    .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                    .with(error -> {
                        log.error("Virhe pdf:n lähetyksessä");
                        throw new RuntimeException("Virhe pdf:n lähetyksessä");
                    })
                    .expectedStatus(SC_OK, SC_CREATED)
                    .ignoreResponse();


//            HttpEntity<byte[]> entity = new HttpEntity<>(pdfData, this.httpEntity.getHeaders());
//            ResponseEntity response = dokumenttiUtilService.createRestTemplateWithPdfConversionSupport().exchange(getDokumenttiApiBaseUrl(tyyppi) + "/api/dokumentit/pdf/data/{dokumenttiId}",
//                    HttpMethod.POST,
//                    entity,
//                    String.class,
//                    dokumenttiId);
            log.info("dokumentti lähetetty palvelulle: {}, vastauksella:{}", getDokumenttiApiBaseUrl(tyyppi) + "/api/dokumentit/pdf/data/"+dokumenttiId);
        } catch (Exception e) {
            throw new ServiceException("PDF-dataa ei saatu lähetettyä: " + e.getMessage());
        }
    }

    @Override
    public void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        try {
            HttpEntity<DokumenttiTila> entity = new HttpEntity<>(tila, this.httpEntity.getHeaders());
            restTemplate.exchange(getDokumenttiApiBaseUrl(tyyppi) + "/api/dokumentit/pdf/tila/{dokumenttiId}",
                    HttpMethod.POST,
                    entity,
                    String.class,
                    dokumenttiId);
        } catch (Exception e) {
            log.error(Throwables.getStackTraceAsString(e));
            throw new ServiceException("PDF-generoinnin tilaa ei saatu päivitettyä: " + e.getMessage());
        }
    }

    private String getDokumenttiApiBaseUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE) || tyyppi.equals(DokumenttiTyyppi.KVLIITE)) {
            return eperusteetServiceUrl;
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl;
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl;
        } else {
            throw new BusinessRuleViolationException("DokumenttiApi-urlin valinta epäonnistui.");
        }
    }

    private String getTermiUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return eperusteetServiceUrl + EPERUSTEET_API + "{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/{id}/termisto/{avain}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/opetussuunnitelmat/{id}/termi/{avain}";
        } else {
            throw new BusinessRuleViolationException("Termi-urlin valinta epäonnistui.");
        }
    }

    private String getLiitetiedostoUrl(DokumenttiTyyppi tyyppi) {
        if (tyyppi.equals(DokumenttiTyyppi.PERUSTE)) {
            return eperusteetServiceUrl + EPERUSTEET_API + "{id}/liitteet/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API + "liitetiedostot/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/opetussuunnitelmat/{id}/kuvat/{fileName}";
        } else {
            throw new BusinessRuleViolationException("Liitetiedosto-urlin valinta epäonnistui.");
        }
    }

    private String getDokumenttiKuvaUrl(DokumenttiTyyppi tyyppi, Long ktId, Long opsId) {
        if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            // koska amosaan ja ylopsin urlien rakenne eroaa, ja amosaa vaatii opsId:n kahdesti, asetetaan tässä ensimmäinen kerta ja muut uriVariablet myöhemmin
            return amosaaServiceUrl + "/api/koulutustoimijat/" + ktId + "/opetussuunnitelmat/" + opsId + "/dokumentti/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/dokumentit/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else {
            throw new BusinessRuleViolationException("Dokumenttikuva-urlin valinta epäonnistui.");
        }
    }
}
