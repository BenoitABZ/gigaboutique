package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ImageProduit")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ImageProduitBean.class)
public class ImageProduitBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "imageproduit_id_image_produit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_image_produit")
	private Integer idImageProduit;

	@Column(name = "adresse_web")
	private String adresseWeb;

	@ManyToOne
	@JoinColumn(name = "id_produit")
	private ProduitBean produit;

	public Integer getIdImageProduit() {
		return idImageProduit;
	}

	public void setIdImageProduit(Integer idImageProduit) {
		this.idImageProduit = idImageProduit;
	}

	public String getAdresseWeb() {
		return adresseWeb;
	}

	public void setAdresseWeb(String adresseWeb) {
		this.adresseWeb = adresseWeb;
	}

	public ProduitBean getProduit() {
		return produit;
	}

	public void setProduit(ProduitBean produit) {
		this.produit = produit;
	}

}
