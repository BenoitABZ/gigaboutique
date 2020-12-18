package com.gigaboutique.gigauserservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class DaoManager {

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private ProduitPanierDao produitPanierDao;

	private static DaoManager daoManager;

	private DaoManager() {

	}

	@Bean(name = "daoManager")
	public static DaoManager getInstance() {

		if (daoManager == null) {

			daoManager = new DaoManager();
		}

		return daoManager;

	}

	public UtilisateurDao getUtilisateurDao() {

		return utilisateurDao;
	}

	public ProduitPanierDao getProduitPanierDao() {

		return produitPanierDao;
	}

}
