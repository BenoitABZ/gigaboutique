package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Utilisateur")
public class UtilisateurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utlisateur")
	private Integer id;

	@Size(min = 3, message = "votre nom doit comporter au moins 3 caractères")
	@Column(name = "nom")
	private String nom;

	@Size(min = 3, message = "votre prenom doit comporter au moins 3 caractères")
	@Column(name = "prenom")
	private String prenom;

	@JsonProperty("mail")
	@Email(message = "format d'adresse mail incorrect")
	@Column(name = "adresse_mail")
	private String mail;

	@JsonProperty("motDePasse")
	@Column(name = "mot_de_passe")
	private String motDePasse;

	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER)
	private Set<UtilisateurProduitPanierBean> utilisateurProduits = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role", nullable = false)
	private RoleBean role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public Set<UtilisateurProduitPanierBean> getUtilisateurProduits() {
		return utilisateurProduits;
	}

	public void setUtilisateurProduits(Set<UtilisateurProduitPanierBean> utilisateurProduits) {
		this.utilisateurProduits = utilisateurProduits;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UtilisateurBean) {

			UtilisateurBean utilisateur = (UtilisateurBean) obj;

			return this.getId() == utilisateur.getId();
		}

		return false;

	}

}
