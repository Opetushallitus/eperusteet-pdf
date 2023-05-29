package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.properties.OphProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Profile({"default", "hahtuva"})
@PropertySource("classpath:application.properties")
@Configuration
public class UrlConfiguration extends OphProperties {

    @Autowired
    public UrlConfiguration(Environment environment) {
        this.addDefault("host.alb", environment.getRequiredProperty("host.alb"));
        this.addDefault("cas.base", environment.getRequiredProperty("web.url.cas"));
    }
}