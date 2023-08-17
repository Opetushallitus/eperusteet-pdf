package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.common.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.fenum.qual.AwtFlowLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class KoodistoClientImpl implements KoodistoClient {

    @Value("${koodisto.service.url:https://virkailija.opintopolku.fi/koodisto-service}")
    private String koodistoServiceUrl;

    private static final String KOODISTO_API = "/rest/json/";

    @Autowired
    RestClientFactory restClientFactory;

    @Autowired
    @Lazy
    KoodistoClient self; // for cacheable

    @Autowired
    HttpEntity httpEntity;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    protected void init() {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    @Cacheable(value = "koodistot")
    public List<KoodistoKoodiDto> getAll(String koodisto) {
        String url = koodistoServiceUrl + KOODISTO_API + koodisto + "/koodi?onlyValidKoodis=" + false;
        try {
            ResponseEntity<KoodistoKoodiDto[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, KoodistoKoodiDto[].class);
            List<KoodistoKoodiDto> koodistoDtot = List.of(response.getBody());
            return koodistoDtot;
        } catch (Exception ex) {
            throw new ServiceException("Koodistoa ei löytynyt " + ex.getMessage());
        }
    }

    @Override
    public KoodistoKoodiDto get(String koodistoUri, String koodiUri) {
        // yritetään hakea ensin cachesta
        Optional<KoodistoKoodiDto> koodistoKoodi = this.self.getAll(koodistoUri).stream()
                .filter(koodi -> koodi.getKoodiUri().equals(koodiUri))
                .findFirst();
        return koodistoKoodi.orElseGet(() -> null);
    }
    
    @Override
    public KoodistoKoodiDto getByUri(String uri) {
        String[] splitted = uri.split("_");
        if (splitted.length < 2) {
            return null;
        } else if (splitted.length == 2) {
            return get(splitted[0], uri);
        } else if (splitted[0].startsWith("paikallinen_tutkinnonosa")) {
            return null; // FIXME
        } else {
            return null;
        }
    }
}
