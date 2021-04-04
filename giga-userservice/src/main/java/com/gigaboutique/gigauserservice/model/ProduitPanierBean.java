
package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Produitpanier")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProduitPanierBean.class)
public class ProduitPanierBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_produitpanier")
	private int id;

	@OneToMany(mappedBy = "produitPanier", fetch = FetchType.EAGER)
	private Set<UtilisateurProduitPanierBean> utilisateurProduits = new HashSet<>();

	public Set<UtilisateurProduitPanierBean> getUtilisateurProduits() {
		return utilisateurProduits;
	}

	public void setUtilisateurProduits(Set<UtilisateurProduitPanierBean> utilisateurProduits) {
		this.utilisateurProduits = utilisateurProduits;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProduitPanierBean) {

			ProduitPanierBean produitPanier = (ProduitPanierBean) obj;

			return this.getId() == produitPanier.getId();
		}

		return false;

	}

}
