package com.gigaboutique.gigauserservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;

@RestController
public class ProduitPanierController {

	@Autowired
	private ProduitPanierService produitService;

	@PostMapping("/addProduitPanier")
	public UtilisateurDto addProduit(@RequestBody UtilisateurDto utilisateurDto) {

		try {
			produitService.addProduitPanier(utilisateurDto);
		} catch (TechnicalException e) {
			utilisateurDto.setMessage(e.getMessage());
		}

		utilisateurDto.setMessage("article ajouté avec succés");

		return utilisateurDto;
	}
	
	@PostMapping("/removeProduitPanier")
	public UtilisateurDto removeProduit(@RequestBody UtilisateurDto utilisateurDto) {

		try {
			produitService.removeProduitPanier(utilisateurDto);
		} catch (TechnicalException e) {
			utilisateurDto.setMessage(e.getMessage());
		}

		utilisateurDto.setMessage("article supprimé avec succés");

		return utilisateurDto;
	}

}
