package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;
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
	BCryptPasswordEncoder passwordEncoder;

	@Mock
	UtilisateurDao utilisateurDao;

	UtilisateurBean utilisateurBean;

	RegisterDto registerDto;

	UtilisateurDto utilisateurDto;

	@BeforeEach
	public void setUp() {

		registerDto = new RegisterDto();
		registerDto.setNom("testLastName");
		registerDto.setPrenom("testFirstName");
		registerDto.setMail("testadressemail@gigaboutique.fr");
		registerDto.setPassword("Poiuytreza31");

		UtilisateurDto utilisateurDto = new UtilisateurDto();
		utilisateurDto.setNom("testLastNameDto");

		utilisateurBean = new UtilisateurBean();
		utilisateurBean.setId(1);
		utilisateurBean.setNom("testLastName");
		utilisateurBean.setPrenom("testFirstName");
		utilisateurBean.setMail("mail.adm@gmail.com");
		utilisateurBean.setMotDePasse("Poiuytreza31");

		RoleBean roleBean = new RoleBean();
		roleBean.setRole("ROLE_ADMIN");
		utilisateurBean.setRole(roleBean);

	}

	@Test
	public void getUtilisateursTest() throws UtilisateurException {

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
	public void registerUtilisateurTest1() throws UtilisateurException {

		when(mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto))
				.thenReturn(utilisateurBean);

		when(passwordEncoder.encode(anyString())).thenReturn("passwordEncoded31");

		when(utilisateurDao.findByMail(anyString())).thenReturn(null);

		when(roleService.setRoleUtilisateur(utilisateurBean)).thenReturn(utilisateurBean);

		try {
			utilisateurService.registerUtilisateur(registerDto, roleService);
		} catch (UtilisateurException e) {
			fail("Exception anormale");
		}

		verify(utilisateurDao, times(1)).save(utilisateurBean);

	}

	@Test
	public void registerUtilisateurTest2() throws UtilisateurException {

		when(mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto)).thenReturn(utilisateurBean);

		when(utilisateurDao.findByMail(anyString())).thenReturn(null);

		utilisateurBean.setMail("testAdresseMail");

		assertThrows(UtilisateurException.class, () -> {
			utilisateurService.registerUtilisateur(registerDto, roleService);
		});

	}

	@Test
	public void registerUtilisateurTest3() throws UtilisateurException {

		when(mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto)).thenReturn(utilisateurBean);

		when(utilisateurDao.findByMail(anyString())).thenReturn(null);

		utilisateurBean.setMotDePasse("a");

		assertThrows(UtilisateurException.class, () -> {
			utilisateurService.registerUtilisateur(registerDto, roleService);
		});

	}

}
