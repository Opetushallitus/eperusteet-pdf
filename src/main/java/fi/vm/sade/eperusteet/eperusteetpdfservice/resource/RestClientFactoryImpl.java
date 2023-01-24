package fi.vm.sade.eperusteet.eperusteetpdfservice.resource;


import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import org.springframework.stereotype.Service;

@Service
public class RestClientFactoryImpl implements RestClientFactory {
    @Override
    public OphHttpClient get(String service, boolean requireCas) {
        return null;
    }

    @Override
    public String getCallerId() {
        return null;
    }
}
