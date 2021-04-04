package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Taille_Produit")
public class TailleProduitBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "taille_produit_id_taille_produit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_taille_produit")
	private Integer idTailleProduit;

	public Integer getIdTailleProduit() {
		return idTailleProduit;
	}

	public void setIdTailleProduit(Integer idTailleProduit) {
		this.idTailleProduit = idTailleProduit;
	}

	@Column(name = "disponibilite")
	private boolean disponibilite;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produit")
	private ProduitBean produit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_taille")
	private TailleBean taille;

	public TailleProduitBean() {

	}

	public TailleProduitBean(boolean disponibilite, ProduitBean produit, TailleBean taille) {
		super();
		this.disponibilite = disponibilite;
		this.produit = produit;
		this.taille = taille;
	}

	public boolean isDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

	public ProduitBean getProduit() {
		return produit;
	}

	public void setProduit(ProduitBean produit) {
		this.produit = produit;
	}

	public TailleBean getTaille() {
		return taille;
	}

	public void setTaille(TailleBean taille) {
		this.taille = taille;
	}

}
