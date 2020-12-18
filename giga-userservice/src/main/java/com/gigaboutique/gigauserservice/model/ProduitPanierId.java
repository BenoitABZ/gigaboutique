package com.gigaboutique.gigauserservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProduitPanierId {
	
	@Column(name="id_utilisateur")
	private int idUtilisateur;
	
	@Column(name = "id_produit_external")
	private Integer idProduit;

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}
	

}
