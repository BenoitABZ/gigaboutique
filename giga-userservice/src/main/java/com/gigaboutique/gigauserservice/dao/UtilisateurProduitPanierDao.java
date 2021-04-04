package com.gigaboutique.gigauserservice.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigauserservice.model.UtilisateurProduitPanierBean;

@Repository
public interface UtilisateurProduitPanierDao extends JpaRepository<UtilisateurProduitPanierBean, Integer> {

	@Query("select up FROM UtilisateurProduitPanierBean as up WHERE up.produitPanier.id =:idProduitPanier AND up.utilisateur.id=:idUtilisateur")
	UtilisateurProduitPanierBean findByUtilisateurProduitPanier(int idProduitPanier, int idUtilisateur);

	@Transactional
	@Modifying
	@Query("delete FROM UtilisateurProduitPanierBean as up WHERE up.produitPanier.id =:idProduitPanier")
	void deleteByRelatedProduit(@Param("idProduitPanier") int idProduitPanier);

}
