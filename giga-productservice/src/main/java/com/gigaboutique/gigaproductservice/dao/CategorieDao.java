package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.CategorieBean;

@Repository
public interface CategorieDao extends JpaRepository<CategorieBean, Integer> {

	CategorieBean findById(int id);

	CategorieBean findByCategorie(String categorie);

	List<CategorieBean> findAll();

}
