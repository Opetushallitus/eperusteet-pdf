package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.common.ArviointiAsteikkoDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EperusteetServiceImpl implements EperusteetService {
    private static final String EPERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    HttpEntity httpEntity;

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

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
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
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
            throw new ServiceException("KV-liitettä ei saatu haettua: " + e.getMessage());
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
        } catch (Exception e) {
            throw new ServiceException("Arviointiasteikkoa ei saatu haettua: " + e.getMessage());
        }
    }
}
