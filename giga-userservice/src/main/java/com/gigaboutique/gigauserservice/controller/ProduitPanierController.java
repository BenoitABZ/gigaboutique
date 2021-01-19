package com.gigaboutique.gigauserservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;

@RestController
@RequestMapping("/panier")
public class ProduitPanierController {

	@Autowired
	private ProduitPanierService produitService;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> addProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur) {

		try {
			produitService.addProduitPanier(idProduit, idUtilisateur);
			
		} catch (TechnicalException e) {
			
			return new ResponseEntity<String>("problème lors de l'ajout d'un produit", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/remove")
	@ResponseStatus(HttpStatus.OK)
	public void removeProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur) {

		produitService.removeProduitPanier(idProduit, idUtilisateur);
	}

}
