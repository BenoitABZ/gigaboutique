package com.gigaboutique.gigaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class GigaAdminApplication {

	public static void main(String[] args) {
	        //un com
		SpringApplication.run(GigaAdminApplication.class, args);
	}

}
