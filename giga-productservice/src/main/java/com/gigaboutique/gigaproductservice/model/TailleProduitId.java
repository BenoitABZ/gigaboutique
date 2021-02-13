package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class TailleProduitId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_produit")
	private Integer idProduit;

	@Column(name = "id_taille")
	private Integer idTaille;

	public TailleProduitId() {
	}

	public TailleProduitId(Integer idProduit, Integer idTaille) {
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

	@Override
	public int hashCode() {
		return Objects.hash(getIdTaille(), getIdProduit());
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof TailleProduitId))
			return false;
		TailleProduitId c = (TailleProduitId) obj;
		return Objects.equals(getIdProduit(), c.getIdProduit()) && Objects.equals(getIdTaille(), c.getIdTaille());
	}

}
