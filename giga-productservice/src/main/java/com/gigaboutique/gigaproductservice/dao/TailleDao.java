package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.TailleBean;

@Repository
public interface TailleDao extends JpaRepository<TailleBean, Integer> {

	TailleBean findById(int id);

	TailleBean findByTaille(String taille);

	List<TailleBean> findAll();

}
