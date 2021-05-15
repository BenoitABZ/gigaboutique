package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gigaboutique.gigauserservice.configuration.UserConfiguration;
import com.gigaboutique.gigauserservice.dao.RoleDao;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.RoleService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class RoleServiceTests {
/*
	@InjectMocks
	RoleService roleService;

	@Mock
	UserConfiguration rc;

	@Mock
	RoleDao roleDao;

	UtilisateurBean utilisateurBean;

	@BeforeAll
	public void setUp() {

		utilisateurBean = new UtilisateurBean();

		utilisateurBean.setMail("mail.adm@gmail.com");

	}

	@Test
	public void setUtilisateurRoleTest() {

		RoleBean roleBeanAdmin = new RoleBean();
		roleBeanAdmin.setId(1);
		roleBeanAdmin.setRole("ROLE_ADMIN");

		RoleBean roleBeanUser = new RoleBean();
		roleBeanUser.setId(2);
		roleBeanUser.setRole("ROLE_USER");

		when(rc.getAdminMail()).thenReturn(".adm@gmail.com");

		when(rc.getRoleBeanAdmin()).thenReturn(roleBeanAdmin);

		doNothing().when(rc).init();
		
		when(roleDao.save(roleBeanAdmin)).thenReturn(roleBeanAdmin);

		utilisateurBean = roleService.setRoleUtilisateur(utilisateurBean);

		assertEquals("ROLE_ADMIN", utilisateurBean.getRole().getRole());

	}
*/
}
