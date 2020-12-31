package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;
import com.gigaboutique.gigauserservice.service.RoleService;
import com.gigaboutique.gigauserservice.service.UtilisateurService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UtilisateurServiceTests {

	@InjectMocks
	UtilisateurService utilisateurService;

	@Mock
	MapUtilisateurDtoService mapUtilisateurDtoService;
	
	@Mock
	RoleService roleService;

	@Mock
	ModelMapper modelMapper;
	
	@Mock
    PasswordEncoder passwordEncoder;
	
	@Mock
	UtilisateurDao utilisateurDao;

	UtilisateurBean utilisateurBean;

	RegisterDto registerDto;

	UtilisateurDto utilisateurDto;

	@BeforeAll
	public void setUp() {

		registerDto = new RegisterDto();
		registerDto.setNom("testLastName");
		registerDto.setPrenom("testFirstName");
		registerDto.setPrenom("testFirstName");
		registerDto.setMail("testAdresseMail@gigaboutique.fr");
		registerDto.setPassword("Poiuytreza31");

		UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setNom("testLastNameDto");

		utilisateurBean = new UtilisateurBean();
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

	}

	@Test
	public void getUtilisateursTest() {

		List<UtilisateurBean> utilisateursBean = new ArrayList<>();
		
		List<UtilisateurDto> utilisateursDtoExpected = new ArrayList<>();
		utilisateursDtoExpected.add(utilisateurDto);

		utilisateursBean.add(utilisateurBean);

		when(utilisateurDao.findAll()).thenReturn(utilisateursBean);

		when(mapUtilisateurDtoService.convertToUtilisateurDto(utilisateurBean)).thenReturn(utilisateurDto);
		
		List<UtilisateurDto> utilisateursDtoReal = utilisateurService.getUtilisateurs();
		
		assertTrue(utilisateursDtoReal.containsAll(utilisateursDtoExpected));
	}
	
	@Test
	public void registerUtilisateurTest1() {

		when(mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto, modelMapper)).thenReturn(utilisateurBean);
		
		when(passwordEncoder.encode(anyString())).thenReturn("passwordEncoded");
	
		when(utilisateurDao.findByMail(anyString())).thenReturn(null);
		
		when(roleService.setRoleUtilisateur(utilisateurBean)).thenReturn(utilisateurBean);
				
		verify(utilisateurDao, times(1)).save(utilisateurBean);
		
	}
	
	@Test
	public void registerUtilisateurTest2() {

		when(mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto, modelMapper)).thenReturn(utilisateurBean);
			
		when(utilisateurDao.findByMail(anyString())).thenReturn(null);
		
		utilisateurBean.setMail("testAdresseMail");
		
		assertThrows(UtilisateurException.class, () -> {
			utilisateurService.registerUtilisateur(registerDto, roleService);
		});
		
	}

}
