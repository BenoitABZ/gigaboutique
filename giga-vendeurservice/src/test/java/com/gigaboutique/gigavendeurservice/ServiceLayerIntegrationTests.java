package com.gigaboutique.gigavendeurservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.gigaboutique.gigavendeurservice.dao.VendeurDao;
import com.gigaboutique.gigavendeurservice.dto.CommentaireDto;
import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.exception.TechnicalException;
import com.gigaboutique.gigavendeurservice.exception.VendeurException;
import com.gigaboutique.gigavendeurservice.model.CommentaireBean;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;
import com.gigaboutique.gigavendeurservice.service.MapVendeurDtoService;
import com.gigaboutique.gigavendeurservice.service.VendeurService;

import javassist.NotFoundException;

@SpringBootTest
@Transactional
@Rollback(true)
public class ServiceLayerIntegrationTests {

	@Autowired
	MapVendeurDtoService mapVendeurDtoService;

	@Autowired
	VendeurService vendeurService;

	@Autowired
	VendeurDao vendeurDao;

	@Test
	public void convertToUtilisateurDtoTest() throws VendeurException {

		VendeurBean vendeur = new VendeurBean();
		vendeur.setNom("vendeur");
		vendeur.setLogo("adresseLogo");
		vendeur.setNote("4");
		vendeur.setNombreDeCommentaires("42");

		List<CommentaireBean> commentaires = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeur);

		commentaires.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeur);

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		vendeurDao.save(vendeur);

		VendeurBean vendeurTest = vendeurDao.findById(vendeur.getId()).get();

		VendeurDto vendeurDto = mapVendeurDtoService.convertToVendeurDto(vendeurTest);

		assertTrue(vendeurTest.getId() == vendeurDto.getId());
		assertTrue(vendeurDto.getCommentaires().size() == 2);
		assertEquals(LocalDate.now().toString(), vendeurDto.getCommentaires().get(0).getDateCommentaire());

	}

	@Test
	public void convertToUtilisateurBeanTest() throws VendeurException {

		VendeurDto vendeur = new VendeurDto();
		vendeur.setNom("vendeur");
		vendeur.setLogo("adresseLogo");
		vendeur.setNote("4");
		vendeur.setNombreDeCommentaires("42");

		List<CommentaireDto> commentaires = new ArrayList<>();

		CommentaireDto commentaire1 = new CommentaireDto();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire("2013-09-07T13:37:00");

		commentaires.add(commentaire1);

		CommentaireDto commentaire2 = new CommentaireDto();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire("2015-09-07T13:38:00");

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		VendeurBean vendeurTest = mapVendeurDtoService.convertToVendeurBean(vendeur);

		assertEquals("4", vendeurTest.getNote());
		assertTrue(vendeurTest.getCommentaires().size() == 2);
		assertEquals("07/09/2013", vendeurTest.getCommentaires().get(0).getDateCommentaire());

	}

	@Test
	public void updateToUtilisateurBeanTest() throws VendeurException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setNom("vendeur");
		vendeurBean.setLogo("adresseLogo");
		vendeurBean.setNote("5");
		vendeurBean.setNombreDeCommentaires("42");

		List<CommentaireBean> commentaires1 = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeurBean);

		commentaires1.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeurBean);

		commentaires1.add(commentaire2);

		vendeurBean.setCommentaires(commentaires1);

		vendeurDao.save(vendeurBean);

		VendeurDto vendeurDto = new VendeurDto();
		vendeurDto.setNom("vendeur");
		vendeurDto.setLogo("adresseLogo");
		vendeurDto.setNote("4");
		vendeurDto.setNombreDeCommentaires("62");

		List<CommentaireDto> commentaires2 = new ArrayList<>();

		CommentaireDto commentaire3 = new CommentaireDto();
		commentaire3.setAuteur("auteur3");
		commentaire3.setDescription("description3");
		commentaire3.setNote("4.5");
		commentaire3.setDateCommentaire("2016-09-07T13:37:00");

		commentaires2.add(commentaire3);

		vendeurDto.setCommentaires(commentaires2);

		VendeurBean vendeurTest = mapVendeurDtoService.updateToVendeurBean(vendeurBean, vendeurDto);

		assertEquals("4", vendeurTest.getNote());
		assertTrue(vendeurTest.getCommentaires().size() == 1);
		assertEquals("07/09/2016", vendeurTest.getCommentaires().get(0).getDateCommentaire());

	}

	@Test
	public void addVendeurTest() throws VendeurException, TechnicalException, NotFoundException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setNom("vendeur");
		vendeurBean.setLogo("adresseLogo");
		vendeurBean.setNote("5");

		List<CommentaireBean> commentaires1 = new ArrayList<>();

		CommentaireBean commentaire1 = new CommentaireBean();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("description1");
		commentaire1.setNote("4.5");
		commentaire1.setDateCommentaire(LocalDate.now());
		commentaire1.setVendeur(vendeurBean);

		commentaires1.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire(LocalDate.now());
		commentaire2.setVendeur(vendeurBean);

		commentaires1.add(commentaire2);

		vendeurBean.setCommentaires(commentaires1);

		vendeurDao.save(vendeurBean);

		VendeurDto vendeurDto = new VendeurDto();
		vendeurDto.setNom("vendeur");
		vendeurDto.setLogo("adresseLogo");
		vendeurDto.setNote("4");

		List<CommentaireDto> commentaires2 = new ArrayList<>();

		CommentaireDto commentaire3 = new CommentaireDto();
		commentaire3.setAuteur("auteur3");
		commentaire3.setDescription("description3");
		commentaire3.setNote("4.5");
		commentaire3.setDateCommentaire("2016-09-07T13:37:00");

		commentaires2.add(commentaire3);

		vendeurDto.setCommentaires(commentaires2);

		VendeurDto vendeurDtoTest1 = vendeurService.addVendeur(vendeurDto);

		VendeurDto vendeurDtoTest2 = vendeurService.getVendeur(vendeurDtoTest1.getId());

		assertTrue(vendeurDtoTest1.getId() == vendeurBean.getId());

		assertNotNull(vendeurDtoTest2);

		// MapVendeurDtoService mockedMap = Mockito.mock(MapVendeurDtoService.class);

		// vendeurService.addVendeur(vendeurDto);

		// Mockito.verify(mockedMap).updateToVendeurBean(Mockito.any(VendeurBean.class), Mockito.any(VendeurDto.class));

	}
}
