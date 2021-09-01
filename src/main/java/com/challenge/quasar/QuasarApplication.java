package com.challenge.quasar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class QuasarApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuasarApplication.class, args);
		//TODO:
		// Add more unit testing.
		// Create exception classes for location and messaging errors.
		// Think about the caching time and how topSecretSplit will be used.
	}

}
