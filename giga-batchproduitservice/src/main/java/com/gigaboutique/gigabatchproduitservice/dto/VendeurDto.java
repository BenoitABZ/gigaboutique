package com.gigaboutique.gigabatchproduitservice.dto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VendeurDto {

	private Integer id;

	private String nom;

	private String logo;

	private String note;

	private String nombreDeCommentaires;

	private List<CommentaireDto> commentaires;

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

	public List<CommentaireDto> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<CommentaireDto> commentaires) {
		this.commentaires = commentaires;
	}
}
