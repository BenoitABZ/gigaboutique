package com.gigaboutique.gigaproductservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Vendeur")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = VendeurBean.class)
public class VendeurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_vendeur")
	private Integer id;

	@OneToMany(mappedBy = "vendeur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ProduitBean> produits = new ArrayList<>();

}
