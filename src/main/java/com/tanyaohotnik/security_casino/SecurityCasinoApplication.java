package com.tanyaohotnik.security_casino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tanyaohotnik.security_casino")
public class SecurityCasinoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCasinoApplication.class, args);
	}
}
