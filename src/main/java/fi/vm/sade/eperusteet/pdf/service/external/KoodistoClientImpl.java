package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.eperusteet.koodisto.KoodistoKoodiDto;
import fi.vm.sade.eperusteet.pdf.service.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Profile("default")
public class KoodistoClientImpl implements KoodistoClient {

    @Value("${koodisto.service.url:https://virkailija.opintopolku.fi/koodisto-service}")
    private String koodistoServiceUrl;

    private static final String KOODISTO_API = "/rest/json/";
    private static final String CODEELEMENT = "/rest/codeelement";

    @Autowired
    RestClientFactory restClientFactory;

    @Autowired
    KoodistoClient self; // for cacheable

//    @Autowired
//    CacheManager cacheManager;

//    @Autowired
//    OphClientHelper ophClientHelper;

//    @Autowired
//    private DtoMapper mapper;

    @Autowired
    HttpEntity httpEntity;

    @Override
    public List<KoodistoKoodiDto> getAll(String koodisto) {
        return self.getAll(koodisto, false);
    }

    @Override
    @Cacheable(value = "koodistot", key = "#p0 + #p1")
    public List<KoodistoKoodiDto> getAll(String koodisto, boolean onlyValidKoodis) {
        RestTemplate restTemplate = new RestTemplate();
        String url = koodistoServiceUrl + KOODISTO_API + koodisto + "/koodi?onlyValidKoodis=" + onlyValidKoodis;
        try {
            ResponseEntity<KoodistoKoodiDto[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, KoodistoKoodiDto[].class);
            List<KoodistoKoodiDto> koodistoDtot = List.of(response.getBody());
            return koodistoDtot;
        } catch (HttpServerErrorException ex) {
            throw new BusinessRuleViolationException("koodistoa-ei-loytynyt");
        }
    }

    @Override
    public KoodistoKoodiDto get(String koodistoUri, String koodiUri) {
        // yritetään hakea ensin cachesta
        Optional<KoodistoKoodiDto> koodistoKoodi = this.self.getAll(koodistoUri).stream()
                .filter(koodi -> koodi.getKoodiUri().equals(koodiUri))
                .findFirst();
        return koodistoKoodi.orElseGet(() -> self.get(koodistoUri, koodiUri, null));
    }

    @Override
    @Cacheable("koodistokoodit")
    public KoodistoKoodiDto get(String koodistoUri, String koodiUri, Long versio) {
        if (koodistoUri == null || koodiUri == null) {
            return null;
        }
        RestTemplate restTemplate = new RestTemplate();
        String url = koodistoServiceUrl + KOODISTO_API + koodistoUri + "/koodi/" + koodiUri + (versio != null ? "?koodistoVersio=" + versio.toString() : "");
        ResponseEntity<KoodistoKoodiDto> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, KoodistoKoodiDto.class);
        return response.getBody();
    }
}
