package com.gigaboutique.gigavendeurservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigavendeurservice.model.VendeurBean;

@Repository
public interface VendeurDao extends JpaRepository<VendeurBean, Integer> {

	VendeurBean findById(int id);

	VendeurBean findByNom(String nom);

	List<VendeurBean> findAll();

}
