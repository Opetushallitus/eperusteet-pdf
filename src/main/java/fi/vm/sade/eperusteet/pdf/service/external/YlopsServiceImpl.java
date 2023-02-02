package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.InitJacksonConverter;
import fi.vm.sade.eperusteet.pdf.domain.common.enums.Kuvatyyppi;
import fi.vm.sade.eperusteet.pdf.domain.eperusteet.enums.Kieli;
import fi.vm.sade.eperusteet.pdf.dto.ylops.ops.OpetussuunnitelmaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YlopsServiceImpl implements YlopsService {

    private static final String YLOPS_API = "/api/ylops/";
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
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + YLOPS_API  + "opetussuunnitelmat/" + opsId,
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
    public OpetussuunnitelmaDto getOpetussuunnitelmaTemp(Long opsId) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(ylopsServiceUrl + YLOPS_API  + "opetussuunnitelmat/" + opsId,
                    HttpMethod.GET,
                    httpEntity,
                    String.class);
            return objectMapper.readValue(response.getBody(), OpetussuunnitelmaDto.class);
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public byte[] getDokumenttiKuva(Long opsId, Kuvatyyppi tyyppi, Kieli kieli) {
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(ylopsServiceUrl + YLOPS_API  + "dokumentit/kuva?opsId={opsId}?tyyppi={tyyppi}?kieli={kieli}",
                    HttpMethod.GET,
                    httpEntity,
                    byte[].class,
                    opsId,
                    tyyppi.toString(),
                    kieli.toString());
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }
}
