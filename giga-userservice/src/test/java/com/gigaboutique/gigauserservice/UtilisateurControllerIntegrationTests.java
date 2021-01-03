package com.gigaboutique.gigauserservice;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.service.RoleService;
import com.gigaboutique.gigauserservice.service.UtilisateurService;

@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
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

		// Creating the ObjectMapper object
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/signUp").contentType(MediaType.APPLICATION_JSON).content(registerDtoJson))
				.andExpect(status().isOk());
	}

}
