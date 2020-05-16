package com.fsd.emart.message;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.fsd.emart.*")
@EnableJpaRepositories("com.fsd.emart.common.dao")
@EntityScan("com.fsd.emart.*")
public class DirectMessageApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectMessageApplication.class, args);
	}
}
