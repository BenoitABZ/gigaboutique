package com.gigaboutique.gigaproductservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigaproductservice.model.GenreBean;

@Repository
public interface GenreDao extends JpaRepository<GenreBean, Integer> {

	GenreBean findById(int id);

	GenreBean findByGenre(String genre);

	List<GenreBean> findAll();

}
