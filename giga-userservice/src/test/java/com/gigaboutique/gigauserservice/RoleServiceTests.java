package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gigaboutique.gigauserservice.configuration.RoleConfiguration;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.RoleService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RoleServiceTests {

	@InjectMocks
	RoleService roleService;

	@Mock
	RoleConfiguration rc;

	UtilisateurBean utilisateurBean;

	@BeforeAll
	public void setUp() {

		utilisateurBean = new UtilisateurBean();

		utilisateurBean.setMail("admin@gigaboutique.fr");

	}

	@Test
	public void setUtilisateurRoleTest() {

		when(rc.getAdminMail()).thenReturn("@gigaboutique.fr");
		when(rc.getAdminRole()).thenReturn("ROLE_ADMIN");

		utilisateurBean = roleService.setRoleUtilisateur(utilisateurBean);

		assertEquals("ROLE_ADMIN", utilisateurBean.getRole().getRole());

	}

}
