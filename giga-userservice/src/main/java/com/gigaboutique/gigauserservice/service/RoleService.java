package com.gigaboutique.gigauserservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.configuration.RoleConfiguration;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class RoleService {

	@Autowired
	RoleConfiguration rc;

	private RoleBean role;

	public RoleService() {

		setRole(new RoleBean());

	}

	public UtilisateurBean setRoleUtilisateur(UtilisateurBean utilisateur) {

		if (isAdmin(utilisateur)) {

			role.setRole(rc.getAdminRole());

		} else {
			
			role.setRole(rc.getUserRole());

		}

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
