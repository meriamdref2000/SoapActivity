package comptes;

import comptes.jax.ws.web.service.Compte;
import comptes.soap.web.service.with.spring.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SoapWebServiceWithSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(SoapWebServiceWithSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			compteRepository.getAllComptes().add(new Compte(1L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(2L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(3L, Math.random() * 9888, new Date()));
		};
	}
}
