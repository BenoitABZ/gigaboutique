package com.gigaboutique.gigaproductservice.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Produit")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProduitBean.class)
public class ProduitBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "produit_id_produit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_produit")
	private Integer idProduit;

	@Column(name = "nom")
	private String nom;

	@Column(name = "prix")
	@Digits(fraction = 2, integer = 5, message = "2 digits max")
	private Double prix;

	@Column(name = "promotion")
	@Min(0)
	@Max(100)
	private Integer promotion;

	@Column(name = "marque")
	private String marque;

	@Column(name = "maj")
	private Boolean maj;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_vendeur")
	private VendeurBean vendeur;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categorie")
	private CategorieBean categorie;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_genre")
	private GenreBean genre;

	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
	private List<ImageProduitBean> images = new ArrayList<>();

	@OneToMany(mappedBy = "produit", fetch = FetchType.EAGER)
	private List<TailleProduit> taillesProduits = new ArrayList<>();

	public Integer getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Integer idProduit) {
		this.idProduit = idProduit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getPromotion() {
		return promotion;
	}

	public void setPromotion(Integer promotion) {
		this.promotion = promotion;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public VendeurBean getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurBean vendeur) {
		this.vendeur = vendeur;
	}

	public CategorieBean getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieBean categorie) {
		this.categorie = categorie;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

	public List<ImageProduitBean> getImages() {
		return images;
	}

	public void setImages(List<ImageProduitBean> images) {
		this.images = images;
	}

	public List<TailleProduit> getTaillesProduits() {
		return taillesProduits;
	}

	public void setTaillesProduits(List<TailleProduit> taillesProduits) {
		this.taillesProduits = taillesProduits;
	}

	public Boolean getMaj() {
		return maj;
	}

	public void setMaj(Boolean maj) {
		this.maj = maj;
	}

}
