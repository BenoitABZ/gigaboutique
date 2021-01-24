package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.ImageProduitBean;
import com.gigaboutique.gigaproductservice.model.ProduitBean;

@Repository
public interface ImageProduitDao extends JpaRepository<ImageProduitBean, Integer> {

	ImageProduitBean findById(int id);

	ImageProduitBean findByProduit(ProduitBean produit);

	List<ImageProduitBean> findAll();

}
