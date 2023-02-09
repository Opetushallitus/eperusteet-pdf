package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.DokumenttiTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
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

    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    HttpEntity httpEntity;

    @PostConstruct
    protected void init() {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public InputStream getLiitetiedosto(Long id, UUID fileName, DokumenttiTyyppi tyyppi) {
        try {
            ResponseEntity<Resource> exchange = restTemplate.exchange(getLiitetiedostoUrl(tyyppi),
                    HttpMethod.GET,
                    httpEntity,
                    Resource.class,
                    id,
                    fileName);
            return Objects.requireNonNull(exchange.getBody()).getInputStream();
        }  catch (Exception e) {
            throw new ServiceException("Liitetiedostoa ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi kuvatyyppi, Kieli kieli, DokumenttiTyyppi dokumenttityyppi, Long ktId) {
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(getDokumenttiKuvaUrl(dokumenttityyppi, ktId),
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    opsId,
                    kuvatyyppi,
                    kieli);
            return response.getBody();
        }  catch (Exception e) {
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
        }  catch (Exception e) {
            throw new ServiceException("Termiä ei saatu haettua: " + e.getMessage());
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
