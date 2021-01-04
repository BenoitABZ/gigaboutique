package com.gigaboutique.gigauserservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigauserservice.dto.RegisterDto;

@SpringBootTest
//@TestPropertySource(locations = { "classpath:application-test.properties" })
@WithMockUser(username = "utilisateur", password = "mdp", roles = "USER")
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
public class UtilisateurControllerIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void signUpTest() throws Exception {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("lastNameTest");
		registerDto.setPrenom("firstNameTest");
		registerDto.setMail("admin@gigaboutique.fr");
		registerDto.setPassword("Poiuytreza31");
		
		ObjectMapper objectMapper = new ObjectMapper();

		// Creating the ObjectMapper object
		ObjectWriter mapper = objectMapper.writer().withDefaultPrettyPrinter();
		// Converting the Object to JSONString
		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/signup/utilisateur").contentType(MediaType.APPLICATION_JSON).content(registerDtoJson))
				.andExpect(status().is2xxSuccessful());
	}

}
