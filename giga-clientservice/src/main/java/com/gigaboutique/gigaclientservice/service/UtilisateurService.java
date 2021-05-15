package com.gigaboutique.gigaclientservice.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaclientservice.configuration.SecurityConstant;
import com.gigaboutique.gigaclientservice.dto.RegisterDto;
import com.gigaboutique.gigaclientservice.dto.UtilisateurDto;
import com.gigaboutique.gigaclientservice.proxy.GatewayProxy;

@Service
public class UtilisateurService {

	@Autowired
	GatewayProxy gatewayProxy;
	
	@Autowired
	SecurityConstant sc;
	
	@Autowired
	TokenService tokenService;

	public boolean signUp(RegisterDto registerDto) throws ResponseStatusException {

		boolean bool = false;

		UtilisateurDto utilisateurDto = gatewayProxy.signUp(registerDto);

		if (utilisateurDto != null) {

			bool = true;
		}

		return bool;
	}

	public String login(String mail, String mdp, HttpServletResponse response) throws ResponseStatusException {

		ResponseEntity<String> responseEntity = gatewayProxy.login(mail, mdp);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {

			String token = responseEntity.getHeaders().getFirst(sc.getHeader());
			
			tokenService.storeToken(response, token);

			return token;

		}

		return null;
		
	}
}
