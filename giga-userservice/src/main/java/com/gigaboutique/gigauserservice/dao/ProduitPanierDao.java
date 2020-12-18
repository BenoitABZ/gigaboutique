package com.gigaboutique.gigauserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigauserservice.model.ProduitPanierBean;

@Repository
public interface ProduitPanierDao extends JpaRepository<ProduitPanierBean, Integer> {

	List<ProduitPanierBean> findByIdUtilisateur(int id);

}
