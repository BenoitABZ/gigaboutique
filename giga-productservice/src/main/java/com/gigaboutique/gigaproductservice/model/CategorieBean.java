package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Categorie")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CategorieBean.class)
public class CategorieBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "categorie_id_categorie_seq_1", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_categorie")
	private Integer idCategorie;
	
	@Column(name = "nom_categorie")
	private String categorie;
	
	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProduitBean> produits = new ArrayList<>();

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public List<ProduitBean> getProduits() {
		return produits;
	}

	public void setProduits(List<ProduitBean> produits) {
		this.produits = produits;
	}

}
