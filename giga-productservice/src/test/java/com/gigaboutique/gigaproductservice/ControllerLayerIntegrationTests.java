package com.gigaboutique.gigaproductservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigaproductservice.configuration.SecurityConstant;
import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.dto.CritereDto;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.model.ProduitBean;
import com.gigaboutique.gigaproductservice.model.VendeurBean;
import com.gigaboutique.gigaproductservice.service.ProduitService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ControllerLayerIntegrationTests {

	@Autowired
	SecurityConstant sc;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	VendeurDao vendeurDao;
	
	@Autowired
	ProduitDao produitDao;
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private String token;
	
	 @BeforeAll 
	 public void setUp() throws Exception {
		 
		    VendeurBean vendeurBean = new VendeurBean();
			vendeurBean.setId(1);
			vendeurDao.save(vendeurBean);

			ProduitDto produitDto1 = new ProduitDto();
			produitDto1.setCategorie("jean");
			produitDto1.setGenre("homme");
			produitDto1.setIdVendeur(vendeurBean.getId());
			produitDto1.setMarque("tommy");
			produitDto1.setNom("pantalon tommy 31");
			produitDto1.setPrix("84");
			produitDto1.setPromotion("50");
			produitDto1.setAdresseWeb("adresseWebTest");

			List<String> adressesWebImages1 = new ArrayList<>();
			adressesWebImages1.add("adressetest");

			produitDto1.setImages(adressesWebImages1);

			Map<String, Boolean> mapTaillesDispos1 = new HashMap<>();
			mapTaillesDispos1.put("44", true);

			produitDto1.setTailles(mapTaillesDispos1);

			produitService.addProduit(produitDto1);

			ProduitDto produitDto2 = new ProduitDto();
			produitDto2.setCategorie("pulls/sweats");
			produitDto2.setGenre("homme");
			produitDto2.setIdVendeur(vendeurBean.getId());
			produitDto2.setMarque("nike");
			produitDto2.setNom("pullover femme nike");
			produitDto2.setPrix("120.5");
			produitDto2.setPromotion("PROMO 60");
			produitDto2.setAdresseWeb("adresseWebTest");

			List<String> adressesWebImages2 = new ArrayList<>();
			adressesWebImages2.add("adressetest");

			produitDto2.setImages(adressesWebImages2);

			Map<String, Boolean> mapTaillesDispos2 = new HashMap<>();
			mapTaillesDispos2.put("XS", false);
			mapTaillesDispos2.put("S", true);

			produitDto2.setTailles(mapTaillesDispos2);

			produitService.addProduit(produitDto2);

			long expiration = Long.parseLong(sc.getExpiration());

			String secret = sc.getSecret();

			List<String> roles = new ArrayList<>();

			roles.add("USER");
			
			token = Jwts.builder().setSubject("test")
					.setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes()).claim("roles", roles)
					.compact();
		 
	 }

	@Test
	public void getProduits() throws Exception {
				
		MultiValueMap<String, String> paramsAdd = new LinkedMultiValueMap<>();

		paramsAdd.add("page", "0");
		paramsAdd.add("size", "1");

		mvc.perform(get("/produit/getall")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.params(paramsAdd)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$[0].nom", is("pantalon tommy 31")));
	}
	
	@Test
	public void getProduitsByCriteria() throws Exception {
		
		List<String> listeMarque = new ArrayList<>();
		listeMarque.add("TOMMY");
		listeMarque.add("NIKE");
		
		List<String> listeCategorie = new ArrayList<>();
		listeCategorie.add("Pantalons/Jeans");
		listeCategorie.add("Pulls/Sweats");
			
		CritereDto critereDto = new CritereDto();

		critereDto.setGenre("Homme");
		critereDto.setMarques(listeMarque);
		critereDto.setCategories(listeCategorie);
		critereDto.setPage(0);
		critereDto.setSize(2);
		
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String critereJson = ow.writeValueAsString(critereDto);

		mvc.perform(post("/produit/getcriteria")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.content(critereJson)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void getMarques() throws Exception {
		
		mvc.perform(get("/produit/getmarques")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$", hasSize(2)));
		
	}
	
	@Test
	public void setFalse() throws Exception {
		
		mvc.perform(put("/produit/setfalse")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().is2xxSuccessful());
		
		List<ProduitBean> produitsTest1 = produitDao.findAll();
		
		assertFalse(produitsTest1.get(0).getMaj());
		
		mvc.perform(delete("/produit/removeiffalse")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().is2xxSuccessful());
		
		List<ProduitBean> produitsTest2 = produitDao.findAll();
		
		assertTrue(produitsTest2.isEmpty());
		
	}
}
