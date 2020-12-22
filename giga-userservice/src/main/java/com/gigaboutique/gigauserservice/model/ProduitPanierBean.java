package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Utilisateur_Produit")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProduitPanierBean.class)
public class ProduitPanierBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_produitPanier")
	private int id;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<UtilisateurBean> utilisateurs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<UtilisateurBean> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUtilisateurs(Set<UtilisateurBean> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

}
