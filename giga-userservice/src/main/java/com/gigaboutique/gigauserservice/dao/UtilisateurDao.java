package com.gigaboutique.gigauserservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Repository
public interface UtilisateurDao extends JpaRepository<UtilisateurBean, Integer> {

	UtilisateurBean findById(int id);

	UtilisateurBean findByMail(String mail);

	List<UtilisateurBean> findAll();

}
