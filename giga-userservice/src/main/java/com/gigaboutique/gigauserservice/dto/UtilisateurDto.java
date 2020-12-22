package com.gigaboutique.gigauserservice.dto;

import java.util.Set;

import com.gigaboutique.gigauserservice.model.RoleBean;

public class UtilisateurDto {

	private int idUtilisateur;

	private String nom;

	private String prenom;

	private String mail;

	private RoleBean role;

	private Set<Integer> produits;

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

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
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

}
