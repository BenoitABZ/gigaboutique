package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.TailleProduit;
import com.gigaboutique.gigaproductservice.model.TailleProduitId;

@Repository
public interface TailleProduitDao extends JpaRepository<TailleProduit, TailleProduitId> {

	List<TailleProduit> findByIdIdProduit(int id);

	List<TailleProduit> findByIdIdTaille(int id);

	List<TailleProduit> findAll();

}
