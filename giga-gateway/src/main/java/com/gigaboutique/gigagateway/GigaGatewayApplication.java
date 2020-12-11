package com.gigaboutique.gigagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GigaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaGatewayApplication.class, args);
	}

}
