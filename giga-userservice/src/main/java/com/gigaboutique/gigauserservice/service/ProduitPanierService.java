package com.gigaboutique.gigauserservice.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
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

	public UtilisateurDto addProduitPanier(UtilisateurDto utilisateurDto) throws TechnicalException {

		Set<ProduitPanierBean> produitsPanier = new HashSet<>();

		ProduitPanierBean produit = null;

		try {

			UtilisateurBean utilisateurBean = mapUtilisateurDtoService.convertToUtilisateurBean(utilisateurDto,
					modelMapper);

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

		} catch (Exception e) {

			throw new TechnicalException("un problème a eu lieu lors de l'ajout du produit au panier.");

		}

		return utilisateurDto;

	}

	public UtilisateurDto removeProduitPanier(UtilisateurDto utilisateurDto) throws TechnicalException {

		Set<ProduitPanierBean> produitsPanier = new HashSet<>();

		Set<UtilisateurBean> utilisateurs = new HashSet<>();

		ProduitPanierBean produit = null;

		try {

			UtilisateurBean utilisateurBean = mapUtilisateurDtoService.convertToUtilisateurBean(utilisateurDto,
					modelMapper);

			Integer id = utilisateurDto.getIdProduitToRemove();

			if (produitPanierDao.findById(id).isPresent()) {

				produit = produitPanierDao.findById(id).get();

				utilisateurs = produit.getUtilisateurs();

				utilisateurs.remove(utilisateurBean);

				if (utilisateurs.isEmpty()) {

					produitPanierDao.delete(produit);
				} else {

					produit.setUtilisateurs(utilisateurs);

					produitPanierDao.save(produit);

				}
			}
			produitsPanier = utilisateurBean.getProduitsPanier();

			produitsPanier.remove(produit);

			utilisateurBean.setProduitsPanier(produitsPanier);

			utilisateurDao.save(utilisateurBean);

		} catch (Exception e) {

			throw new TechnicalException("un problème a eu lieu lors de de la suppression du produit au panier.");

		}

		return utilisateurDto;

	}

}
