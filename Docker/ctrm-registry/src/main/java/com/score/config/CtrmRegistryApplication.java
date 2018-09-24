package com.score.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CtrmRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtrmRegistryApplication.class, args);
	}
}
