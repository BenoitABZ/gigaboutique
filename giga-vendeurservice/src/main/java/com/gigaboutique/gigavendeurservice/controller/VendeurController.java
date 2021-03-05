package com.gigaboutique.gigavendeurservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.exception.TechnicalException;
import com.gigaboutique.gigavendeurservice.exception.VendeurException;
import com.gigaboutique.gigavendeurservice.service.VendeurService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/vendeur")
public class VendeurController {

	@Autowired
	private VendeurService vendeurService;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public VendeurDto addVendeur(@RequestBody VendeurDto vendeurDto) {

		try {

			VendeurDto vendeurDtoReturned = vendeurService.addVendeur(vendeurDto);

			return vendeurDtoReturned;

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		} catch (VendeurException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}

	@GetMapping("/get/{idVendeur}")
	public VendeurDto getVendeur(@PathVariable("idVendeur") int idVendeur) {

		try {

			VendeurDto vendeurDto = vendeurService.getVendeur(idVendeur);

			return vendeurDto;

		} catch (NotFoundException | VendeurException | TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		}
	}
}
