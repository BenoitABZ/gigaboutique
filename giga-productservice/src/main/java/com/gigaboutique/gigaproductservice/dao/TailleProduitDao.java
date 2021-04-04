package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.TailleProduitBean;

@Repository
public interface TailleProduitDao extends JpaRepository<TailleProduitBean, Integer> {

	List<TailleProduitBean> findById(int id);

	List<TailleProduitBean> findAll();
	
	@Query("select tp FROM TailleProduitBean as tp WHERE tp.produit.idProduit =:idProduit AND tp.taille.idTaille=:idTaille")
	TailleProduitBean findByTailleProduit(int idProduit, int idTaille);
	
	@Transactional
	@Modifying
	@Query("delete FROM TailleProduitBean as tp WHERE tp.produit.idProduit =:idProduit")
	void deleteByRelatedProduit(@Param("idProduit") int idProduit);

}
