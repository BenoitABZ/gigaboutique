package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.ProduitBean;

@Repository
public interface ProduitDao extends JpaRepository<ProduitBean, Integer> {

	ProduitBean findById(int id);

	ProduitBean findByNom(String nom);

	List<ProduitBean> findAll();

}
