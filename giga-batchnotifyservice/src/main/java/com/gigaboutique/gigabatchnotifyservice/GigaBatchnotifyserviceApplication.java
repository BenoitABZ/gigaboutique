package com.gigaboutique.gigabatchnotifyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients("com.gigaboutique.gigabatchnotifyservice.proxy")
public class GigaBatchnotifyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaBatchnotifyserviceApplication.class, args);
	}

}
