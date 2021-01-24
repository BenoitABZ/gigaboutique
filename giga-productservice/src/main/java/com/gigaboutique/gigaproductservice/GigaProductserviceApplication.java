package com.gigaboutique.gigaproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GigaProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaProductserviceApplication.class, args);
	}

}
