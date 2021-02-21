package com.gigaboutique.gigavendeurservice.dto;

import org.springframework.stereotype.Service;

@Service
public class CommentaireDto {

	private String dateCommentaire;

	private String description;

	private String auteur;

	private String note;

	public String getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(String dateCommentaire) {
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

}
