package fi.vm.sade.eperusteet.eperusteetpdfservice.configuration;

import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.javautils.http.OphHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfigs {

    @Bean
    public RestClientFactory restClientFactory() {
        return new RestClientFactory() {
            @Override
            public OphHttpClient get(String s, boolean b) {
                return null;
            }

            @Override
            public String getCallerId() {
                return "1.2.246.562.10.00000000001.eperusteet.pdf";
            }
        };
    }
}
