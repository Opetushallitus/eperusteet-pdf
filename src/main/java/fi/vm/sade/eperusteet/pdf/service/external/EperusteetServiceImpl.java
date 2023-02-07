package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EperusteetServiceImpl implements EperusteetService {

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    private static final String EPERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long id, Integer revision) {
        try {
            String url = eperusteetServiceUrl + EPERUSTEET_API + id + "/kaikki";
            if (revision != null) {
                url = url.concat("?rev=" + revision);
            }

            ResponseEntity<String> response = restTemplate.exchange(url,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public KVLiiteJulkinenDto getKvLiite(Long id) {
        try {
            ResponseEntity<KVLiiteJulkinenDto> response = restTemplate.exchange(eperusteetServiceUrl + EPERUSTEET_API + "{id}/kvliite",
                    HttpMethod.GET,
                    httpEntity,
                    KVLiiteJulkinenDto.class,
                    id);
            return response.getBody();
        } catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public ArviointiAsteikkoDto getArviointiasteikko(Long id) {
        try {
            ResponseEntity<ArviointiAsteikkoDto> response = restTemplate.exchange(eperusteetServiceUrl + EPERUSTEET_API + "arviointiasteikot/{id}",
                    HttpMethod.GET,
                    httpEntity,
                    ArviointiAsteikkoDto.class,
                    id);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    // TODO: remove temp funktio
    @Override
    public PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id, Integer revision) throws JsonProcessingException {
        // haetaan opintopolusta peruste
        String tempDevUrl = "https://eperusteet.testiopintopolku.fi/eperusteet-service/api/perusteet/2499640/kaikki";
        ResponseEntity<String> response = restTemplate.exchange(tempDevUrl,
                HttpMethod.GET,
                httpEntity,
                String.class);
        return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
    }
}
