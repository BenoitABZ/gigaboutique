package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TailleProduitId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idProduit;
	private Integer idTaille;

	public TailleProduitId() {
	}

	public TailleProduitId(Integer idProduit, Integer idTaille) {
		super();
		this.idProduit = idProduit;
		this.idTaille = idTaille;
	}

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

	public Integer getIdTaille() {
		return idTaille;
	}

	public void setIdTaille(Integer idTaille) {
		this.idTaille = idTaille;
	}

}
