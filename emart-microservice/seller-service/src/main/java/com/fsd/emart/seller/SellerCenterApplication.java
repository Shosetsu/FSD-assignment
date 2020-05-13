package com.fsd.emart.seller;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fsd.ermat.common", "com.fsd.ermat.seller" })
public class SellerCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(SellerCenterApplication.class, args);
	}
}
