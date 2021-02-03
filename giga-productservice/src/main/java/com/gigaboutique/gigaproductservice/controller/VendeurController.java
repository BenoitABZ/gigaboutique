package com.gigaboutique.gigaproductservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.service.VendeurService;

@RestController
@RequestMapping("/vendeur")
public class VendeurController {

	@Autowired
	private VendeurService vendeurService;

	@GetMapping("/add/{idvendeur}")
	@ResponseStatus(HttpStatus.CREATED)
	public void addVendeur(@PathVariable("idVendeur") int idVendeur) {

		try {
			vendeurService.addVendeur(idVendeur);
		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}
}
