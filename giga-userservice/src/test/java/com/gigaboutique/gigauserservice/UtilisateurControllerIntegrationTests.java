package com.gigaboutique.gigauserservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
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
import com.gigaboutique.gigauserservice.dto.RegisterDto;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
public class UtilisateurControllerIntegrationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void signUpTest() throws Exception {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("ABOUZEID");
		registerDto.setPrenom("Benoit");
		registerDto.setMail("mailtest@gmail.com");
		registerDto.setPassword("Poiuytreza31");

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectWriter mapper = objectMapper.writer().withDefaultPrettyPrinter();
		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/utilisateur/signup")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(registerDtoJson))
		   .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void loginTest() throws Exception {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("lastNameTest");
		registerDto.setPrenom("firstNameTest");
		registerDto.setMail("mailtest2.adm@gmail.com");
		registerDto.setPassword("Poiuytreza3");

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectWriter mapper = objectMapper.writer().withDefaultPrettyPrinter();

		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/utilisateur/signup")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(registerDtoJson));

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

		params.add("mail", "mailtest2.adm@gmail.com");
		params.add("motDePasse", "Poiuytreza3");

		mvc.perform(post("/utilisateur/login")
	       .params(params)
		   .accept("application/json;charset=UTF-8"))
		   .andExpect(status().isOk())
		   .andReturn()
		   .getResponse()
		   .getHeader("Authorization");
	}

}
