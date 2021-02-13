package com.gigaboutique.gigauserservice.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class ProduitPanierService {

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	ProduitPanierDao produitPanierDao;

	@Autowired
	UtilisateurDao utilisateurDao;

	public void addProduitPanier(Integer idProduit, Integer idUtilisateur) throws TechnicalException {

		ProduitPanierBean produit = null;

		try {

			UtilisateurBean utilisateurBean = utilisateurDao.findById(idUtilisateur).get();

			if (produitPanierDao.findById(idProduit).isPresent()) {

				produit = produitPanierDao.findById(idProduit).get();

				Set<UtilisateurBean> utilisateurs = produit.getUtilisateurs();

				utilisateurs.add(utilisateurBean);

				produit.setUtilisateurs(utilisateurs);

				produitPanierDao.save(produit);

			} else {

				produit = new ProduitPanierBean();

				produit.setId(idProduit);

				Set<UtilisateurBean> utilisateurs = new HashSet<>();

				utilisateurs.add(utilisateurBean);

				produit.setUtilisateurs(utilisateurs);

				produitPanierDao.save(produit);

			}

		} catch (Exception e) {

			throw new TechnicalException("un problème a eu lieu lors de l'ajout du produit au panier");

		}

	}

	public void removeProduitPanier(int idProduit, int idUtilisateur) throws TechnicalException {
		
		try {

		ProduitPanierBean produit = produitPanierDao.findById(idProduit);

		UtilisateurBean utilisateur = utilisateurDao.findById(idUtilisateur);

		Set<UtilisateurBean> utilisateurs = produit.getUtilisateurs();

		utilisateurs.removeIf(x -> (x == utilisateur));

		if (utilisateurs.isEmpty()) {

			produitPanierDao.delete(produit);

		} else {

			produit.setUtilisateurs(utilisateurs);

			produitPanierDao.save(produit);
		}
		}catch(Exception e) {
			
			throw new TechnicalException("un problème a eu lieu lors de la suppression du produit au panier");
		}

	}

}
