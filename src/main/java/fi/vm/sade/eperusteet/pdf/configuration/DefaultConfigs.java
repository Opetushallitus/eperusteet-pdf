package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.eperusteet.utils.client.RestTemplateConfig;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ RestTemplateConfig.class })
public class DefaultConfigs {

    //TODO: temppitavaraa devauksen ajaksi
    private static final int TIMEOUT = 60000;
    public static final String CALLER_ID = "1.2.246.562.10.00000000001.eperusteet-amosaa";
    @Value("${fi.vm.sade.eperusteet.amosaa.oph_username:''}")
    private String username;

    @Value("${fi.vm.sade.eperusteet.amosaa.oph_password:''}")
    private String password;

    @Value("${web.url.cas:''}")
    private String casUrl;

    @Bean
    public RestClientFactory restClientFactory() {
        return new RestClientFactory() {
            @Override
            public OphHttpClient get(String s, boolean b) {
                OphHttpClient client;
                if (b) {
                    CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
                            .username(username)
                            .password(password)
                            .webCasUrl(casUrl)
                            .casServiceUrl(s)
                            .build();

                    client = new OphHttpClient.Builder(CALLER_ID)
                            .authenticator(casAuthenticator)
                            .timeoutMs(TIMEOUT)
                            .build();
                } else {
                    client = new OphHttpClient.Builder(CALLER_ID)
                            .timeoutMs(TIMEOUT)
                            .build();
                }
                return client;
            }

            @Override
            public String getCallerId() {
                return "1.2.246.562.10.00000000001.eperusteet.pdf";
            }
        };
    }
}
