package fi.vm.sade.eperusteet.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EperusteetPdfServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EperusteetPdfServiceApplication.class, args);
	}

}
