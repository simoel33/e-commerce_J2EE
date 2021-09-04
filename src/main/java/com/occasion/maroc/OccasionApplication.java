package com.occasion.maroc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OccasionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OccasionApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	@Bean
public SpringApplicationContext springApplicationContext() {
		
		return new SpringApplicationContext();
	}
	

}
