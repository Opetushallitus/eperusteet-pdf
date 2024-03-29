package fi.vm.sade.eperusteet.pdf.configuration;

import fi.vm.sade.eperusteet.pdf.exception.DokumenttiExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@Profile("!test")
public class DokumenttiAsyncConfig implements AsyncConfigurer {

    @Bean(value = "schedulingExecutor")
    public Executor createScheduledExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Override
    @Bean(name = "docTaskExecutor")
    public ThreadPoolTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new DokumenttiExceptionHandler();
    }
}
