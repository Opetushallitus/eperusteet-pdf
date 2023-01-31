package fi.vm.sade.eperusteet.pdf.service.external;

import fi.vm.sade.eperusteet.pdf.dto.amosaa.koulutustoimija.OpetussuunnitelmaDto;
import fi.vm.sade.eperusteet.pdf.dto.amosaa.ops.TermiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AmosaaServiceImpl implements AmosaaService{

    private static final String AMOSAA_API = "/api/amosaa/";

    @Value("${fi.vm.sade.eperusteet.pdf.eperusteet-service:''}")
    private String amosaaServiceUrl;

    @Autowired
    HttpEntity httpEntity;

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public OpetussuunnitelmaDto getOpetussuunnitelma(Long ktId, Long opsId) {
        try {
            ResponseEntity<OpetussuunnitelmaDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/" + ktId + "/opetussuunnitelmat/" + opsId,
                    HttpMethod.GET,
                    httpEntity,
                    OpetussuunnitelmaDto.class);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }

    @Override
    public TermiDto getTermi(Long ktId, String avain) {
        try {
            ResponseEntity<TermiDto> response = restTemplate.exchange(amosaaServiceUrl + AMOSAA_API  + "koulutustoimijat/" + ktId + "/termisto/" + avain,
                    HttpMethod.GET,
                    httpEntity,
                    TermiDto.class);
            return response.getBody();
        }  catch (Exception e) {
            // TODO: käsittele poikkeus
            return null;
        }
    }
}
