package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@Slf4j
@Service
@Profile("!test")
public class AmosaaServiceImpl implements AmosaaService {

    private static final String AMOSAA_PERUSTEET_API = "/api/perusteet/";
    private static final String AMOSAA_ARVIOINTIASTEIKOT_API = "/api/arviointiasteikot/";
    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpEntity httpEntity;

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long cachedPerusteId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_PERUSTEET_API + "{id}/kaikki",
                    HttpMethod.GET,
                    null,
                    String.class,
                    cachedPerusteId);
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public ArviointiAsteikkoDto getArviointiasteikko(Long arviointiAsteikkoId) {
        try {
            ResponseEntity<ArviointiAsteikkoDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_ARVIOINTIASTEIKOT_API + "{arviointiAsteikkoId}",
                    HttpMethod.GET,
                    httpEntity,
                    ArviointiAsteikkoDto.class,
                    arviointiAsteikkoId);
            return response.getBody();
        } catch (Exception e) {
            throw new ServiceException("Sisältöä ei saatu haettua: " + e.getMessage());
        }
    }
}
