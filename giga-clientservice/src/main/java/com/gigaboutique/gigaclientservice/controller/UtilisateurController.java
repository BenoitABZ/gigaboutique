package com.gigaboutique.gigaclientservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaclientservice.configuration.SecurityConstant;
import com.gigaboutique.gigaclientservice.dto.ProduitDto;
import com.gigaboutique.gigaclientservice.dto.RegisterDto;
import com.gigaboutique.gigaclientservice.service.HeartService;
import com.gigaboutique.gigaclientservice.service.ProduitService;
import com.gigaboutique.gigaclientservice.service.TokenService;
import com.gigaboutique.gigaclientservice.service.UtilisateurService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Controller
public class UtilisateurController {

	@Autowired
	UtilisateurService utilisateurService;

	@Autowired
	TokenService tokenService;
	
	@Autowired
	HeartService heartService;

	@Autowired
	ProduitService produitService;

	@Autowired
	SecurityConstant sc;
	
	@GetMapping(path = "/inscription")
	public String inscription(Model model) {

			return "inscription";		
	}
	
	@GetMapping(path = "/logout")
	public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {

		try {

			String token = tokenService.getToken(request);
			
			System.out.println(token);

            tokenService.deleteToken(request, response, token);

		    return "connexion";

		} catch (NullPointerException npe) {

			return "connexion";
		}		
	}

	@GetMapping(path = "/")
	public String getHome(Model model, HttpServletRequest request) {

		try {

			String token = tokenService.getToken(request);
			
			System.out.println(token);

			if (token != null) {

				Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes())
						.parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

				int idUtilisateur = (int) claims.get("id");

				List<ProduitDto> produitsDtoFromUser = produitService.getProduitsPanier(token, idUtilisateur);

				int size = produitsDtoFromUser.size();

				model.addAttribute("numberOfItems", size);

				return "index";

			} else {

				return "connexion";
			}

		} catch (NullPointerException npe) {

			return "connexion";
		}
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String login(Model model, @RequestParam MultiValueMap<String, String> params, HttpServletResponse response) {

		String mail = params.getFirst("mail");
		String mdp = params.getFirst("motDePasse");
		String message = null;

		try {

			String token = utilisateurService.login(mail, mdp, response);
			
			System.out.println(token);

			if (token != null) {

				message = "authentification réussie";

				Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

				int idUtilisateur = (int) claims.get("id");

				List<ProduitDto> produitsDtoFromUser = produitService.getProduitsPanier(token, idUtilisateur);

				int size = produitsDtoFromUser.size();

				model.addAttribute("numberOfItems", size);
				model.addAttribute("message", message);

				return "index";

			} else {

				message = "authentification échec";

				model.addAttribute("message", message);

				return "Connexion";
			}

		} catch (ResponseStatusException rse) {

			message = rse.getMessage();

			model.addAttribute("message", message);

			return "connexion";

		}
	}

	@PostMapping(path = "/signup", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
	public String signUp(Model model, @ModelAttribute RegisterDto registerDto, HttpServletResponse response) {

		String message = null;

		try {

			boolean bool = utilisateurService.signUp(registerDto);

			if (bool == true) {

				message = "inscription réussie";

				model.addAttribute("message", message);

				return "connexion";

			} else {

				message = "inscription échec";

				model.addAttribute("message", message);

				return "inscription";
			}

		} catch (ResponseStatusException rse) {

			message = rse.getMessage();

			model.addAttribute("message", message);

			return "inscription";

		}
	}
}
