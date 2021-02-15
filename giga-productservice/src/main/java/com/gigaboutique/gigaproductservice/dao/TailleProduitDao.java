package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.TailleProduit;

@Repository
public interface TailleProduitDao extends JpaRepository<TailleProduit, Integer> {

	List<TailleProduit> findById(int id);

	List<TailleProduit> findAll();
	
	@Transactional
	@Modifying
	@Query("delete FROM TailleProduit as tp WHERE tp.produit.idProduit =:idProduit")
	void deleteByRelatedProduit(@Param("idProduit") int idProduit);

}
