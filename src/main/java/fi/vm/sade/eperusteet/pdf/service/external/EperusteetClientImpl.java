package fi.vm.sade.eperusteet.pdf.service.external;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fi.vm.sade.eperusteet.pdf.configuration.AbstractRakenneOsaDeserializer;
import fi.vm.sade.eperusteet.pdf.configuration.MappingModule;
import fi.vm.sade.eperusteet.pdf.dto.eperusteet.tutkinnonrakenne.AbstractRakenneOsaDto;
import fi.vm.sade.eperusteet.pdf.service.exception.BusinessRuleViolationException;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.OphHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@Service
@Profile("default")
@Transactional
public class EperusteetClientImpl implements EperusteetClient {
    private static final Logger logger = LoggerFactory.getLogger(EperusteetClientImpl.class);

    @Value("${fi.vm.sade.eperusteet.amosaa.eperusteet-service: ''}")
    private String eperusteetServiceUrl;

    @Autowired
    RestClientFactory restClientFactory;

    private OphHttpClient client;

    private ObjectMapper mapper;

    @PostConstruct
    protected void init() {
        client = restClientFactory.get(eperusteetServiceUrl, false);
        mapper = new ObjectMapper();
        MappingModule module = new MappingModule();
        module.addDeserializer(AbstractRakenneOsaDto.class, new AbstractRakenneOsaDeserializer());
        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }

    private <T> T commonGet(String endpoint, Class<T> type) {

        String url = eperusteetServiceUrl + endpoint;

        OphHttpRequest request = OphHttpRequest.Builder
                .get(url)
                .addHeader("Accept-Charset", "UTF-8")
                .build();

        return client.<T>execute(request)
                .expectedStatus(SC_OK)
                .mapWith(text -> {
                    try {
                        return mapper.readValue(text, type);
                    } catch (IOException e) {
                        throw new BusinessRuleViolationException("haku-epaonnistui");
                    }
                })
                .orElse(null);
    }

    @Override
    public String getPerusteData(Long id) {
        try {
            JsonNode node = commonGet("/api/perusteet/" + id + "/kaikki", JsonNode.class);
            Object perusteObj = mapper.treeToValue(node, Object.class);
            String json = mapper.writeValueAsString(perusteObj);
            return json;
        } catch (IOException ex) {
            throw new BusinessRuleViolationException("perustetta-ei-loytynyt");
        }
    }
}
