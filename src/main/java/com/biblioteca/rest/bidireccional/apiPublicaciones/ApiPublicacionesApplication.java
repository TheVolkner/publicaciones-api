package com.biblioteca.rest.bidireccional.apiPublicaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiPublicacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPublicacionesApplication.class, args);
	}

}
