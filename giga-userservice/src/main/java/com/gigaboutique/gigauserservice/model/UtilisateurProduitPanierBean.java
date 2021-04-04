package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Utilisateur_Produitpanier")
public class UtilisateurProduitPanierBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public UtilisateurProduitPanierBean(UtilisateurBean utilisateur, ProduitPanierBean produitPanier) {
		super();
		this.utilisateur = utilisateur;
		this.produitPanier = produitPanier;
	}

	public UtilisateurProduitPanierBean() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utilisateur_produitpanier")
	private Integer idUtilisateurProduitPanier;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_utilisateur")
	private UtilisateurBean utilisateur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produitpanier")
	private ProduitPanierBean produitPanier;

	public Integer getIdUtilisateurProduitPanier() {
		return idUtilisateurProduitPanier;
	}

	public void setIdUtilisateurProduitPanier(Integer idUtilisateurProduitPanier) {
		this.idUtilisateurProduitPanier = idUtilisateurProduitPanier;
	}

	public UtilisateurBean getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurBean utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ProduitPanierBean getProduitPanier() {
		return produitPanier;
	}

	public void setProduitPanier(ProduitPanierBean produitPanier) {
		this.produitPanier = produitPanier;
	}

}
