package com.gigaboutique.gigauserservice;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.model.UtilisateurProduitPanierBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ProduitPanierIntegrationTests {
/*
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	private ProduitPanierDao produitPanierDao;

	UtilisateurDto utilisateurDto;
	
	ObjectWriter mapper;
	
	String token;
	
	  @BeforeAll 
	  public void authenticate() throws Exception {
		  
			RegisterDto registerDto = new RegisterDto();

			registerDto.setNom("lastNameTest");
			registerDto.setPrenom("firstNameTest");
			registerDto.setMail("mail1.adm@gmail.com");
			registerDto.setPassword("Poiuytreza3");

			ObjectMapper objectMapper = new ObjectMapper();
			mapper = objectMapper.writer().withDefaultPrettyPrinter();

			String registerDtoJson = mapper.writeValueAsString(registerDto);

			mvc.perform(post("/utilisateur/signup")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(registerDtoJson));

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

			params.add("mail", "mail1.adm@gmail.com");
			params.add("motDePasse", "Poiuytreza3");

			token = mvc.perform(post("/utilisateur/login")
					   .params(params)
					   .accept("application/json;charset=UTF-8"))
					   .andReturn().getResponse().getHeader("Authorization");
			
			System.out.println(token);

			utilisateurDto = new UtilisateurDto();

			utilisateurDto.setNom("lastNameTest");
			utilisateurDto.setPrenom("firstNameTest");
			utilisateurDto.setMail("mail1.adm@gmail.com");

			UtilisateurBean utilisateurBean = utilisateurDao.findByMail(registerDto.getMail());

			int id = utilisateurBean.getId();
			String role = utilisateurBean.getRole().getRole();

			utilisateurDto.setIdUtilisateur(id);
			utilisateurDto.setRole(role);			
	 }

	@Test
	public void addProduitPanierTest() throws Exception {

		int idProduitToAdd = 1;
		int idUtilisateur = utilisateurDto.getIdUtilisateur();

		MultiValueMap<String, String> paramsAdd = new LinkedMultiValueMap<>();

		paramsAdd.add("idProduit", Integer.toString(idProduitToAdd));
		paramsAdd.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token)
		   .params(paramsAdd)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());
		
		assertTrue(produitPanierDao.findById(idProduitToAdd) != null);
		
		UtilisateurBean utilisateurBean = utilisateurDao.findById(idUtilisateur);
		
		UtilisateurDto utilisateurDtoTest = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateurBean);
		
		assertEquals(1, utilisateurDtoTest.getProduits().size());
	}

	@Test
	public void removeProduitPanierTest() throws Exception {
		
		int idProduitToAdd = 1;
		int idUtilisateur = utilisateurDto.getIdUtilisateur();

		MultiValueMap<String, String> paramsAdd = new LinkedMultiValueMap<>();

		paramsAdd.add("idProduit", Integer.toString(idProduitToAdd));
		paramsAdd.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token)
		   .params(paramsAdd)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());

		int idProduitToRemove = 1;

		MultiValueMap<String, String> paramsRemove = new LinkedMultiValueMap<>();

		paramsRemove.add("idProduit", Integer.toString(idProduitToRemove));
		paramsRemove.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(delete("/panier/remove")
		   .header("Authorization", token)
		   .params(paramsRemove)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk());
		
		assertFalse(produitPanierDao.findById(idProduitToRemove) != null);
	}

	@Test
	public void removeProduitPanierTestWithMoreThanOneUserTest() throws Exception {
		
		RegisterDto registerDto2 = new RegisterDto();

		registerDto2.setNom("lastNameTest2");
		registerDto2.setPrenom("firstNameTest2");
		registerDto2.setMail("mail2.adm@gmail.com");
		registerDto2.setPassword("Poiuytreza3");

		ObjectMapper objectMapper = new ObjectMapper();
		mapper = objectMapper.writer().withDefaultPrettyPrinter();
		String registerDtoJson2 = mapper.writeValueAsString(registerDto2);

		mvc.perform(post("/utilisateur/signup")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(registerDtoJson2));

		MultiValueMap<String, String> params2 = new LinkedMultiValueMap<>();

		params2.add("mail", "mail2.adm@gmail.com");
		params2.add("motDePasse", "Poiuytreza3");

		String token2 = mvc.perform(post("/utilisateur/login")
				           .params(params2)
				           .accept("application/json;charset=UTF-8"))
				           .andReturn().getResponse()
				           .getHeader("Authorization");
	
		UtilisateurDto utilisateurDto2 = new UtilisateurDto();

		utilisateurDto2.setNom("lastNameTest2");
		utilisateurDto2.setPrenom("firstNameTest2");
		utilisateurDto2.setMail("mail2.adm@gmail.com");
		
		UtilisateurBean utilisateurBean2 = utilisateurDao.findByMail(registerDto2.getMail());

		int id2 = utilisateurBean2.getId();
		String role2 = utilisateurBean2.getRole().getRole();

		utilisateurDto2.setIdUtilisateur(id2);
		utilisateurDto2.setRole(role2);
		
		int idProduitToAdd = 1;
		int idUtilisateur = utilisateurDto.getIdUtilisateur();

		MultiValueMap<String, String> paramsAdd = new LinkedMultiValueMap<>();

		paramsAdd.add("idProduit", Integer.toString(idProduitToAdd));
		paramsAdd.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token)
		   .params(paramsAdd)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());

		int idUtilisateur2 = utilisateurDto2.getIdUtilisateur();

		MultiValueMap<String, String> paramsAdd2 = new LinkedMultiValueMap<>();

		paramsAdd2.add("idProduit", Integer.toString(idProduitToAdd));
		paramsAdd2.add("idUtilisateur", Integer.toString(idUtilisateur2));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token2)
		   .params(paramsAdd2)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());
		
		int idProduitToRemove = 1;

		MultiValueMap<String, String> paramsRemove = new LinkedMultiValueMap<>();

		paramsRemove.add("idProduit", Integer.toString(idProduitToRemove));
		paramsRemove.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(delete("/panier/remove")
		   .header("Authorization", token)
		   .params(paramsRemove)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk());
		
		ProduitPanierBean produitPanier = produitPanierDao.findById(idProduitToRemove);

		assertTrue(produitPanierDao.findById(idProduitToRemove) != null);
		assertTrue(produitPanier.getUtilisateurProduits().size() == 1);
		assertTrue(((UtilisateurProduitPanierBean)produitPanier.getUtilisateurProduits().toArray()[0]).getUtilisateur().getId() == utilisateurBean2.getId());
	}
	
	@Test
	public void getProduitPanierTest() throws Exception {

		int idProduitToAdd1 = 1;
		int idUtilisateur = utilisateurDto.getIdUtilisateur();

		MultiValueMap<String, String> paramsAdd1 = new LinkedMultiValueMap<>();

		paramsAdd1.add("idProduit", Integer.toString(idProduitToAdd1));
		paramsAdd1.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token)
		   .params(paramsAdd1)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());
		
		int idProduitToAdd2 = 2;
		
		MultiValueMap<String, String> paramsAdd2 = new LinkedMultiValueMap<>();

		paramsAdd2.add("idProduit", Integer.toString(idProduitToAdd2));
		paramsAdd2.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(post("/panier/add")
		   .header("Authorization", token)
		   .params(paramsAdd2)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(status().isCreated());
		
		mvc.perform(get("/panier/get/" + idUtilisateur)
		   .header("Authorization", token)
		   .params(paramsAdd2)
		   .contentType(MediaType.APPLICATION_JSON))
		   .andExpect(jsonPath("$.produits", hasSize(2)));
	}
*/
}
