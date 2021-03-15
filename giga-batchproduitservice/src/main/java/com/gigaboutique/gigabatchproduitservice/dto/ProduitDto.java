package com.gigaboutique.gigabatchproduitservice.dto;

import java.util.List;
import java.util.Map;

public class ProduitDto {

	private int idProduit;

	private int idVendeur;

	private String nom;

	private String prix;

	private String newPrix;

	private String promotion;

	private String marque;

	private String genre;

	private String categorie;

	private String adresseWeb;

	private Map<String, Boolean> tailles;

	private List<String> images;

	public int getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}

	public int getIdVendeur() {
		return idVendeur;
	}

	public void setIdVendeur(int idVendeur) {
		this.idVendeur = idVendeur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public Map<String, Boolean> getTailles() {
		return tailles;
	}

	public void setTailles(Map<String, Boolean> map) {
		this.tailles = map;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getAdresseWeb() {
		return adresseWeb;
	}

	public void setAdresseWeb(String adresseWeb) {
		this.adresseWeb = adresseWeb;
	}

	public String getNewPrix() {
		return newPrix;
	}

	public void setNewPrix(String newPrix) {
		this.newPrix = newPrix;
	}

	@Override
	public String toString() {
		return "ProduitDto [idProduit=" + idProduit + ", idVendeur=" + idVendeur + ", nom=" + nom + ", prix=" + prix
				+ ", newPrix=" + newPrix + ", promotion=" + promotion + ", marque=" + marque + ", genre=" + genre
				+ ", categorie=" + categorie + ", adresseWeb=" + adresseWeb + ", tailles=" + tailles + ", images="
				+ images + "]";
	}

}
