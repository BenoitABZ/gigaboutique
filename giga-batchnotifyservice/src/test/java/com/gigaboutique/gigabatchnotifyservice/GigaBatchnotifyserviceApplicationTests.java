package com.gigaboutique.gigabatchnotifyservice;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

	UtilisateurDto utilisateurDto;

	@Test
	void contextLoads() {
	}

	@Test
	public void notifyUsersTest1() throws BatchNotifyException {

		RegisterDto registerDto = new RegisterDto();

		registerDto.setNom("ABOUZEID");
		registerDto.setPrenom("Benoit");
		registerDto.setMail("benoit.abouzeid@gmail.com");
		registerDto.setPassword("Poiuytreza31");

		UtilisateurDto utilisateurDto = utilisateurProxy.signUp(registerDto);

		int idUtilisateur = utilisateurDto.getIdUtilisateur();
		int idProduit = 500;

		utilisateurProxy.addProduit(idProduit, idUtilisateur);

		boolean bool = notifyUsersService.notifyUsers();

		assertTrue(bool);

	}

	@Test
	public void notifyUsersTest2() throws BatchNotifyException {

		boolean bool = notifyUsersService.notifyUsers();

		assertFalse(bool);

	}

}
