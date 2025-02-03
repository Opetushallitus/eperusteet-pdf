package fi.vm.sade.eperusteet.pdf.configuration;

import com.fasterxml.jackson.core.StreamReadConstraints;
import fi.vm.sade.eperusteet.pdf.exception.RestTemplateResponseErrorHandler;
import fi.vm.sade.eperusteet.utils.client.RestClientFactory;
import fi.vm.sade.eperusteet.utils.client.RestTemplateConfig;
import fi.vm.sade.javautils.http.OphHttpClient;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Configuration
@Import({ RestTemplateConfig.class })
public class DefaultConfigs {

    //TODO: temppitavaraa devauksen ajaksi
    private static final int TIMEOUT = 60000;
    public static final String CALLER_ID = "1.2.246.562.10.00000000001.eperusteet-pdf";
    @Value("${fi.vm.sade.eperusteet.oph_username:''}")
    private String username;

    @Value("${fi.vm.sade.eperusteet.oph_password:''}")
    private String password;

    @Value("${web.url.cas:''}")
    private String casUrl;

    @Bean
    public RestClientFactory restClientFactory() {
        return new RestClientFactory() {
            @Override
            public OphHttpClient get(String service, boolean requireCas) {
                OphHttpClient client;
                if (requireCas) {
                    CasAuthenticator casAuthenticator = new CasAuthenticator.Builder()
                            .username(username)
                            .password(password)
                            .webCasUrl(casUrl)
                            .casServiceUrl(service)
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
                return CALLER_ID;
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .connectTimeout(Duration.ofSeconds(60))
                .readTimeout(Duration.ofSeconds(60))
                .build();
    }

    static {
        StreamReadConstraints.overrideDefaultStreamReadConstraints(
                StreamReadConstraints.builder().maxStringLength(200000000).build()
        );
    }
}
