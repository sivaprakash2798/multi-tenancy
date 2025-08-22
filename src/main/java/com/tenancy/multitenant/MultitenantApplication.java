package com.tenancy.multitenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.tenancy.multitenant")
@EnableJpaRepositories(basePackages = "com.tenancy.multitenant.repository")
@EntityScan(basePackages = "com.tenancy.multitenant.model")
public class MultitenantApplication {
	public static void main(String[] args) {
		SpringApplication.run(MultitenantApplication.class, args);
	}
}

