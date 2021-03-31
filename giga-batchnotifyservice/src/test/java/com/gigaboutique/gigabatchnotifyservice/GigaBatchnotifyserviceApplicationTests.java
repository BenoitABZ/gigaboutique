package com.gigaboutique.gigabatchnotifyservice;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigabatchnotifyservice.dto.RegisterDto;
import com.gigaboutique.gigabatchnotifyservice.dto.UtilisateurDto;
import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;
import com.gigaboutique.gigabatchnotifyservice.proxy.UtilisateurProxy;
import com.gigaboutique.gigabatchnotifyservice.service.NotifyUsersService;

@SpringBootTest
class GigaBatchnotifyserviceApplicationTests {

	@Autowired
	UtilisateurProxy utilisateurProxy;

	@Autowired
	NotifyUsersService notifyUsersService;

	@Test
	void contextLoads() {
	}

	@Test
	public void notifyUsersTest() throws BatchNotifyException {
		
		RegisterDto registerDto = new RegisterDto();
		
		registerDto.setNom("ABOU");
		registerDto.setPrenom("Benoit");
		registerDto.setMail("mail.adm@gmail.com");
		registerDto.setPassword("Poiuytreza31");
		
		UtilisateurDto utilisateurDto = utilisateurProxy.signUp(registerDto);

		RegisterDto registerDto2 = new RegisterDto();

		registerDto2.setNom("ABOUZEID");
		registerDto2.setPrenom("Benoit");
		registerDto2.setMail("benoit.abouzeid@gmail.com");
		registerDto2.setPassword("Poiuytreza31");

		UtilisateurDto utilisateurDto2 = utilisateurProxy.signUp(registerDto2);

		int idUtilisateur = utilisateurDto2.getIdUtilisateur();
		int idProduit = 500;

		utilisateurProxy.addProduit(idProduit, idUtilisateur);

		boolean bool = notifyUsersService.notifyUsers();

		assertTrue(bool);

	}

}
