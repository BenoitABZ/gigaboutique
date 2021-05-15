package com.gigaboutique.gigaclientservice.dto;

import java.util.Set;

public class UtilisateurDto {

	private int idUtilisateur;

	private String nom;

	private String prenom;

	private String mail;

	private String role;

	private Set<Integer> produits;

	private int idProduitToRemove;

	private String message;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Integer> getProduits() {
		return produits;
	}

	public void setProduits(Set<Integer> produits) {
		this.produits = produits;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getIdProduitToRemove() {
		return idProduitToRemove;
	}

	public void setIdProduitToRemove(int idProduitToRemove) {
		this.idProduitToRemove = idProduitToRemove;
	}

}
