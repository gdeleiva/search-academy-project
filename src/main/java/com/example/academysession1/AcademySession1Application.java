package com.example.academysession1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This annotation will automatically scan this package and all its subpackages to process Spring annotations
// It will also enable Spring Boot's autoconfiguration mechanism
// Equivalent to combining @EnableAutoConfiguration, @ComponentScan and @Configuration
@SpringBootApplication
public class AcademySession1Application {

	public static void main(String[] args) {
		SpringApplication.run(AcademySession1Application.class, args);
	}
}
