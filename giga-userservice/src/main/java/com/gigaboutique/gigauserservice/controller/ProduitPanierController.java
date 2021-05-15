package com.gigaboutique.gigauserservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;

@RestController
@RequestMapping("/utilisateur")
public class ProduitPanierController {

	@Autowired
	private ProduitPanierService produitService;

	@GetMapping("/panier/get/{idUtilisateur}")
	@ResponseStatus(HttpStatus.OK)
	public UtilisateurDto getProduits(@PathVariable("idUtilisateur") int idUtilisateur) {

		UtilisateurDto utilisateurDto = null;

		try {

			utilisateurDto = produitService.getProduits(idUtilisateur);

		} catch (UtilisateurException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

		return utilisateurDto;
	}

	@PostMapping("/panier/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur) {

		try {

			produitService.addProduitPanier(idProduit, idUtilisateur);

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}

	@DeleteMapping("/panier/remove")
	@ResponseStatus(HttpStatus.OK)
	public void removeProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur) {

		try {

			produitService.removeProduitPanier(idProduit, idUtilisateur);

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
