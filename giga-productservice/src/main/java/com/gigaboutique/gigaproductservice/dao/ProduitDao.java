package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.ProduitBean;

@Repository
public interface ProduitDao extends JpaRepository<ProduitBean, Integer> {

	ProduitBean findById(int id);

	ProduitBean findByNom(String nom);

	Page<ProduitBean> findAll(Pageable paging);
	
	List<ProduitBean> findByMaj(boolean bool);
	
	@Transactional
	@Modifying
	@Query("Delete FROM ProduitBean as p "
		    + "WHERE p.maj = false")
	public void deleteByMaj();
	
	@Query("Select p.marque from ProduitBean as p")
	List<String> findAllMarques();
		
	@Query("Select p FROM ProduitBean as p INNER JOIN p.genre as g INNER JOIN p.categorie as c "
			+ "WHERE (:genre is null OR g.genre =:genre) "
			+ "AND  ((:categorie) is null OR c.categorie =:categorie) "
			+ "AND ((:marque) is null OR p.marque =:marque) "
			+ "ORDER BY p.promotion DESC")	
	Page<ProduitBean> findByCriteria(@Param("genre") String genre, @Param("categorie") String categorie, @Param("marque") String marque, Pageable paging);

}
