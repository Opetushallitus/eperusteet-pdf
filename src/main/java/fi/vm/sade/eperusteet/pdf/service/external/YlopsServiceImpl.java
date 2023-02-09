package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.ylops.OpetussuunnitelmaExportDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
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
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YlopsServiceImpl implements YlopsService {

    private static final String API = "/api/";
    private static final String YLOPS_OPETUSSUUNNITELMA_API = "/api/opetussuunnitelmat/julkiset/{opsId}/julkaisu";
    private static final String YLOPS_PERUSTEET_API = "/api/perusteet/";
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
    public List<TekstiKappaleViiteDto.Matala> getTekstiKappaleViiteOriginals(Long opsId, Long viiteId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "opetussuunnitelmat/{opsId}/tekstit/{viiteId}/alkuperaiset",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    opsId,
                    viiteId);
            return Collections.singletonList(objectMapper.readValue(response.getBody(), TekstiKappaleViiteDto.Matala.class));
        } catch (Exception e) {
            throw new ServiceException("Tekstikappaleviitettä ei saatu haettua: " + e.getMessage());
        }
    }

    @Override
    public TekstiKappaleViiteDto getLops2019PerusteTekstikappale(Long opsId, Long tekstikappaleId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "opetussuunnitelmat/{opsId}/peruste/tekstikappaleet/{tekstikappaleId}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    opsId,
                    tekstikappaleId);
            return objectMapper.readValue(response.getBody(), TekstiKappaleViiteDto.class);
        } catch (Exception e) {
            throw new ServiceException("Perustetekstikappaletta ei saatu haettua: " + e.getMessage());
        }
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
}
