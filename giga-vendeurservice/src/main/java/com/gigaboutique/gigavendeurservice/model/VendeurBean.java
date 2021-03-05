package com.gigaboutique.gigavendeurservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Vendeur")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = VendeurBean.class)
public class VendeurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "vendeur_id_vendeur_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_vendeur")
	private Integer id;

	@Column(name = "nom", unique = true)
	private String nom;

	@Column(name = "logo", columnDefinition="text", length=10485760)
	private String logo;

	@Column(name = "note")
	private String note;

	@Column(name = "nombre_commentaires")
	private String nombreDeCommentaires;

	@OneToMany(mappedBy = "vendeur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<CommentaireBean> commentaires = new ArrayList<>();

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNombreDeCommentaires() {
		return nombreDeCommentaires;
	}

	public void setNombreDeCommentaires(String nombreDeCommentaires) {
		this.nombreDeCommentaires = nombreDeCommentaires;
	}

	public List<CommentaireBean> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<CommentaireBean> commentaires) {
		this.commentaires = commentaires;
	}

}
