package com.gigaboutique.gigavendeurservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gigaboutique.gigavendeurservice.model.CommentaireBean;

@Repository
public interface CommentaireDao extends JpaRepository<CommentaireBean, Integer> {

	CommentaireBean findById(int id);

}
