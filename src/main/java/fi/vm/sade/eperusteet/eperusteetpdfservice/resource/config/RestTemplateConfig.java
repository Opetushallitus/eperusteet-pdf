package fi.vm.sade.eperusteet.eperusteetpdfservice.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

@Configuration
public class RestTemplateConfig {

    @Bean
    @Scope("prototype")
    HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Caller-Id", "1.2.246.562.10.00000000001.eperusteet.pdf");
        headers.add("CSRF", "CachingRestClient");
        headers.add("Cookie", "CSRF=CachingRestClient");

        return headers;
    }

    @Bean
    @Scope("prototype")
    HttpEntity httpEntity() {
        return new HttpEntity(httpHeaders());
    }

}
