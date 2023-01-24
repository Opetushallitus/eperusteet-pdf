package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.peruste.PerusteKaikkiDto;
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

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String eperusteetServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    @Override
    public PerusteKaikkiDto getKaikkiDto(Long id, Integer revision) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(eperusteetServiceUrl + "/api/perusteet/" + id + "/kaikki?rev={rev}",
                HttpMethod.GET,
                httpEntity,
                String.class,
                revision);
        return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
    }
}
