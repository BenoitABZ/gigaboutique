package com.gigaboutique.gigabatchnotifyservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gigaboutique.gigabatchnotifyservice.dto.RegisterDto;
import com.gigaboutique.gigabatchnotifyservice.dto.UtilisateurDto;

@FeignClient(name = "giga-userservice", url = "localhost:8001")
public interface UtilisateurProxy {

	@GetMapping("/utilisateur/getall")
	public List<UtilisateurDto> getUtilisateurs();

	@PostMapping("/utilisateur/signup")
	public UtilisateurDto signUp(@RequestBody RegisterDto registerDto);

	@PostMapping("/login/utilisateur")
	public void login();

	@PostMapping("/panier/add")
	public void addProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur);

	@DeleteMapping("/panier/remove")
	public void removeProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur);

}
