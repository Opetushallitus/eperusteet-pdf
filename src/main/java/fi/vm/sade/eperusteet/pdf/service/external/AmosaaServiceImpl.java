package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.SisaltoTyyppi;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteKaikkiDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.peruste.PerusteenOsaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.teksti.SisaltoViiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    public PerusteenOsaDto getPerusteenOsa(Long perusteId, Long perusteenosaId) {
        try {
            ResponseEntity<PerusteenOsaDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_PERUSTEET_API  + "{perusteId}/perusteenosa/{perusteenosaId}",
                    HttpMethod.GET,
                    httpEntity,
                    PerusteenOsaDto.class,
                    perusteId,
                    perusteenosaId);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

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
