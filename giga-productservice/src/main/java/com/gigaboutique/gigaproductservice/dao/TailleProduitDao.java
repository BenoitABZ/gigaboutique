package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.TailleProduit;

@Repository
public interface TailleProduitDao extends JpaRepository<TailleProduit, Integer> {

	List<TailleProduit> findById(int id);

	List<TailleProduit> findByProduit(int id);

	List<TailleProduit> findByTaille(int id);

	List<TailleProduit> findAll();

}
