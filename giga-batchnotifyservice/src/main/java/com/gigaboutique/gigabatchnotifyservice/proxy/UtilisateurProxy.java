package com.gigaboutique.gigabatchnotifyservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gigaboutique.gigabatchnotifyservice.dto.UtilisateurDto;

@FeignClient(name = "giga-userservice", url = "localhost:8001")
public interface UtilisateurProxy {

	@GetMapping("/utilisateur/getall")
	public List<UtilisateurDto> getUtilisateurs();

	@GetMapping("/utilisateur/signup")
	public UtilisateurDto signUp();

	@PostMapping("/login/utilisateur")
	public void login();

}
