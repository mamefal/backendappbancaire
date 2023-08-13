package com.mame.appbancaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppbancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppbancaireApplication.class, args);
	}

}
