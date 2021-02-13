package com.gigaboutique.gigauserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.service.RoleService;
import com.gigaboutique.gigauserservice.service.UtilisateurService;

@RestController
public class UtilisateurController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UtilisateurService utilisateurService;

	@PostMapping("/signup/utilisateur")
	@ResponseStatus(HttpStatus.CREATED)
	public UtilisateurDto signUp(@RequestBody RegisterDto registerDto) {

		UtilisateurDto utilisateurDto = new UtilisateurDto();

		try {

			utilisateurDto = utilisateurService.registerUtilisateur(registerDto, roleService);

		} catch (UtilisateurException e) {

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);

		}

		return utilisateurDto;
	}

	@GetMapping("/utilisateurs")
	public List<UtilisateurDto> getUtilisateurs() {

		return utilisateurService.getUtilisateurs();
	}

}
