package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.KVLiiteJulkinenDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.TermiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EperusteetServiceImpl implements EperusteetService{

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    private static final String EPERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long id, Integer revision) throws JsonProcessingException {
        ResponseEntity<String> response = restTemplate.exchange(eperusteetServiceUrl + EPERUSTEET_API + id + "/kaikki?rev={rev}",
                HttpMethod.GET,
                httpEntity,
                String.class,
                revision);
        return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
    }

    @Override
    public KVLiiteJulkinenDto getKvLiite(Long id) {
        ResponseEntity<KVLiiteJulkinenDto> response = restTemplate.exchange(eperusteetServiceUrl + EPERUSTEET_API + id + "/kvliite",
                HttpMethod.GET,
                httpEntity,
                KVLiiteJulkinenDto.class);
        return response.getBody();
    }

    @Override
    public TermiDto getTermi(Long id, String avain) {
        ResponseEntity<TermiDto> response = restTemplate.exchange(eperusteetServiceUrl + EPERUSTEET_API + id + "/termisto/" + avain,
                HttpMethod.GET,
                httpEntity,
                TermiDto.class);
        return response.getBody();
    }
}
