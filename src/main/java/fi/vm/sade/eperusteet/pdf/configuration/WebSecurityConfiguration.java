package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.java_utils.security.OpintopolkuCasAuthenticationFilter;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import fi.vm.sade.javautils.kayttooikeusclient.OphUserDetailsServiceImpl;
import org.apereo.cas.client.session.SingleSignOutFilter;
import org.apereo.cas.client.validation.Cas20ProxyTicketValidator;
import org.apereo.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Profile({"default", "hahtuva"})
@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Value("${host.alb}")
    private String host;

    @Value("${web.url.cas}")
    private String webUrlCas;

    @Value("${fi.vm.sade.eperusteet.oph_username:''}")
    private String username;

    @Value("${fi.vm.sade.eperusteet.oph_password:''}")
    private String password;

    @Value("${cas.service.eperusteet-pdf-service}")
    private String casService;

    @Value("${cas_key}")
    private String casKey;

    @Bean
    public CasAuthenticator casAuthenticator() {
        return new CasAuthenticator(webUrlCas, username, password, host, null, false, null);
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casService + "/j_spring_cas_security_check");
        serviceProperties.setSendRenew(false);
        serviceProperties.setAuthenticateAllArtifacts(true);
        return serviceProperties;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
        casAuthenticationProvider.setAuthenticationUserDetailsService(new OphUserDetailsServiceImpl());
        casAuthenticationProvider.setServiceProperties(serviceProperties());
        casAuthenticationProvider.setTicketValidator(ticketValidator());
        casAuthenticationProvider.setKey(this.casKey);
        return casAuthenticationProvider;
    }

    @Bean
    public TicketValidator ticketValidator() {
        org.apereo.cas.client.validation.Cas20ProxyTicketValidator ticketValidator = new Cas20ProxyTicketValidator(this.webUrlCas);
        ticketValidator.setAcceptAnyProxy(true);
        return ticketValidator;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(HttpSecurity http) throws Exception {
        OpintopolkuCasAuthenticationFilter casAuthenticationFilter = new OpintopolkuCasAuthenticationFilter(serviceProperties());
        casAuthenticationFilter.setAuthenticationManager(authenticationManager(http));
        casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
        casAuthenticationFilter.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
        return casAuthenticationFilter;
    }

    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        return singleSignOutFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .anyRequest().authenticated())
                .addFilter(casAuthenticationFilter(http))
                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .headers().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                .antMatchers("/actuator/health").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/pdf/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .addFilter(casAuthenticationFilter())
//                .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(casAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }
}
