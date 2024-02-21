package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class YlopsServiceImpl implements YlopsService {

    private static final String API = "/api/";

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

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
    public OrganisaatioDto getOrganisaatio(String oid) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "ulkopuoliset/organisaatiot/{oid}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    oid);
            return objectMapper.readValue(response.getBody(), OrganisaatioDto.class);
        } catch (Exception e) {
            throw new ServiceException("Organisaatiota ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long perusteId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "ulkopuoliset/peruste/{perusteId}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    perusteId);
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
        }
    }
}
