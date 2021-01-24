package com.gigaboutique.gigaproductservice;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gigaboutique.gigaproductservice.dao.CategorieDao;
import com.gigaboutique.gigaproductservice.dao.GenreDao;
import com.gigaboutique.gigaproductservice.dao.ImageProduitDao;
import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.TailleDao;
import com.gigaboutique.gigaproductservice.dao.TailleProduitDao;
import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.model.CategorieBean;
import com.gigaboutique.gigaproductservice.model.GenreBean;
import com.gigaboutique.gigaproductservice.model.ImageProduitBean;
import com.gigaboutique.gigaproductservice.model.ProduitBean;
import com.gigaboutique.gigaproductservice.model.TailleBean;
import com.gigaboutique.gigaproductservice.model.TailleProduit;
import com.gigaboutique.gigaproductservice.model.VendeurBean;

@SpringBootTest
public class ConsumerIntegrationTests {
	
	@Autowired
	CategorieDao categorieDao;
	
	@Autowired
	TailleDao tailleDao;
	
	@Autowired
	ImageProduitDao imageProduitDao;
	
	@Autowired
	GenreDao genreDao;
	
	@Autowired
	VendeurDao vendeurDao;
	
	@Autowired
	ProduitDao produitDao;
	
	@Autowired
	TailleProduitDao tailleProduitDao;

	
	@Test
	public void addProduitToDbTest() {
		
		VendeurBean vendeur = new VendeurBean();
		vendeur.setId(1);
		vendeurDao.save(vendeur);
		
		CategorieBean categorie = new CategorieBean();
		categorie.setCategorie("manteau");
		categorieDao.save(categorie);
		
		GenreBean genre = new GenreBean();
		genre.setGenre("homme");
		genreDao.save(genre);
		
		TailleBean taille = new TailleBean();
		taille.setTaille("43");
		tailleDao.save(taille);
		
		ImageProduitBean image = new ImageProduitBean();
		image.setAdresseWeb("test");
		imageProduitDao.save(image);
		
		List<ImageProduitBean> images = new ArrayList<>();		
		images.add(image);
		
		ProduitBean produit = new ProduitBean();
		
		produit.setMarque("nike");
		produit.setNom("airmax");
		produit.setPrix(85.20);
		produit.setPromotion(50);
		produit.setVendeur(vendeur);
		produit.setGenre(genre);
		produit.setCategorie(categorie);
		produit.setImages(images);
		
		produitDao.save(produit);
		
		
		TailleProduit tailleProduit = new TailleProduit();
		tailleProduit.setProduit(produit);
		tailleProduit.setTaille(taille);
		
		produit.getTaillesProduits().add(tailleProduit);
		taille.getTaillesProduits().add(tailleProduit);
		
		tailleProduitDao.save(tailleProduit);
	    
		
	}
	
	

}
