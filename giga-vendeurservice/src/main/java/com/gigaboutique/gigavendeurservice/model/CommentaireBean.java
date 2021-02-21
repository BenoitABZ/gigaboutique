package com.gigaboutique.gigavendeurservice.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Commentaire")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CommentaireBean.class)
public class CommentaireBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "commentaire_id_commentaire_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_commentaire")
	private Integer id;
	
	@Column(name = "date_commentaire")
	private LocalDate dateCommentaire;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "auteur")
	private String auteur;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "nombreDeCommentaires")
	private String nombreDeCommentaires;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private VendeurBean vendeur;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(LocalDate dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public VendeurBean getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurBean vendeur) {
		this.vendeur = vendeur;
	}

}
