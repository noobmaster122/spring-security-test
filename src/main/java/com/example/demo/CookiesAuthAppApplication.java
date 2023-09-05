package com.example.demo;

import org.springframework.boot.SpringApplication;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CookiesAuthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CookiesAuthAppApplication.class, args);
	}

}
