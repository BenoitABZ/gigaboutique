package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Taille_Produit")
public class TailleProduit implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TailleProduitId id = new TailleProduitId();
	
	@ManyToOne
	@MapsId("idProduit")
	private ProduitBean produit;
	
	@ManyToOne
	@MapsId("idTaille")
	private TailleBean taille;
	
	@Column(name="disponibilite")
	private boolean disponibilite;

	public TailleProduitId getId() {
		return id;
	}

	public void setId(TailleProduitId id) {
		this.id = id;
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

	public boolean isDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(boolean disponibilite) {
		this.disponibilite = disponibilite;
	}

}
