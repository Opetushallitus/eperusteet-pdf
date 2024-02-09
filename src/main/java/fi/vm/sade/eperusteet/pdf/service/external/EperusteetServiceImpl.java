package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.OphHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static javax.servlet.http.HttpServletResponse.SC_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Service
public class EperusteetServiceImpl implements EperusteetService {

    private static final String EPERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    HttpEntity httpEntity;

    @Autowired
    private RestClientFactory restClientFactory;

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public KVLiiteJulkinenDto getKvLiite(Long id) {
        OphHttpClient client = restClientFactory.get(eperusteetServiceUrl, true);
        String url = UriComponentsBuilder.fromUriString(eperusteetServiceUrl + EPERUSTEET_API + "{id}/kvliite").build(id).toString();
        OphHttpRequest request = OphHttpRequest.Builder.get(url).build();

        return client.<KVLiiteJulkinenDto>execute(request)
                .handleErrorStatus(SC_FOUND, SC_UNAUTHORIZED, SC_FORBIDDEN, SC_METHOD_NOT_ALLOWED, SC_BAD_REQUEST, SC_INTERNAL_SERVER_ERROR)
                .with(error -> {
                    throw new RuntimeException("KV-liitettä ei saatu haettua: " + error);
                })
                .expectedStatus(SC_OK, SC_CREATED)
                .mapWith(text -> {
                    try {
                        return objectMapper.readValue(text, KVLiiteJulkinenDto.class);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElse(null);
    }

    @Override
    public ArviointiAsteikkoDto getArviointiasteikko(Long id) {
        try {
            ResponseEntity<ArviointiAsteikkoDto> response = restTemplate.exchange(eperusteetServiceUrl + "/api/arviointiasteikot/{id}",
                    HttpMethod.GET,
                    httpEntity,
                    ArviointiAsteikkoDto.class,
                    id);
            return response.getBody();
        } catch (Exception e) {
            throw new ServiceException("Arviointiasteikkoa ei saatu haettua: " + e.getMessage());
        }
    }
}
