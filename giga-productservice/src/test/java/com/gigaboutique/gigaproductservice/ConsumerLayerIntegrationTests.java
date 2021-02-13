package com.gigaboutique.gigaproductservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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
@Transactional
@Rollback(true)
public class ConsumerLayerIntegrationTests {

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
		categorie.setCategorie("chaussure");
		categorieDao.save(categorie);

		GenreBean genre = new GenreBean();
		genre.setGenre("homme");
		genreDao.save(genre);

		TailleBean taille = new TailleBean();
		taille.setTaille("43");
		tailleDao.save(taille);

		ProduitBean produit = new ProduitBean();

		produit.setMarque("nike");
		produit.setNom("airmax");
		produit.setPrix(85.20);
		produit.setPromotion(50);
		produit.setVendeur(vendeur);
		produit.setGenre(genre);
		produit.setCategorie(categorie);

		produitDao.save(produit);

		ImageProduitBean image = new ImageProduitBean();
		image.setAdresseWeb("test");
		image.setProduit(produit);
		imageProduitDao.save(image);

		vendeur.getProduits().add(produit);

		vendeurDao.save(vendeur);

		TailleProduit tailleProduit = new TailleProduit(true, produit, taille);
		tailleProduit.setProduit(produit);
		tailleProduit.setTaille(taille);

		produit.getTaillesProduits().add(tailleProduit);
		taille.getTaillesProduits().add(tailleProduit);

		tailleProduitDao.save(tailleProduit);

		VendeurBean vendeurTest = vendeurDao.findById(1);

		List<ProduitBean> produits = vendeurTest.getProduits();

		List<TailleProduit> taillesProduits = produits.get(0).getTaillesProduits();

		assertEquals("nike", produits.get(0).getMarque());

		assertEquals("homme", produits.get(0).getGenre().getGenre());

		assertEquals("chaussure", produits.get(0).getCategorie().getCategorie());

		assertEquals("43", taillesProduits.get(0).getTaille().getTaille());

	}

}
