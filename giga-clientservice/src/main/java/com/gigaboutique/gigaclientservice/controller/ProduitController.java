package com.gigaboutique.gigaclientservice.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gigaboutique.gigaclientservice.configuration.SecurityConstant;
import com.gigaboutique.gigaclientservice.dto.CommentaireDto;
import com.gigaboutique.gigaclientservice.dto.CritereDto;
import com.gigaboutique.gigaclientservice.dto.FormatReview;
import com.gigaboutique.gigaclientservice.dto.ProduitDto;
import com.gigaboutique.gigaclientservice.dto.VendeurDto;
import com.gigaboutique.gigaclientservice.service.HeartService;
import com.gigaboutique.gigaclientservice.service.ProduitService;
import com.gigaboutique.gigaclientservice.service.TokenService;
import com.gigaboutique.gigaclientservice.service.VendeurService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Controller
public class ProduitController {

	@Autowired
	ProduitService produitService;

	@Autowired
	VendeurService vendeurService;

	@Autowired
	TokenService tokenService;
	
	@Autowired
	HeartService heartService;

	@Autowired
	SecurityConstant sc;

	@GetMapping(path = "/getproduits")
	public String getProduits(Model model, HttpServletRequest request) {

		String token = tokenService.getToken(request);
		
		HttpSession session = request.getSession();
		
		//String token = request.getHeader("Authorization");

		String page = "0";
		String size = "15";
		int numberOfItems = 0;
		int counter = 0;
		boolean less = false;
		boolean more = true;
		
		if (token != null) {
			
			session.setAttribute("page", Integer.parseInt(page));
			session.setAttribute("counter", counter);

			List<String> categories = produitService.getCategories(token);

			List<String> marquesList = produitService.getMarques(token);			
			Set<String> marques = toSet(marquesList);

			List<String> genres = new ArrayList<>();
			genres.add("Homme");
			genres.add("Femme");

			CritereDto critereDto = new CritereDto();
			
			session.setAttribute("critereDto", null);

			List<ProduitDto> produitsDto = produitService.getProduits(token, page, size);
			
			numberOfItems = heartService.setHeart(produitsDto, token);

			model.addAttribute("categories", categories);			
			model.addAttribute("marques", marques);
			model.addAttribute("genres", genres);
			model.addAttribute("critereDto", critereDto);
			model.addAttribute("numberOfItems", numberOfItems);
			model.addAttribute("less", less);
			model.addAttribute("more", more);
			model.addAttribute("produitsDto", produitsDto);

			return "products-list";

		} else {

			return "connexion";
		}

	}
	
	@GetMapping(path = "/getpaginatedproduits")
	public String getProduitsNext(Model model, HttpServletRequest request, @RequestParam("pagination") String pagination) {

		String token = tokenService.getToken(request);

		List<ProduitDto> produitsDto = new ArrayList<>();
		
		HttpSession session = request.getSession();
		
		//String token = request.getHeader("Authorization");

		int page = (int) session.getAttribute("page");
		
		int counter = (int) session.getAttribute("counter");
		boolean less = true;
		boolean more = true;
			
		if(pagination.equals("next")) {
		
		page++;
		
		counter ++;
		
		}else {
			
		page--;
		
		counter--;
		
		if (counter <= 0) {
			
			less = false;
		}

			
		}
		
		String size = "15";
		int sizeInt = 15;
		
		int numberOfItems = 0;
		
		if (token != null) {
			
			String newPage = String.valueOf(page);
			
			session.setAttribute("page", page);
			session.setAttribute("counter", counter);

			List<String> categories = produitService.getCategories(token);

			List<String> marques = produitService.getMarques(token);

			List<String> genres = new ArrayList<>();
			genres.add("Homme");
			genres.add("Femme");

			CritereDto critereDto = (CritereDto) session.getAttribute("critereDto");
			
			if(critereDto != null) {
				
				critereDto.setPage(page);
				critereDto.setSize(sizeInt);
				
				produitsDto = produitService.getProduitsByCriteria(token, critereDto);
				
				
			}else {
				
				critereDto = new CritereDto();
				
				produitsDto = produitService.getProduits(token, newPage, size);
			}

			
			
			if(produitsDto.size() < sizeInt) {
				
				more = false;
			}
						
			numberOfItems = heartService.setHeart(produitsDto, token);

			model.addAttribute("categories", categories);
			model.addAttribute("marques", marques);
			model.addAttribute("genres", genres);
			model.addAttribute("critereDto", critereDto);
			model.addAttribute("numberOfItems", numberOfItems);
			model.addAttribute("less", less);
			model.addAttribute("more", more);
			model.addAttribute("produitsDto", produitsDto);

			return "products-list";

		} else {

			return "connexion";
		}

	}

	@PostMapping(path = "/getproduitscriteria")
	public String getPaginatedProduitsCriteria(Model model, @ModelAttribute("critereDto") CritereDto critereDto, HttpServletRequest request) {


		String token = tokenService.getToken(request);
		
		HttpSession session = request.getSession();
		
		//String token = request.getHeader("Authorization");

		String page = "0";
		String size = "15";
		int sizeInt = 15;
		int numberOfItems = 0;
		int counter = 0;
		boolean less = false;
		boolean more = true;
		
		if (token != null) {
			
			session.setAttribute("page", Integer.parseInt(page));
			session.setAttribute("counter", counter);

			List<String> categories = produitService.getCategories(token);

			List<String> marquesList = produitService.getMarques(token);			
			Set<String> marques = toSet(marquesList);

			List<String> genres = new ArrayList<>();
			genres.add("Homme");
			genres.add("Femme");
			
			if(critereDto.getCategorie().equals("")) {
				
				critereDto.setCategorie(null);
			}
			
            if(critereDto.getGenre().equals("")) {
				
				critereDto.setGenre(null);
			}
            
            if(critereDto.getMarque().equals("")) {
				
				critereDto.setMarque(null);
			}
			
			critereDto.setPage(Integer.parseInt(page));
			critereDto.setSize(Integer.parseInt(size));
			
			session.setAttribute("critereDto", critereDto);

			List<ProduitDto> produitsDto = produitService.getProduitsByCriteria(token, critereDto);
			
            if(produitsDto != null && produitsDto.size() < sizeInt) {
				
				more = false;
				
				numberOfItems = heartService.setHeart(produitsDto, token);			
			}
			
            if(produitsDto == null) {
            	
            more = false;
            
            }

			model.addAttribute("categories", categories);			
			model.addAttribute("marques", marques);
			model.addAttribute("genres", genres);
			model.addAttribute("critereDto", critereDto);
			model.addAttribute("numberOfItems", numberOfItems);
			model.addAttribute("less", less);
			model.addAttribute("more", more);
			model.addAttribute("produitsDto", produitsDto);

			return "products-list";

		} else {

			return "connexion";
		}
	}

	@GetMapping(path = "/getproduit/{id}")
	public String getProduit(Model model, @PathVariable("id") int idProduit, HttpServletRequest request) {

		String token = tokenService.getToken(request);
		
		List<ProduitDto> produitsDto = new ArrayList<>();

		if (token != null) {

			ProduitDto produitDto = produitService.getProduit(token, idProduit);
			
			produitsDto.add(produitDto);

			VendeurDto vendeurDto = vendeurService.getVendeur(token, produitDto.getIdVendeur());
			
			System.out.println(vendeurDto.getLogo());
			
			int numberOfItems = heartService.setHeart(produitsDto, token);
			
			boolean heart = produitDto.isHeart();
			
			produitDto = produitsDto.get(0);
			
			List<CommentaireDto> commentaires= vendeurDto.getCommentaires();			
			List<CommentaireDto> threeLastComments = commentaires.subList(0, 3);
			
			List<FormatReview> reviews = new ArrayList<>();
			
			for(CommentaireDto commentaire: threeLastComments) {
				
				FormatReview formatReview = new FormatReview();
				
				formatReview.setAuteur(commentaire.getAuteur());
				formatReview.setDateCommentaire(commentaire.getDateCommentaire());
				formatReview.setDescription(commentaire.getDescription());
				formatReview.setNote(Integer.parseInt(commentaire.getNote()));
				
				reviews.add(formatReview);
				
				System.out.println(formatReview.getDescription());
			}
			
			
			model.addAttribute("heart", heart);
			model.addAttribute("commentaires", reviews);
			model.addAttribute("numberOfItems", numberOfItems);
			model.addAttribute("produitDto", produitDto);
			model.addAttribute("vendeurDto", vendeurDto);

			return "product-detail";

		} else {

			return "connexion";
	}

	}

	@GetMapping(path = "/getproduitspanier")
	public String getProduitPanier(Model model, HttpServletRequest request) {

		String token = tokenService.getToken(request);

		if (token != null) {

			Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

			int idUtilisateur = (int) claims.get("id");

			List<ProduitDto> produitsDto = produitService.getProduitsPanier(token, idUtilisateur);

			model.addAttribute("produitsDto", produitsDto);

			return "shoping-cart";

		} else {

			return "connexion";
		}
	}
	
	@GetMapping(path = "/addproduitpanier")
	public ResponseEntity<String> addProduitPanier(HttpServletRequest request, @RequestParam("id") int idProduit) {

		String token = tokenService.getToken(request);
		
		//String token = request.getHeader("Authorization");

		if (token != null) {

			Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

			int idUtilisateur = (int) claims.get("id");

			produitService.addProduitPanier(token, idProduit,idUtilisateur);
			
			return new ResponseEntity<String>(HttpStatus.OK);


		} 
		
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
	
	@GetMapping(path = "/removeproduitpanier")
	public ResponseEntity<String> removeProduitPanier(HttpServletRequest request, @RequestParam("id") int idProduit) {

		String token = tokenService.getToken(request);
		
		//String token = request.getHeader("Authorization");

		if (token != null) {

			Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

			int idUtilisateur = (int) claims.get("id");

			produitService.removeProduitPanier(token, idProduit,idUtilisateur);
			
			return new ResponseEntity<String>(HttpStatus.OK);


		} 
		
		return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	}
	
	private Set<String> toSet(List<String> produitsList) {
		
		Set<String> produitsSet = new HashSet<>();
		
		for(String produitDto : produitsList) {
			
			produitsSet.add(produitDto);
		}
		
		return produitsSet;		
	}
}
