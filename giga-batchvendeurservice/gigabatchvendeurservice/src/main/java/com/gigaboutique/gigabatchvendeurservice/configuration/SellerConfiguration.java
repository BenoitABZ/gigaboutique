package com.gigaboutique.gigabatchvendeurservice.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SellerConfiguration {

	@Value("#{${seller-configs.vendeurNom}}")
	private Map<String, List<String>> vendeurNom;

	@Value("#{${seller-configs.vendeurNote}}")
	private Map<String, List<String>> vendeurNote;

	@Value("#{${seller-configs.vendeurLogo}}")
	private Map<String, List<String>> vendeurLogo;

	@Value("#{${seller-configs.vendeurNombreDeCommentaires}}")
	private Map<String, List<String>> vendeurNombreDeCommentaires;

	@Value("#{${seller-configs.auteurCommentaire}}")
	private Map<String, List<String>> auteurCommentaire;

	@Value("#{${seller-configs.noteCommentaire}}")
	private Map<String, List<String>> noteCommentaire;

	@Value("#{${seller-configs.descriptionCommentaire}}")
	private Map<String, List<String>> descriptionCommentaire;

	@Value("#{${seller-configs.dateCommentaire}}")
	private Map<String, List<String>> dateCommentaire;

	@Value("#{${seller-configs.commentaireSection}}")
	private String commentaireSection;

	@Value("#{${seller-configs.nombreCommentaires}}")
	private String nombreCommentaires;

	@Value("#{${seller-configs.patterLinkToScrap}}")
	private String patternLinkToScrap;

	@Value("#{${seller-configs.sellers}}")
	private List<String> sellers;

	public Map<String, List<String>> getVendeurNom() {
		return vendeurNom;
	}

	public void setVendeurNom(Map<String, List<String>> vendeurNom) {
		this.vendeurNom = vendeurNom;
	}

	public Map<String, List<String>> getVendeurNote() {
		return vendeurNote;
	}

	public void setVendeurNote(Map<String, List<String>> vendeurNote) {
		this.vendeurNote = vendeurNote;
	}

	public Map<String, List<String>> getVendeurLogo() {
		return vendeurLogo;
	}

	public void setVendeurLogo(Map<String, List<String>> vendeurLogo) {
		this.vendeurLogo = vendeurLogo;
	}

	public Map<String, List<String>> getVendeurNombreDeCommentaires() {
		return vendeurNombreDeCommentaires;
	}

	public void setVendeurNombreDeCommentaires(Map<String, List<String>> vendeurNombreDeCommentaires) {
		this.vendeurNombreDeCommentaires = vendeurNombreDeCommentaires;
	}

	public Map<String, List<String>> getAuteurCommentaire() {
		return auteurCommentaire;
	}

	public void setAuteurCommentaire(Map<String, List<String>> auteurCommentaire) {
		this.auteurCommentaire = auteurCommentaire;
	}

	public Map<String, List<String>> getNoteCommentaire() {
		return noteCommentaire;
	}

	public void setNoteCommentaire(Map<String, List<String>> noteCommentaire) {
		this.noteCommentaire = noteCommentaire;
	}

	public Map<String, List<String>> getDescriptionCommentaire() {
		return descriptionCommentaire;
	}

	public void setDescriptionCommentaire(Map<String, List<String>> descriptionCommentaire) {
		this.descriptionCommentaire = descriptionCommentaire;
	}

	public Map<String, List<String>> getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(Map<String, List<String>> dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public String getCommentaireSection() {
		return commentaireSection;
	}

	public void setCommentaireSection(String commentaireSection) {
		this.commentaireSection = commentaireSection;
	}

	public String getNombreCommentaires() {
		return nombreCommentaires;
	}

	public void setNombreCommentaires(String nombreCommentaires) {
		this.nombreCommentaires = nombreCommentaires;
	}

	public String getPatternLinkToScrap() {
		return patternLinkToScrap;
	}

	public void setPatternLinkToScrap(String patternLinkToScrap) {
		this.patternLinkToScrap = patternLinkToScrap;
	}

	public List<String> getSellers() {
		return sellers;
	}

	public void setSellers(List<String> sellers) {
		this.sellers = sellers;
	}

}
