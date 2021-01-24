package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.VendeurBean;

@Repository
public interface VendeurDao extends JpaRepository<VendeurBean, Integer> {

	VendeurBean findById(int id);

	List<VendeurBean> findAll();

}
