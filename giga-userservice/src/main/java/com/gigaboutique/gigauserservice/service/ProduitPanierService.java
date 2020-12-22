package com.gigaboutique.gigauserservice.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class ProduitPanierService {

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	ProduitPanierDao produitPanierDao;

	@Autowired
	UtilisateurDao utilisateurDao;

	public UtilisateurDto addProduitPanier(UtilisateurDto utilisateurDto) {

		Set<ProduitPanierBean> produitsPanier = new HashSet<>();

		ProduitPanierBean produit = null;

		UtilisateurBean utilisateurBean = mapUtilisateurDtoService.convertToUtilisateurBean(utilisateurDto);

		for (Integer id : utilisateurDto.getProduits()) {

			if (produitPanierDao.findById(id).isPresent()) {

				produit = produitPanierDao.findById(id).get();

			} else {

				produit = new ProduitPanierBean();

				produit.setId(id);

			}

			produitsPanier.add(produit);

		}

		utilisateurBean.setProduitsPanier(produitsPanier);

		utilisateurDao.save(utilisateurBean);

		return utilisateurDto;

	}

}
