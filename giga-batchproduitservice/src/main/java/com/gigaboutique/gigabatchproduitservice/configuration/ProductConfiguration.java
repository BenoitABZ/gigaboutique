package com.gigaboutique.gigabatchproduitservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("product-configs")
public class ProductConfiguration {

	private String[] produitTailleDisponible;

	private String[] produitTailleIndisponible;

	private String[] produitTailleSection;
	
	private String[] produitImage;
	
	private String[] imageSection;

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



}
