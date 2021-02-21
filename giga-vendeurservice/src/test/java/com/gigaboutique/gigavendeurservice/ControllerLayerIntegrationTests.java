package com.gigaboutique.gigavendeurservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigavendeurservice.configuration.SecurityConstant;
import com.gigaboutique.gigavendeurservice.dao.VendeurDao;
import com.gigaboutique.gigavendeurservice.dto.CommentaireDto;
import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.model.CommentaireBean;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ControllerLayerIntegrationTests {

	@Autowired
	private SecurityConstant sc;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	VendeurDao vendeurDao;

	private String token;
	
	private VendeurDto vendeur;

	@BeforeAll
	public void setUp() throws Exception {
		
		vendeur = new VendeurDto();
		vendeur.setNom("vendeur");
		vendeur.setLogo("adresseLogo");
		vendeur.setNote("4");
		vendeur.setNombreDeCommentaires("42");

		List<CommentaireDto> commentaires = new ArrayList<>();

		CommentaireDto commentaire1 = new CommentaireDto();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire("2013-09-07T13:37:00");

		commentaires.add(commentaire1);

		CommentaireDto commentaire2 = new CommentaireDto();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire("2015-09-07T13:38:00");

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		long expiration = Long.parseLong(sc.getExpiration());

		String secret = sc.getSecret();

		List<String> roles = new ArrayList<>();

		roles.add("USER");

		token = Jwts.builder().setSubject("test")
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.claim("roles", roles).compact();

	}
	
	@Test
	public void addVendeur() throws Exception {
		
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String vendeurJson = ow.writeValueAsString(vendeur);
		
		mvc.perform(post("/vendeur/add")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.content(vendeurJson)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.id", notNullValue()))
		        .andExpect(jsonPath("$.commentaires", hasSize(2)))
		        .andExpect(status().isCreated());
				
	}
	
	@Test
	public void getVendeur() throws Exception {
		
		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setNom("vendeur");
		vendeurBean.setLogo("adresseLogo");
		vendeurBean.setNote("5");

		List<CommentaireBean> commentaires1 = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeurBean);

		commentaires1.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeurBean);

		commentaires1.add(commentaire2);

		vendeurBean.setCommentaires(commentaires1);

		vendeurDao.save(vendeurBean);

       int id = vendeurBean.getId();
		
		mvc.perform(get("/vendeur/get/" + id)
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.id", is(id)))
		        .andExpect(status().is2xxSuccessful());
				
	}

}
