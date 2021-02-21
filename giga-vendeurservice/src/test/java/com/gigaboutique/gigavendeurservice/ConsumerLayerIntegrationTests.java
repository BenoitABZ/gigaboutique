package com.gigaboutique.gigavendeurservice;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.gigaboutique.gigavendeurservice.dao.CommentaireDao;
import com.gigaboutique.gigavendeurservice.dao.VendeurDao;
import com.gigaboutique.gigavendeurservice.model.CommentaireBean;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;

@SpringBootTest
@Transactional
@Rollback(true)
public class ConsumerLayerIntegrationTests {

	@Autowired
	VendeurDao vendeurDao;

	@Autowired
	CommentaireDao commentaireDao;

	@Test
	public void addVendeurTest() {

		VendeurBean vendeur = new VendeurBean();
		vendeur.setNom("vendeur");
		vendeur.setLogo("adresseLogo");
		vendeur.setNote("note");

		List<CommentaireBean> commentaires = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("note1");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeur);

		commentaires.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("note2");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeur);

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		vendeurDao.save(vendeur);

		VendeurBean vendeurTest = vendeurDao.findById(vendeur.getId()).get();

		List<CommentaireBean> commentairesTest = vendeurTest.getCommentaires();

		assertTrue(commentairesTest.size() == 2);

	}

	@Test
	public void updateVendeurTest() {

		VendeurBean vendeur = new VendeurBean();
		vendeur.setNom("vendeur");
		vendeur.setLogo("adresseLogo");
		vendeur.setNote("note");

		List<CommentaireBean> commentaires = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("note1");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeur);

		commentaires.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("note2");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeur);

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		vendeurDao.save(vendeur);

		VendeurBean vendeurTest = vendeurDao.findById(vendeur.getId()).get();

		commentaireDao.deleteAllInBatch();
		
		CommentaireBean commentaire3 = new CommentaireBean();
		commentaire3.setAuteur("auteur1");
		commentaire3.setDescription("description1");
		commentaire3.setNote("note1");
		commentaire3.setDateCommentaire(LocalDate.now());
		commentaire3.setVendeur(vendeur);
		
		List<CommentaireBean> commentairesToAdd = new ArrayList<>();		
		commentairesToAdd.add(commentaire3);

		vendeurTest.setCommentaires(commentairesToAdd);
		
		vendeurDao.save(vendeurTest);
		
		VendeurBean vendeurTest2 = vendeurDao.findById(vendeur.getId()).get();
		
		List<CommentaireBean> commentairesTest2 = vendeurTest2.getCommentaires();

		assertTrue(commentairesTest2.size() == 1);
	}
}
