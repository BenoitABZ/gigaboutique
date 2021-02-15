package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.ImageProduitBean;

@Repository
public interface ImageProduitDao extends JpaRepository<ImageProduitBean, Integer> {

	ImageProduitBean findById(int id);
	
	ImageProduitBean findByAdresseWeb(String adresseWeb);

	List<ImageProduitBean> findAll();
	
	@Transactional
	@Modifying
	@Query("delete FROM ImageProduitBean as ip WHERE ip.produit.idProduit =:idProduit")
	void deleteByRelatedProduit(@Param("idProduit") int idProduit);

}
