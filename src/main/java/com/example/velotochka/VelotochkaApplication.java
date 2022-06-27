package com.example.velotochka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VelotochkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(VelotochkaApplication.class, args);
	}
}
