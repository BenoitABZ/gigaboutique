package com.gigaboutique.gigabatchproduitservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("product-configs")
public class ProductConfiguration {

	private String[] produitsLink;

	private String[] produitSection;

	private String[] produitLinks;

	private String[] produitTailleDisponible;

	private String[] produitTailleIndisponible;

	private String[] produitTailleSection;

	private String[] produitImage;

	private String[] imageSection;

	private String[] produitCategorie;

	private String[] produitMarque;

	private String[] produitNewPrix;

	private String[] produitOldPrix;

	private String[] produitNom;

	private String[] produitGenre;

	private String[] vendeurNom;

	private int nombreProduits;

	public String[] getProduitsLink() {
		return produitsLink;
	}

	public void setProduitsLink(String[] produitsLink) {
		this.produitsLink = produitsLink;
	}

	public String[] getProduitSection() {
		return produitSection;
	}

	public void setProduitSection(String[] produitSection) {
		this.produitSection = produitSection;
	}

	public String[] getProduitLinks() {
		return produitLinks;
	}

	public void setProduitLinks(String[] produitLinks) {
		this.produitLinks = produitLinks;
	}

	public String[] getProduitTailleDisponible() {
		return produitTailleDisponible;
	}

	public void setProduitTailleDisponible(String[] produitTailleDisponible) {
		this.produitTailleDisponible = produitTailleDisponible;
	}

	public String[] getProduitTailleIndisponible() {
		return produitTailleIndisponible;
	}

	public void setProduitTailleIndisponible(String[] produitTailleIndisponible) {
		this.produitTailleIndisponible = produitTailleIndisponible;
	}

	public String[] getProduitTailleSection() {
		return produitTailleSection;
	}

	public void setProduitTailleSection(String[] produitTailleSection) {
		this.produitTailleSection = produitTailleSection;
	}

	public String[] getProduitImage() {
		return produitImage;
	}

	public void setProduitImage(String[] produitImage) {
		this.produitImage = produitImage;
	}

	public String[] getImageSection() {
		return imageSection;
	}

	public void setImageSection(String[] imageSection) {
		this.imageSection = imageSection;
	}

	public String[] getProduitCategorie() {
		return produitCategorie;
	}

	public void setProduitCategorie(String[] produitCategorie) {
		this.produitCategorie = produitCategorie;
	}

	public String[] getProduitMarque() {
		return produitMarque;
	}

	public void setProduitMarque(String[] produitMarque) {
		this.produitMarque = produitMarque;
	}

	public String[] getProduitNewPrix() {
		return produitNewPrix;
	}

	public void setProduitNewPrix(String[] produitNewPrix) {
		this.produitNewPrix = produitNewPrix;
	}

	public String[] getProduitOldPrix() {
		return produitOldPrix;
	}

	public void setProduitOldPrix(String[] produitOldPrix) {
		this.produitOldPrix = produitOldPrix;
	}

	public String[] getProduitNom() {
		return produitNom;
	}

	public void setProduitNom(String[] produitNom) {
		this.produitNom = produitNom;
	}

	public String[] getProduitGenre() {
		return produitGenre;
	}

	public void setProduitGenre(String[] produitGenre) {
		this.produitGenre = produitGenre;
	}

	public int getNombreProduits() {
		return nombreProduits;
	}

	public void setNombreProduits(int nombreProduits) {
		this.nombreProduits = nombreProduits;
	}

	public String[] getVendeurNom() {
		return vendeurNom;
	}

	public void setVendeurNom(String[] vendeurNom) {
		this.vendeurNom = vendeurNom;
	}

}
