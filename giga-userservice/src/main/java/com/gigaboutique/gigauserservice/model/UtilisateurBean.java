package com.gigaboutique.gigauserservice.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Utilisateur")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UtilisateurBean.class)
public class UtilisateurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "utilisateur_id_utilisateur_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
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
	//@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,32}", message = "votre mot de passe doit comporter au moins 8 caractères dont 1 majuscule et 1 chiffre")
	@Column(name = "mot_de_passe")
	private String motDePasse;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Utilisateur_ProduitPanier", joinColumns = @JoinColumn(name = "id_utilisateur"), inverseJoinColumns = @JoinColumn(name = "id_produitPanier"))
	private Set<ProduitPanierBean> produitsPanier;

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

	public Set<ProduitPanierBean> getProduitsPanier() {
		return produitsPanier;
	}

	public void setProduitsPanier(Set<ProduitPanierBean> produitPanier) {
		this.produitsPanier = produitPanier;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

}
