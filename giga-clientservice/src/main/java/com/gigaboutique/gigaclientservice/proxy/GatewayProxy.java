package com.gigaboutique.gigaclientservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.gigaboutique.gigaclientservice.dto.CritereDto;
import com.gigaboutique.gigaclientservice.dto.ProduitDto;
import com.gigaboutique.gigaclientservice.dto.RegisterDto;
import com.gigaboutique.gigaclientservice.dto.UtilisateurDto;
import com.gigaboutique.gigaclientservice.dto.VendeurDto;

@FeignClient(name = "giga-gateway", url = "localhost:8080")
public interface GatewayProxy {
	
	@GetMapping("/utilisateur/panier/get/{idUtilisateur}")
	public UtilisateurDto getProduitsPanier(@RequestHeader("Authorization") String token, @PathVariable("idUtilisateur") int idUtilisateur);
	
	@PostMapping("/utilisateur/panier/add")
	public void addProduit(@RequestHeader("Authorization") String token, @RequestParam int idProduit, @RequestParam int idUtilisateur);
	
	@DeleteMapping("/utilisateur/panier/remove")
	public void removeProduit(@RequestHeader("Authorization") String token, @RequestParam int idProduit, @RequestParam int idUtilisateur);
	
	@PostMapping("/utilisateur/signup")
	public UtilisateurDto signUp(@RequestBody RegisterDto registerDto);
	
	@PostMapping(path = "/utilisateur/login")
	public ResponseEntity<String> login(@RequestParam String mail, @RequestParam String motDePasse);

	@GetMapping("/produit/getall")
	public List<ProduitDto> getProduits(@RequestHeader("Authorization") String token, @RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "15") String size);

	@PostMapping("/produit/getcriteria")
	public List<ProduitDto> getProduitsCriteria(@RequestHeader("Authorization") String token, @RequestBody CritereDto critereDto);
	
	@GetMapping("/produit/getmarques")
	public List<String> getMarques(@RequestHeader("Authorization") String token);
	
	@GetMapping("/produit/getid/{id}")
	public ProduitDto getProduitById(@RequestHeader("Authorization") String token, @PathVariable("id") int id);
	
	@GetMapping("/produit/categorie/getall")
	public List<String> getCategories(@RequestHeader("Authorization") String token);
	
	@GetMapping("/vendeur/get/{idVendeur}")
	public VendeurDto getVendeur(@RequestHeader("Authorization") String token, @PathVariable("idVendeur") int idVendeur);
}
