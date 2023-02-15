package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;
import fi.vm.sade.eperusteet.pdf.dto.enums.SisaltoTyyppi;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AmosaaServiceImpl implements AmosaaService {

    private static final String AMOSAA_API = "/api/amosaa/";
    private static final String AMOSAA_EXTERNAL_API = "/api/external/";
    private static final String AMOSAA_PERUSTEET_API = "/api/perusteet/";
    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Value("${fi.vm.sade.eperusteet.pdf.amosaa-service:''}")
    private String amosaaServiceUrl;

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
    public OpetussuunnitelmaKaikkiDto getOpetussuunnitelma(Long ktId, Long opsId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_EXTERNAL_API  + "opetussuunnitelma/{ktId}/{opsId}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    ktId,
                    opsId);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Opetussuunnitelmaa ei saatu haettua: " + e.getMessage());
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
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
        }
    }

//    @Override
//    public PerusteenOsaDto getPerusteenOsa(Long perusteId, Long perusteenosaId) {
//        try {
//            ResponseEntity<PerusteenOsaDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_PERUSTEET_API  + "{perusteId}/perusteenosa/{perusteenosaId}",
//                    HttpMethod.GET,
//                    httpEntity,
//                    PerusteenOsaDto.class,
//                    perusteId,
//                    perusteenosaId);
//            return response.getBody();
//        } catch (Exception e) {
//            throw new ServiceException("Perusteenosaa ei saatu haettua: " + e.getMessage());
//        }
//    }

    @Override
    public List<SisaltoViiteDto> getSisaltoviitteenTyypilla(Long ktId, Long opsId, SisaltoTyyppi tyyppi) {
        try {
            ResponseEntity<SisaltoViiteDto[]> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/{ktId}/opetussuunnitelmat/{opsId}/sisaltoviitteet/{tyyppi}",
                    HttpMethod.GET,
                    httpEntity,
                    SisaltoViiteDto[].class,
                    ktId,
                    opsId,
                    tyyppi);
            return Arrays.asList(Objects.requireNonNull(response.getBody()));
        } catch (Exception e) {
            throw new ServiceException("Sisältöä ei saatu haettua: " + e.getMessage());
        }
    }








    // TODO: remove temp funktio
    @Override
    public OpetussuunnitelmaKaikkiDto getOpetussuunnitelmaTemp(Long ktId, Long opsId) {
        // haetaan opintopolusta opetussuunnitelma
        String tempDevUrl = "https://eperusteet.testiopintopolku.fi/eperusteet-amosaa-service/api/julkinen/koulutustoimijat/98276/opetussuunnitelmat/2096885/julkaisu";
        try {
            ResponseEntity<String> response = restTemplate.exchange(tempDevUrl,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Opetussuunnitelmaa ei saatu haettua: " + e.getMessage());
        }
    }

    // TODO: remove temp funktio
    @Override
    public PerusteKaikkiDto getPerusteKaikkiDtoTemp(Long id) {
        // haetaan opintopolusta peruste
        String tempDevUrl = "https://eperusteet.testiopintopolku.fi/eperusteet-service/api/perusteet/4804100/kaikki";
        try {
            ResponseEntity<String> response = restTemplate.exchange(tempDevUrl,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), PerusteKaikkiDto.class);
        } catch (Exception e) {
            throw new ServiceException("Perustedataa ei saatu haettua: " + e.getMessage());
        }
    }
}
