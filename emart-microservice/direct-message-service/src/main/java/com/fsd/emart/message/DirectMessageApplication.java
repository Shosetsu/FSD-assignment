package com.fsd.emart.message;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fsd.ermat.common", "com.fsd.ermat.message" })
public class DirectMessageApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectMessageApplication.class, args);
	}
}
