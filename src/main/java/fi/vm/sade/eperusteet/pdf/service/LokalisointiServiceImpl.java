package fi.vm.sade.eperusteet.pdf.service;

import fi.vm.sade.eperusteet.pdf.dto.LokalisointiDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

//TODO: copypastetettu

@Service
public class LokalisointiServiceImpl implements LokalisointiService {

    private static final Logger LOG = LoggerFactory.getLogger(LokalisointiService.class);

    @Value("${lokalisointi.service.url:https://virkailija.opintopolku.fi/lokalisointi/cxf/rest/v1/localisation?}")
    private String lokalisointiServiceUrl;

    @Value("${lokalisointi.service.category:eperusteet}")
    private String category;

    @Autowired
    HttpEntity httpEntity;

    @Autowired
    HttpHeaders httpHeaders;

    @Override
    @Cacheable("lokalisoinnit")
    public LokalisointiDto get(String key, String locale) {
        RestTemplate restTemplate = new RestTemplate();
        String url = lokalisointiServiceUrl + "category=" + category + "&locale=" + locale + "&key=" + key;
        LokalisointiDto[] re;
        try {
            ResponseEntity<LokalisointiDto[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, LokalisointiDto[].class);
            re = response.getBody();

        } catch (RestClientException ex) {
            LOG.error("Rest client error: {}", ex.getLocalizedMessage());
            re = new LokalisointiDto[]{};
        }

        if (re.length > 1) {
            LOG.warn("Got more than one object: {} from {}", re, url);
        }
        if (re.length > 0) {
            return re[0];
        }
        return null;
    }
}
