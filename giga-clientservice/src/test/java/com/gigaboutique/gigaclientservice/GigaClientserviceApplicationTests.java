package com.gigaboutique.gigaclientservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gigaboutique.gigaclientservice.proxy.GatewayProxy;

@SpringBootTest
@Rollback(true)
@AutoConfigureMockMvc
class GigaClientserviceApplicationTests {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private GatewayProxy gatewayProxy;

	@Test
	void contextLoads() {
	}
/*		
	@Test
	public void signUpTest() throws Exception {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("ABZ");
		registerDto.setPrenom("Benoit");
		registerDto.setMail("mailrandom@gmail.com");
		registerDto.setPassword("Poiuytreza31");

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectWriter mapper = objectMapper.writer().withDefaultPrettyPrinter();
		String registerDtoJson = mapper.writeValueAsString(registerDto);

		mvc.perform(post("/signup")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(registerDtoJson))
		   .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void loginTest() throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		String mail =  "mailrandom@gmail.com";

		params.add("mail", mail);
		params.add("motDePasse", "Poiuytreza31");

		mvc.perform(post("/login")
	       .params(params)
	       .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		   .accept("application/x-wwww-form-urlencoded;charset=UTF-8"))
		   .andExpect(status().isOk());

	}
	
	@Test
	public void getProductListTest() throws Exception {
		
		String mail =  "mailrandom@gmail.com";
		String mdp = "Poiuytreza31";

		ResponseEntity<String> responseEntity = gatewayProxy.login(mail, mdp);

	    String token = responseEntity.getHeaders().getFirst("Authorization");
		
		mvc.perform(get("/getproduits")
			       .header("Authorization", token)
			       .contentType(MediaType.APPLICATION_JSON)
				   .accept("application/json;charset=UTF-8"))
				   .andExpect(status().isOk());
		
	}
	
	@Test
	public void getProductListCriteriaTest() throws Exception {
		
		String mail =  "mailrandom@gmail.com";
		String mdp = "Poiuytreza31";

		ResponseEntity<String> responseEntity = gatewayProxy.login(mail, mdp);

	    String token = responseEntity.getHeaders().getFirst("Authorization");
	    
	    //gatewayProxy.addProduit(token, 4, 1);
	    
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("genre", "Homme");
		params.add("page", "0");
				
		mvc.perform(post("/getproduitscriteria/paginated")
			       .header("Authorization", token)
			       .params(params)
			       .contentType(MediaType.APPLICATION_JSON)
				   .accept("application/json;charset=UTF-8"))
				   .andExpect(status().isOk());
		
	}	
	
	@Test
	public void getProductDetailTest() throws Exception {
		
		String mail =  "mailrandom@gmail.com";
		String mdp = "Poiuytreza31";

		ResponseEntity<String> responseEntity = gatewayProxy.login(mail, mdp);

	    String token = responseEntity.getHeaders().getFirst("Authorization");
	    
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    
	    int idProduit = 4;
	    
	    mvc.perform(get("/getproduit/"+idProduit)
			       .header("Authorization", token)
			       .contentType(MediaType.APPLICATION_JSON)
				   .accept("application/json;charset=UTF-8"))
				   .andExpect(status().isOk());
	    		
	}
*/
	
	@Test
	public void addProductListTest() throws Exception {
		
		String mail =  "mailrandom@gmail.com";
		String mdp = "Poiuytreza31";

		ResponseEntity<String> responseEntity = gatewayProxy.login(mail, mdp);

	    String token = responseEntity.getHeaders().getFirst("Authorization");
	    
	    String id = "2";
		
		mvc.perform(get("/addproduitpanier")
			       .header("Authorization", token)
			       .param("id", id)
			       .contentType(MediaType.APPLICATION_JSON)
				   .accept("application/json;charset=UTF-8"))
				   .andExpect(status().isOk());
}
}
