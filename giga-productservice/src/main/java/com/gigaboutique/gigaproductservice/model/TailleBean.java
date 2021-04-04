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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Taille")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TailleBean.class)
public class TailleBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "identifier", sequenceName = "taille_id_taille_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	@Column(name = "id_taille")
	private Integer idTaille;

	@Column(name = "taille")
	private String taille;

	@OneToMany(mappedBy = "taille", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<TailleProduitBean> taillesProduits = new ArrayList<>();

	public Integer getIdTaille() {
		return idTaille;
	}

	public void setIdTaille(Integer idTaille) {
		this.idTaille = idTaille;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public List<TailleProduitBean> getTaillesProduits() {
		return taillesProduits;
	}

	public void setTaillesProduits(List<TailleProduitBean> taillesProduits) {
		this.taillesProduits = taillesProduits;
	}

}
