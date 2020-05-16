package com.fsd.emart.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.fsd.emart.*")
@EnableJpaRepositories("com.fsd.emart.common.dao")
@EntityScan("com.fsd.emart.*")
public class SellerCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerCenterApplication.class, args);
    }
}
