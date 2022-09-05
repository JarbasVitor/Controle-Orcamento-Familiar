package com.br.alura.financias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FinanciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanciasApplication.class, args);
	}

}
