package com.gigaboutique.gigaconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GigaConfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaConfigserverApplication.class, args);
	}

}
