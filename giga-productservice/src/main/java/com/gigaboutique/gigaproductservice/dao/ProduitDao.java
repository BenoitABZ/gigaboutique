package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.ProduitBean;

@Repository
public interface ProduitDao extends JpaRepository<ProduitBean, Integer> {

	ProduitBean findById(int id);

	ProduitBean findByNom(String nom);

	Page<ProduitBean> findAll(Pageable paging);
	
	void deleteByMaj(boolean bool);
	
	@Query("Select p.marque from Produit p")
	List<String> findAllMarques();
		
	@Query("Select p FROM Produit p JOIN p.genre g JOIN p.categorie c "
			+ "WHERE (:genre is null OR g.genre =:genre) "
			+ "AND  (:categories is null OR c.categorie IN :categories) "
			+ "AND (:categories is null OR p.marque IN :marques) "
			+ "ORDER BY p.prix ASC")
	
	Page<ProduitBean> findByCriteria(@Param("genre") String genre, @Param("categories") List<String> categories, @Param("marques") List<String> marques, Pageable paging);

}
