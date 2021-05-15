package com.gigaboutique.gigaclientservice;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.gigaboutique.gigaclientservice")
public class GigaClientserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaClientserviceApplication.class, args);
	}
/*	
	@Bean
	public ThymeleafViewResolver viewResolver(SpringTemplateEngine templateEngine){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setContentType("application/json");
	    viewResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
	    viewResolver.setOrder(1);
	    viewResolver.setViewNames(new String[] {"*.json"});
	    viewResolver.setTemplateEngine(templateEngine);
	    return viewResolver;
	}
*/
}
