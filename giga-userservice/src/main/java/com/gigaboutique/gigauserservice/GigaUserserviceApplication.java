package com.gigaboutique.gigauserservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableJpaRepositories(basePackageClasses = UtilisateurDao.class)
public class GigaUserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GigaUserserviceApplication.class, args);
	}

	@Bean
	public ModelMapper getMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
