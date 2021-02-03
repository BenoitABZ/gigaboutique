package com.gigaboutique.gigaproductservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.service.CategorieService;

@RestController
@RequestMapping("/categorie")
public class CategorieController {

	@Autowired
	private CategorieService categorieService;

	@GetMapping("/get/all")
	public List<String> getCategories() {

		List<String> categories = new ArrayList<>();

		try {

			categories = categorieService.getCategories();

		} catch (TechnicalException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}

		return categories;

	}

}
