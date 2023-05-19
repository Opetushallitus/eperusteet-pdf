package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.java_utils.security.OpintopolkuCasAuthenticationFilter;
import fi.vm.sade.javautils.http.auth.CasAuthenticator;
import fi.vm.sade.javautils.kayttooikeusclient.OphUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Profile("default")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Value("${host.alb}")
//    private String host;
//
//    @Value("${web.url.cas")
//    private String webCasUrl;
//
//    @Value("${fi.vm.sade.eperusteet.oph_username:''}")
//    private String username;
//
//    @Value("${fi.vm.sade.eperusteet.oph_password:''}")
//    private String password;
//
//    @Value("${cas.service.eperusteet-pdf-service}")
//    private String casService;
//
//    @Value("${cas_key}")
//    private String casKey;
//
//    @Bean
//    public CasAuthenticator casAuthenticator() {
//        return new CasAuthenticator(webCasUrl, username, password, host, null, false, null);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new OphUserDetailsServiceImpl(host, DefaultConfigs.CALLER_ID, casAuthenticator());
//    }
//
//    @Bean
//    public ServiceProperties serviceProperties() {
//        ServiceProperties serviceProperties = new ServiceProperties();
//        serviceProperties.setService(casService + "/j_spring_cas_security_check");
//        serviceProperties.setSendRenew(false);
//        serviceProperties.setAuthenticateAllArtifacts(true);
//        return serviceProperties;
//    }
//
//    @Bean
//    public CasAuthenticationProvider casAuthenticationProvider() {
//        CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
//        casAuthenticationProvider.setUserDetailsService(userDetailsService());
//        casAuthenticationProvider.setServiceProperties(serviceProperties());
//        casAuthenticationProvider.setTicketValidator(ticketValidator());
//        casAuthenticationProvider.setKey(casKey);
//        return casAuthenticationProvider;
//    }
//
//    @Bean
//    public TicketValidator ticketValidator() {
//        Cas20ProxyTicketValidator ticketValidator = new Cas20ProxyTicketValidator(webCasUrl);
//        ticketValidator.setProxyCallbackUrl(casService + "/j_spring_cas_security_proxyreceptor");
//        ticketValidator.setAcceptAnyProxy(true);
//        return ticketValidator;
//    }
//
//    @Bean
//    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
//        OpintopolkuCasAuthenticationFilter casAuthenticationFilter = new OpintopolkuCasAuthenticationFilter(serviceProperties());
//        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        casAuthenticationFilter.setFilterProcessesUrl("/j_spring_cas_security_check");
//        return casAuthenticationFilter;
//    }
//
//    @Bean
//    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
//        CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
//        casAuthenticationEntryPoint.setLoginUrl(webCasUrl + "/login");
//        casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
//        return casAuthenticationEntryPoint;
//    }

    //TODO: väliaikaista devaukselle
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers("/actuator/health").permitAll()
                .antMatchers(HttpMethod.POST, "/api/pdf/**").permitAll()
                .anyRequest().permitAll();
//                .and()
//                .addFilter(casAuthenticationFilter())
//                .exceptionHandling()
//                .authenticationEntryPoint(casAuthenticationEntryPoint());
    }
}
