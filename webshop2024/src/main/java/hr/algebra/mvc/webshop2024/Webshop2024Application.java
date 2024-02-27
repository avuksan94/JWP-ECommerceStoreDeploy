package hr.algebra.mvc.webshop2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
@EntityScan("hr.algebra.mvc.webshop2024.DAL.Entity")
@EnableJpaRepositories("hr.algebra.mvc.webshop2024.DAL.Repository")
@ComponentScan(basePackages = {"hr.algebra.mvc.webshop2024","hr.algebra.mvc.webshop2024.DAL", "hr.algebra.mvc.webshop2024.BL"})
public class Webshop2024Application {

	public static void main(String[] args) {
		SpringApplication.run(Webshop2024Application.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
