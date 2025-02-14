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
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.Date;

@Service
@Profile("!test")
public class YlopsServiceImpl implements YlopsService {

    private static final String API = "/api/";

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpEntity httpEntity;

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
    public PerusteKaikkiDto getOpetussuunnitelmaPeruste(Long perusteId, Date aikaleima) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "ulkopuoliset/peruste/{perusteId}/julkaisuhetki/{aikaleima}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    perusteId,
                    aikaleima.getTime());
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
        }
    }
}
