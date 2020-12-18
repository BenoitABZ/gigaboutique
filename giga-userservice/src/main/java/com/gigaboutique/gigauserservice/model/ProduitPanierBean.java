package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@EmbeddedId
	private ProduitPanierId ppId;

	@JoinColumn(name = "utilisateur_id", referencedColumnName = "utilisateur_id")
	@ManyToOne
	private UtilisateurBean utilisateur;

	public ProduitPanierId getPpId() {
		return ppId;
	}

	public void setPpId(ProduitPanierId ppId) {
		this.ppId = ppId;
	}

	public UtilisateurBean getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UtilisateurBean utilisateur) {
		this.utilisateur = utilisateur;
	}

}
