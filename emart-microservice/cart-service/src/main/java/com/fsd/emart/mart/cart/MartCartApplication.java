package com.fsd.emart.mart.cart;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fsd.ermat.common", "com.fsd.ermat.mart.cart" })
public class MartCartApplication {
	public static void main(String[] args) {
		SpringApplication.run(MartCartApplication.class, args);
	}
}
