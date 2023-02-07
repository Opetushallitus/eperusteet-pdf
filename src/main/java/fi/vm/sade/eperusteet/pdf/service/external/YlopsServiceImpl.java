package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.dto.ylops.koodisto.OrganisaatioDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaKevytDto;
import fi.vm.sade.eperusteet.pdf.dto.ylops.teksti.TekstiKappaleViiteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class YlopsServiceImpl implements YlopsService {

    private static final String API = "/api/";
    private static final String YLOPS_EXTERNAL_API = "/api/external/";
    private static final String YLOPS_PERUSTEET_API = "/api/perusteet/";

    @Value("${fi.vm.sade.eperusteet.pdf.ylops-service:''}")
    private String ylopsServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    private RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = InitJacksonConverter.createMapper();

    @Override
    public OpetussuunnitelmaDto getOpetussuunnitelma(Long opsId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API + "opetussuunnitelmat/{opsId}",
                    HttpMethod.GET,
                    httpEntity,
                    String.class,
                    opsId);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaDto.class);
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
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
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
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
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
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
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public byte[] getDokumenttiLiite(Long opsId, Kuvatyyppi tiedostonimi) {
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(ylopsServiceUrl + API + "opetussuunnitelmat/{opsId}/kuvat/{tiedostonimi}",
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    tiedostonimi);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }









    // TODO: remove temp funktio
    @Override
    public OpetussuunnitelmaKevytDto getOpetussuunnitelmaTemp(Long opsId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + API  + "opetussuunnitelmat/" + opsId,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaKevytDto.class);
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }
}
