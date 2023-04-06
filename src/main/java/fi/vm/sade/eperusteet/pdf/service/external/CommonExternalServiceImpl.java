package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTila;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.pdf.service.DokumenttiUtilService;
import lombok.extern.slf4j.Slf4j;
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
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

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
        //TODO: ei osaa vielä hakea amosaasta kuvia, koska rajapinta ei ole julkinen
        try {
            ResponseEntity<byte[]> response = dokumenttiUtilService.createRestTemplateWithImageConversionSupport().exchange(getDokumenttiKuvaUrl(dokumenttityyppi, ktId),
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    kuvatyyppi,
                    kieli);
            return response.getBody();
        } catch (Exception e) {
            throw new ServiceException("Dokumenttikuvaa ei saatu haettua: " + e.getMessage());
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
            HttpEntity<byte[]> entity = new HttpEntity<>(pdfData);
            dokumenttiUtilService.createRestTemplateWithPdfConversionSupport().exchange(getDokumenttiApiBaseUrl(tyyppi) + "/api/dokumentit/pdf/data/{dokumenttiId}",
                    HttpMethod.POST,
                    entity,
                    String.class,
                    dokumenttiId);
        } catch (Exception e) {
            throw new ServiceException("PDF-dataa ei saatu lähetettyä: " + e.getMessage());
        }
    }

    @Override
    public void updateDokumenttiTila(DokumenttiTila tila, Long dokumenttiId, DokumenttiTyyppi tyyppi) {
        try {
            HttpEntity<DokumenttiTila> entity = new HttpEntity<>(tila);
            restTemplate.exchange(getDokumenttiApiBaseUrl(tyyppi) + "/api/dokumentit/pdf/tila/{dokumenttiId}",
                    HttpMethod.POST,
                    entity,
                    String.class,
                    dokumenttiId);
        } catch (Exception e) {
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

    private String getDokumenttiKuvaUrl(DokumenttiTyyppi tyyppi, Long ktId) {
        if (tyyppi.equals(DokumenttiTyyppi.OPS)) {
            return amosaaServiceUrl + AMOSAA_API + "koulutustoimijat/" + ktId +"/opetussuunnitelmat/{opsId}/dokumentti/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else if (tyyppi.equals(DokumenttiTyyppi.TOTEUTUSSUUNNITELMA)) {
            return ylopsServiceUrl + "/api/dokumentit/kuva?opsId={opsId}&tyyppi={tyyppi}&kieli={kieli}";
        } else {
            throw new BusinessRuleViolationException("Dokumenttikuva-urlin valinta epäonnistui.");
        }
    }
}
