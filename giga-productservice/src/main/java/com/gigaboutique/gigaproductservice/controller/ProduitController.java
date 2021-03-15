package com.gigaboutique.gigaproductservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaproductservice.dto.CritereDto;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.exception.ProduitException;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.service.ProduitService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	private ProduitService produitService;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void addProduit(@RequestBody ProduitDto produitDto) {

		try {

			produitService.addProduit(produitDto);

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		} catch (ProduitException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}

	@GetMapping("/getall")
	public List<ProduitDto> getProduits(@RequestParam(defaultValue = "0") String page, @RequestParam(defaultValue = "15") String size) {

		int pageInt = Integer.parseInt(page);
		int sizeInt = Integer.parseInt(size);
		
		Pageable paging = PageRequest.of(pageInt, sizeInt);

		List<ProduitDto> produits = null;

		try {

			produits = produitService.getProduits(paging);

		} catch (ProduitException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		} catch (NotFoundException e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}

		return produits;

	}

	@PostMapping("/getcriteria")
	public List<ProduitDto> getProduitsCriteria(@RequestBody CritereDto critereDto) {

		int page = critereDto.getPage();
		int size = critereDto.getSize();

		Pageable paging = PageRequest.of(page, size);

		List<ProduitDto> produits = null;

		try {

			produits = produitService.getProduitsByCriteria(critereDto, paging);

		} catch (ProduitException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		} catch (NotFoundException e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}

		return produits;

	}

	@GetMapping("/getmarques")
	public List<String> getMarques() {

		List<String> marques = new ArrayList<>();

		marques = produitService.getMarques();

		return marques;

	}

	@GetMapping("/get/{nom}")
	public ProduitDto getProduitByNom(@PathVariable("nom") String nom) {

		ProduitDto produit = null;

		try {

			produit = produitService.getProduitByName(nom);

		} catch (ProduitException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		} catch (NotFoundException e) {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);

		}

		return produit;

	}

	@PutMapping("/setfalse")
	public void setFalse() {

		try {

			produitService.setMajFalse();

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

	}

	@DeleteMapping("/removeiffalse")
	public void removeFalse() {

		try {

			produitService.removeProduitIfFalse();

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}
