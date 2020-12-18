package com.gigaboutique.gigauserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gigaboutique.gigauserservice.model.UtilisateurBean;

public interface UtilisateurDao extends JpaRepository<UtilisateurBean, Integer> {

	UtilisateurBean findById(int id);

	UtilisateurBean findByMail(String mail);

	List<UtilisateurBean> findAll();

}
