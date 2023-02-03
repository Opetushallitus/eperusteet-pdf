package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.TermiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AmosaaServiceImpl implements AmosaaService {

    private static final String AMOSAA_API = "/api/amosaa/";
    private static final String AMOSAA_EXTERNAL_API = "/api/external/";
    private static final String AMOSAA_PERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    private RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public OpetussuunnitelmaDto getOpetussuunnitelma(Long ktId, Long opsId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_EXTERNAL_API  + "opetussuunnitelma/{ktId}/{opsId}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    ktId,
                    opsId);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaDto.class);
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public PerusteKaikkiDto getPerusteKaikkiDto(Long id) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_PERUSTEET_API + "{id}/kaikki",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    id);
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public TermiDto getTermi(Long ktId, String avain) {
        try {
            ResponseEntity<TermiDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/{ktId}/termisto/{avain}",
                    HttpMethod.GET,
                    httpEntity,
                    TermiDto.class,
                    ktId,
                    avain);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    // TODO: remove temp funktio
    @Override
    public OpetussuunnitelmaDto getOpetussuunnitelmaTemp(Long ktId, Long opsId) {
        // haetaan opintopolusta opetussuunnitelma
        String tempDevUrl = "https://eperusteet.testiopintopolku.fi/eperusteet-amosaa-service/api/julkinen/koulutustoimijat/98276/opetussuunnitelmat/2096885/julkaisu";
        try {
            ResponseEntity<String> response = restTemplate.exchange(tempDevUrl,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaDto.class);
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    // TODO: remove temp funktio
    @Override
    public PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id) throws JsonProcessingException {
        // haetaan opintopolusta peruste
        String tempDevUrl = "https://eperusteet.testiopintopolku.fi/eperusteet-service/api/perusteet/4804100/kaikki";
        ResponseEntity<String> response = restTemplate.exchange(tempDevUrl,
                HttpMethod.GET,
                httpEntity,
                String.class);
        return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
    }
}
