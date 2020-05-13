package com.fsd.emart.purchase;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fsd.ermat.common", "com.fsd.ermat.purchase" })
public class PurchaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(PurchaseApplication.class, args);
	}
}
