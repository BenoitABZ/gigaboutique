package com.gigaboutique.gigauserservice.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurProduitPanierDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.model.UtilisateurProduitPanierBean;

@Service
@Transactional
public class ProduitPanierService {

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	ProduitPanierDao produitPanierDao;

	@Autowired
	UtilisateurDao utilisateurDao;

	@Autowired
	UtilisateurProduitPanierDao utilisateurProduitPanierDao;

	public UtilisateurDto getProduits(Integer idUtilisateur) throws UtilisateurException {

		UtilisateurBean utilisateurBean = utilisateurDao.findById(idUtilisateur).get();

		UtilisateurDto utilisateurDto = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateurBean);

		return utilisateurDto;

	}

	public void addProduitPanier(Integer idProduitPanier, Integer idUtilisateur) throws TechnicalException {

		ProduitPanierBean produitPanierBean = null;

		UtilisateurBean utilisateurBean = utilisateurDao.findById(idUtilisateur).get();

		try {

			if (produitPanierDao.findById(idProduitPanier).isPresent()) {

				produitPanierBean = produitPanierDao.findById(idProduitPanier).get();

				UtilisateurProduitPanierBean utilisateurProduitPanierBean = utilisateurProduitPanierDao.findByUtilisateurProduitPanier(idProduitPanier, idUtilisateur);

				if (utilisateurProduitPanierBean == null) {

					utilisateurProduitPanierBean = new UtilisateurProduitPanierBean(utilisateurBean, produitPanierBean);

					utilisateurBean.getUtilisateurProduits().add(utilisateurProduitPanierBean);
					produitPanierBean.getUtilisateurProduits().add(utilisateurProduitPanierBean);

					utilisateurProduitPanierDao.save(utilisateurProduitPanierBean);

				} else {

					return;

				}
			} else {

				produitPanierBean = new ProduitPanierBean();
				produitPanierBean.setId(idProduitPanier);
				produitPanierDao.save(produitPanierBean);

				utilisateurDao.save(utilisateurBean);

				UtilisateurProduitPanierBean utilisateurProduitPanierBean = new UtilisateurProduitPanierBean(utilisateurBean, produitPanierBean);

				utilisateurBean.getUtilisateurProduits().add(utilisateurProduitPanierBean);
				produitPanierBean.getUtilisateurProduits().add(utilisateurProduitPanierBean);

				utilisateurProduitPanierDao.save(utilisateurProduitPanierBean);

			}

		} catch (Exception e) {

			throw new TechnicalException("un problème a eu lieu lors de l'ajout du produit au panier" + e.getMessage());

		}
	}

	public void removeProduitPanier(int idProduitPanier, int idUtilisateur) throws TechnicalException {

		try {

			utilisateurProduitPanierDao.deleteByRelatedProduit(idProduitPanier);

			ProduitPanierBean produitPanierBean = produitPanierDao.findById(idProduitPanier);

			if (produitPanierBean != null) {

				Set<UtilisateurProduitPanierBean> utilisateurs = produitPanierBean.getUtilisateurProduits();

				if (utilisateurs == null || utilisateurs.size() == 0) {

					produitPanierDao.delete(produitPanierBean);

				}
			}
			
		} catch (Exception e) {

			throw new TechnicalException("un problème a eu lieu lors de la suppression du produit au panier");
		}
	}
}
