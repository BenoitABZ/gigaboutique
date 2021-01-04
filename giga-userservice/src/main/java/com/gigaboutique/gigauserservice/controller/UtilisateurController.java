package com.gigaboutique.gigauserservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public UtilisateurDto signUp(@RequestBody RegisterDto registerDto) {

		UtilisateurDto utilisateurDto = new UtilisateurDto();

		try {
			utilisateurDto = utilisateurService.registerUtilisateur(registerDto, roleService);
		} catch (UtilisateurException e) {
			
			utilisateurDto.setMessage(e.getMessage());
			
			System.out.println("ici");
			
			return utilisateurDto;
		}
		
		System.out.println(utilisateurDto);

		//utilisateurDto.setMessage("succes de l'inscription");

		return utilisateurDto;
	}

	@GetMapping("/utilisateurs")
	public List<UtilisateurDto> getUtilisateurs() {

		return utilisateurService.getUtilisateurs();
	}

}
