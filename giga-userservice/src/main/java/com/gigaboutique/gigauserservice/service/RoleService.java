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

	RoleService() {

		setRole(new RoleBean());

	}

	public UtilisateurBean setRoleUtilisateur(UtilisateurBean utilisateur) {

		if (isAdmin(utilisateur)) {

			role.setRole(rc.getUserRole());

		} else {

			role.setRole(rc.getAdminRole());

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

		for (String password : rc.getAdminPassword()) {

			if (utilisateur.getMail().equals(rc.getAdminMail()) && utilisateur.getMotDePasse().equals(password)) {

				bool = true;
			}

		}

		return bool;

	}

}
