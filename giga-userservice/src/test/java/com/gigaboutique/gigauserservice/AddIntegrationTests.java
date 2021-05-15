package com.gigaboutique.gigauserservice;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;

@SpringBootTest
@Rollback(true)
@AutoConfigureMockMvc
public class AddIntegrationTests {
/*
	@Autowired
	private MockMvc mvc;

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private ProduitPanierService produitPanierService;

	@Autowired
	private ProduitPanierDao produitPanierDao;

	UtilisateurDto utilisateurDto;

	ObjectWriter mapper;

	@Test
	public void addProduitPanierTest() throws Exception {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("ABOUZEID");
		registerDto.setPrenom("Benoit");
		registerDto.setMail("mail.adm@gmail.com");
		registerDto.setPassword("Poiuytreza31");

		ObjectMapper objectMapper = new ObjectMapper();
		mapper = objectMapper.writer().withDefaultPrettyPrinter();

		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/utilisateur/signup").contentType(MediaType.APPLICATION_JSON).content(registerDtoJson));

		UtilisateurBean utilisateurBean = utilisateurDao.findByMail(registerDto.getMail());

		int idUtilisateur = utilisateurBean.getId();
		int idProduit = 500;

		produitPanierService.addProduitPanier(idProduit, idUtilisateur);

		ProduitPanierBean produitPanierBean = produitPanierDao.findById(idProduit);

		assertEquals(500, produitPanierBean.getId());
		assertNotNull(produitPanierBean.getUtilisateurProduits().toArray());
	}
*/
}
