package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;

@ExtendWith(MockitoExtension.class)
public class MapUtilisateurDtoServiceTests {

	MapUtilisateurDtoService map;

	UtilisateurBean utilisateurBean;

	ModelMapper modelMapper;

	@BeforeEach
	public void setUp() {

		utilisateurBean = new UtilisateurBean();
		utilisateurBean.setId(1);
		utilisateurBean.setNom("testLastName");
		utilisateurBean.setPrenom("testFirstName");
		utilisateurBean.setPrenom("testFirstName");
		utilisateurBean.setMail("testAdresseMail@gigaboutique.fr");
		utilisateurBean.setMotDePasse("Poiuytreza31");

		RoleBean roleBean = new RoleBean();
		roleBean.setRole("ROLE_ADMIN");
		utilisateurBean.setRole(roleBean);

		map = new MapUtilisateurDtoService();

		modelMapper = new ModelMapper();
	}

	@Test
	public void convertToUtilisateurDtoTest() {

		UtilisateurDto utilisateurDto = map.convertToUtilisateurDto(utilisateurBean);

		assertEquals(utilisateurDto.getMail(), utilisateurBean.getMail());

	}

	@Test
	public void populateProduitPanierIdTest() {

		ProduitPanierBean produitPanier = new ProduitPanierBean();
		produitPanier.setId(1);
		Set<ProduitPanierBean> produitsPanier = new HashSet<>();

		utilisateurBean.setProduitsPanier(produitsPanier);

		UtilisateurDto utilisateurDto = map.convertToUtilisateurDto(utilisateurBean);

		assertEquals(utilisateurDto.getProduits().size(), utilisateurBean.getProduitsPanier().size());

	}

	@Test
	public void convertToUtilisateurBeanTest() {

		Set<Integer> produitsPanierId = new HashSet<>();
		int number = 1;
		produitsPanierId.add(number);

		UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setIdUtilisateur(1);
		utilisateurDto.setMail("testAdresseMail@gigaboutique.fr");
		utilisateurDto.setNom("testLastName");
		utilisateurDto.setPrenom("testFirstName");

		utilisateurDto.setProduits(produitsPanierId);

		UtilisateurBean utilisateurBeanTest = map.convertToUtilisateurBean(utilisateurDto, modelMapper);

		assertEquals(utilisateurBeanTest.getId(), utilisateurDto.getIdUtilisateur());

	}

	@Test
	public void convertToUtilisateurBeanForRegistrationTest() {

		RegisterDto registerDto = new RegisterDto();
		registerDto.setMail("testAdresseMail@gigaboutique.fr");
		registerDto.setNom("testLastName");
		registerDto.setPrenom("testFirstName");
		registerDto.setPassword("Poiuytreza31");

		UtilisateurBean utilisateurBeanTest = map.convertToUtilisateurBeanForRegistration(registerDto, modelMapper);

		assertEquals(utilisateurBeanTest.getMail(), registerDto.getMail());

	}

}
