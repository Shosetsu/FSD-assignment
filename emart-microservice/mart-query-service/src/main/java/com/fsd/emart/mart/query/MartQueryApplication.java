package com.fsd.emart.mart.query;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fsd.ermat.common", "com.fsd.ermat.mart.query" })
public class MartQueryApplication {
	public static void main(String[] args) {
		SpringApplication.run(MartQueryApplication.class, args);
	}
}
