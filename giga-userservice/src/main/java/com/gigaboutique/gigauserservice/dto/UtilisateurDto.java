package com.gigaboutique.gigauserservice.dto;

import java.util.List;

import com.gigaboutique.gigauserservice.model.ProduitPanierId;

public class UtilisateurDto {

	private int idUtilisateur;

	private String nom;

	private String prenom;

	private String mail;

	private List<ProduitPanierId> produits;

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<ProduitPanierId> getProduits() {
		return produits;
	}

	public void setProduits(List<ProduitPanierId> produits) {
		this.produits = produits;
	}


}
