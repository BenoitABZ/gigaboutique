package com.gigaboutique.gigauserservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ProduitPanierIntegrationTests {

	@Autowired
	private MockMvc mvc;

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
			registerDto.setMail("mailadmin@gmail.com");
			registerDto.setPassword("Poiuytreza3");

			ObjectMapper objectMapper = new ObjectMapper();

			mapper = objectMapper.writer().withDefaultPrettyPrinter();

			String registerDtoJson = mapper.writeValueAsString(registerDto);

			mvc.perform(post("/signup/utilisateur")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(registerDtoJson));

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

			params.add("mail", "mailadmin@gmail.com");
			params.add("motDePasse", "Poiuytreza3");

			token = mvc.perform(post("/login/utilisateur")
					   .params(params)
					   .accept("application/json;charset=UTF-8"))
					   .andReturn().getResponse().getHeader("Authorization");
			
			System.out.println(token);

			utilisateurDto = new UtilisateurDto();

			utilisateurDto.setNom("lastNameTest");
			utilisateurDto.setPrenom("firstNameTest");
			utilisateurDto.setMail("mailadmin@gmail.com");

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
		   .contentType(MediaType.APPLICATION_JSON)
		   .params(paramsAdd)
		   .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
		
		assertTrue(produitPanierDao.findById(idProduitToAdd) != null);
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
		   .contentType(MediaType.APPLICATION_JSON)
		   .params(paramsAdd)
		   .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		int idProduitToRemove = 1;

		MultiValueMap<String, String> paramsRemove = new LinkedMultiValueMap<>();

		paramsRemove.add("idProduit", Integer.toString(idProduitToRemove));
		paramsRemove.add("idUtilisateur", Integer.toString(idUtilisateur));

		mvc.perform(delete("/panier/remove")
		   .header("Authorization", token)
		   .params(paramsRemove)
		   .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		assertFalse(produitPanierDao.findById(idProduitToRemove) != null);
	}

}
