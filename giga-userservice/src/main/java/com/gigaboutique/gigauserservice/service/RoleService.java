package com.gigaboutique.gigauserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.configuration.RoleConfiguration;
import com.gigaboutique.gigauserservice.dao.RoleDao;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class RoleService {

	@Autowired
	RoleConfiguration rc;

	@Autowired
	RoleDao roleDao;

	private RoleBean role;

	public UtilisateurBean setRoleUtilisateur(UtilisateurBean utilisateur) {

		rc.init();

		if (isAdmin(utilisateur)) {

			role = rc.getRoleBeanAdmin();

		} else {

			role = rc.getRoleBeanUser();

		}

		role = roleDao.save(role);

		utilisateur.setRole(role);

		return utilisateur;
	}

	public RoleBean getRole() {

		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	private boolean isAdmin(UtilisateurBean utilisateur) {

		boolean bool = false;

		if (utilisateur.getMail().endsWith(rc.getAdminMail())) {

			bool = true;
		}

		return bool;

	}

}
