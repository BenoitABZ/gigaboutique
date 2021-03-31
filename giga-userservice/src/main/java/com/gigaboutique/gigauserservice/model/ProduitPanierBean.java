
package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ProduitPanier")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProduitPanierBean.class)
public class ProduitPanierBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_produitPanier")
	private int id;

	@ManyToMany(mappedBy = "produitsPanier",fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	//@JoinTable(name = "Utilisateur_ProduitPanier", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_ProduitPanier"))
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProduitPanierBean) {

			ProduitPanierBean produitPanier = (ProduitPanierBean) obj;

			return this.getId() == produitPanier.getId();
		}

		return false;

	}

}
