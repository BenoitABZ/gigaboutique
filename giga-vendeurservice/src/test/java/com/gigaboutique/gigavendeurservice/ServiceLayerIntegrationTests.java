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
		commentaire1.setDateCommentaire(LocalDate.parse("2013-09-07"));
		commentaire1.setVendeur(vendeur);

		commentaires.add(commentaire1);

		CommentaireBean commentaire2 = new CommentaireBean();
		commentaire2.setAuteur("auteur2");
		commentaire2.setDescription("description2");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire(LocalDate.parse("2013-09-07"));
		commentaire2.setVendeur(vendeur);

		commentaires.add(commentaire2);

		vendeur.setCommentaires(commentaires);

		vendeurDao.save(vendeur);

		VendeurBean vendeurTest = vendeurDao.findById(vendeur.getId()).get();

		VendeurDto vendeurDto = mapVendeurDtoService.convertToVendeurDto(vendeurTest);

		assertTrue(vendeurTest.getId() == vendeurDto.getId());
		assertTrue(vendeurDto.getCommentaires().size() == 2);
		assertEquals("07/09/2013", vendeurDto.getCommentaires().get(0).getDateCommentaire());

	}

	@Test
	public void convertToVendeurBeanTest() throws VendeurException {

		VendeurDto vendeur = new VendeurDto();
		vendeur.setNom("vendeur");
		vendeur.setLogo("https://s3-eu-west-1.amazonaws.com/tpd/screenshots/4f06b8b60000640005121cdc/198x149.png");
		vendeur.setNote("4");
		vendeur.setNombreDeCommentaires("42");

		List<CommentaireDto> commentaires = new ArrayList<>();

		CommentaireDto commentaire1 = new CommentaireDto();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("grande cliente de zalando depuis plusieurs années, je ne peux que regretter l'évolution du service client. Jusqu'à présent, dès que j'avais besoin d'un article, je commençais par regarder sur zalando, ayant déjà abandonné plusieurs sites marchands en raison d'un service client déplorable. Je suis maintenant en train de réfléchir à faire de même pour zalando. En effet, j'ai récemment commandé 2 pulls. Le traitement ayant pris plus de temps que prévu, je suis retournée voir le détail de ma commande et là, je constate qu'un des deux articles a été annulé, sans que j'en sois informé. Je trouve cette pratique particulièrement méprisante vis-à-vis du client. Surtout que l'article annulé étant alors à nouveau en stock. Pourquoi, dès lors, annulé un article, alors que la commande n'est pas encore expédiée et que celui-ci est à nouveau en stock? N'appréciant pas ces pratiques, je tente de trouver sur internet l'adresse mail du service client pour leur demander d'annuler l'intégralité de la commande (toujours pas expédiée). N'arrivant pas à trouver autre chose qu'un numéro de téléphone, je finis par me retourner sur le formulaire réclamation, seul moyen de contacter le service client par écrit (en tout cas facilement trouvable...). Le lendemain matin je reçois une confirmation par mail de l'annulation de la commande. Et le soir, la confirmation de son expédition. J'écris donc à nouveau au service client pour lui faire part de mon incompréhension et de mon agacement. J'ai eu certes une réponse très rapide, mais qui ne m'a absolument pas plus, ni sur le fonds ni sur la forme. Il s'avère que ce dysfonctionnement justifié par une erreur de ma part, puisque je n'ai pas utilisé le bon formulaire, et que le message de confirmation par mail est en fait généré automatiquement par un robot... qui répond donc n'importe quoi (c'est très rassurant et du grand service client !). Et donc en susbtance, je n'ai que ce que je mérite et que par ailleurs, je peux annuler moi-même mes commandes (au passage, je le sais et l'ai déjà fait par le passé, mais sur celle-ci il y avait un bug d'affichage sur l'appli lorsque je voulais annuler...). Bref, au bout de 30 lignes à charge contre la cliente, on me glisse un pauvre code promo de 10%. Ce n'est pas ça qui va me pousser à recommander de sitôt...");
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

		vendeurDao.save(vendeurTest);

		VendeurBean vendeurTest2 = vendeurDao.findById(vendeurTest.getId()).get();

		assertEquals("grande cliente de zalando depuis plusieurs années, je ne peux que regretter l'évolution du service client. Jusqu'à présent, dès que j'avais besoin d'un article, je commençais par regarder sur zalando, ayant déjà abandonné plusieurs sites marchands en raison d'un service client déplorable. Je suis maintenant en train de réfléchir à faire de même pour zalando. En effet, j'ai récemment commandé 2 pulls. Le traitement ayant pris plus de temps que prévu, je suis retournée voir le détail de ma commande et là, je constate qu'un des deux articles a été annulé, sans que j'en sois informé. Je trouve cette pratique particulièrement méprisante vis-à-vis du client. Surtout que l'article annulé étant alors à nouveau en stock. Pourquoi, dès lors, annulé un article, alors que la commande n'est pas encore expédiée et que celui-ci est à nouveau en stock? N'appréciant pas ces pratiques, je tente de trouver sur internet l'adresse mail du service client pour leur demander d'annuler l'intégralité de la commande (toujours pas expédiée). N'arrivant pas à trouver autre chose qu'un numéro de téléphone, je finis par me retourner sur le formulaire réclamation, seul moyen de contacter le service client par écrit (en tout cas facilement trouvable...). Le lendemain matin je reçois une confirmation par mail de l'annulation de la commande. Et le soir, la confirmation de son expédition. J'écris donc à nouveau au service client pour lui faire part de mon incompréhension et de mon agacement. J'ai eu certes une réponse très rapide, mais qui ne m'a absolument pas plus, ni sur le fonds ni sur la forme. Il s'avère que ce dysfonctionnement justifié par une erreur de ma part, puisque je n'ai pas utilisé le bon formulaire, et que le message de confirmation par mail est en fait généré automatiquement par un robot... qui répond donc n'importe quoi (c'est très rassurant et du grand service client !). Et donc en susbtance, je n'ai que ce que je mérite et que par ailleurs, je peux annuler moi-même mes commandes (au passage, je le sais et l'ai déjà fait par le passé, mais sur celle-ci il y avait un bug d'affichage sur l'appli lorsque je voulais annuler...). Bref, au bout de 30 lignes à charge contre la cliente, on me glisse un pauvre code promo de 10%. Ce n'est pas ça qui va me pousser à recommander de sitôt...",
				      vendeurTest2.getCommentaires().get(0).getDescription());
		assertEquals("4", vendeurTest.getNote());
		assertTrue(vendeurTest.getCommentaires().size() == 2);
		assertEquals("2013-09-07", vendeurTest.getCommentaires().get(0).getDateCommentaire().toString());

	}

	@Test
	public void convertToVendeurBeanTest2() throws VendeurException {

		VendeurDto vendeur = new VendeurDto();
		vendeur.setNom("vendeur");
		vendeur.setLogo("https://s3-eu-west-1.amazonaws.com/tpd/screenshots/4f06b8b60000640005121cdc/198x149.png");
		vendeur.setNote("4");
		vendeur.setNombreDeCommentaires("42");

		List<CommentaireDto> commentaires = new ArrayList<>();

		CommentaireDto commentaire1 = new CommentaireDto();
		commentaire1.setAuteur("auteur1");
		commentaire1.setDescription("grande cliente de zalando depuis plusieurs années, je ne peux que regretter l'évolution du service client. Jusqu'à présent, dès que j'avais besoin d'un article, je commençais par regarder sur zalando, ayant déjà abandonné plusieurs sites marchands en raison d'un service client déplorable. Je suis maintenant en train de réfléchir à faire de même pour zalando. En effet, j'ai récemment commandé 2 pulls. Le traitement ayant pris plus de temps que prévu, je suis retournée voir le détail de ma commande et là, je constate qu'un des deux articles a été annulé, sans que j'en sois informé. Je trouve cette pratique particulièrement méprisante vis-à-vis du client. Surtout que l'article annulé étant alors à nouveau en stock. Pourquoi, dès lors, annulé un article, alors que la commande n'est pas encore expédiée et que celui-ci est à nouveau en stock? N'appréciant pas ces pratiques, je tente de trouver sur internet l'adresse mail du service client pour leur demander d'annuler l'intégralité de la commande (toujours pas expédiée). N'arrivant pas à trouver autre chose qu'un numéro de téléphone, je finis par me retourner sur le formulaire réclamation, seul moyen de contacter le service client par écrit (en tout cas facilement trouvable...). Le lendemain matin je reçois une confirmation par mail de l'annulation de la commande. Et le soir, la confirmation de son expédition. J'écris donc à nouveau au service client pour lui faire part de mon incompréhension et de mon agacement. J'ai eu certes une réponse très rapide, mais qui ne m'a absolument pas plus, ni sur le fonds ni sur la forme. Il s'avère que ce dysfonctionnement justifié par une erreur de ma part, puisque je n'ai pas utilisé le bon formulaire, et que le message de confirmation par mail est en fait généré automatiquement par un robot... qui répond donc n'importe quoi (c'est très rassurant et du grand service client !). Et donc en susbtance, je n'ai que ce que je mérite et que par ailleurs, je peux annuler moi-même mes commandes (au passage, je le sais et l'ai déjà fait par le passé, mais sur celle-ci il y avait un bug d'affichage sur l'appli lorsque je voulais annuler...). Bref, au bout de 30 lignes à charge contre la cliente, on me glisse un pauvre code promo de 10%. Ce n'est pas ça qui va me pousser à recommander de sitôt...");
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

		vendeurDao.save(vendeurTest);

		VendeurBean vendeurTest2 = vendeurDao.findById(vendeurTest.getId()).get();

		assertEquals("grande cliente de zalando depuis plusieurs années, je ne peux que regretter l'évolution du service client. Jusqu'à présent, dès que j'avais besoin d'un article, je commençais par regarder sur zalando, ayant déjà abandonné plusieurs sites marchands en raison d'un service client déplorable. Je suis maintenant en train de réfléchir à faire de même pour zalando. En effet, j'ai récemment commandé 2 pulls. Le traitement ayant pris plus de temps que prévu, je suis retournée voir le détail de ma commande et là, je constate qu'un des deux articles a été annulé, sans que j'en sois informé. Je trouve cette pratique particulièrement méprisante vis-à-vis du client. Surtout que l'article annulé étant alors à nouveau en stock. Pourquoi, dès lors, annulé un article, alors que la commande n'est pas encore expédiée et que celui-ci est à nouveau en stock? N'appréciant pas ces pratiques, je tente de trouver sur internet l'adresse mail du service client pour leur demander d'annuler l'intégralité de la commande (toujours pas expédiée). N'arrivant pas à trouver autre chose qu'un numéro de téléphone, je finis par me retourner sur le formulaire réclamation, seul moyen de contacter le service client par écrit (en tout cas facilement trouvable...). Le lendemain matin je reçois une confirmation par mail de l'annulation de la commande. Et le soir, la confirmation de son expédition. J'écris donc à nouveau au service client pour lui faire part de mon incompréhension et de mon agacement. J'ai eu certes une réponse très rapide, mais qui ne m'a absolument pas plus, ni sur le fonds ni sur la forme. Il s'avère que ce dysfonctionnement justifié par une erreur de ma part, puisque je n'ai pas utilisé le bon formulaire, et que le message de confirmation par mail est en fait généré automatiquement par un robot... qui répond donc n'importe quoi (c'est très rassurant et du grand service client !). Et donc en susbtance, je n'ai que ce que je mérite et que par ailleurs, je peux annuler moi-même mes commandes (au passage, je le sais et l'ai déjà fait par le passé, mais sur celle-ci il y avait un bug d'affichage sur l'appli lorsque je voulais annuler...). Bref, au bout de 30 lignes à charge contre la cliente, on me glisse un pauvre code promo de 10%. Ce n'est pas ça qui va me pousser à recommander de sitôt...",
				     vendeurTest2.getCommentaires().get(0).getDescription());

	}

	@Test
	public void updateToVendeurBeanTest() throws VendeurException {

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
		
		vendeurDao.save(vendeurTest);
		
		VendeurBean vendeurTest2 = vendeurDao.findByNom("vendeur");
		
        assertNotNull(vendeurTest2);
		assertEquals("4", vendeurTest.getNote());
		assertTrue(vendeurTest.getCommentaires().size() == 1);
		assertEquals("2016-09-07", vendeurTest.getCommentaires().get(0).getDateCommentaire().toString());

	}

	@Test
	public void addVendeurTest() throws VendeurException, TechnicalException, NotFoundException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setNom("vendeur");
		vendeurBean.setLogo("adresseLogo");
		vendeurBean.setNote("5");
		vendeurBean.setNombreDeCommentaires("62");

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

		VendeurDto vendeurDtoTest1 = vendeurService.addVendeur(vendeurDto);

		VendeurDto vendeurDtoTest2 = vendeurService.getVendeur(vendeurDtoTest1.getId());

		assertTrue(vendeurDtoTest1.getId() == vendeurBean.getId());

		assertNotNull(vendeurDtoTest2);

		// MapVendeurDtoService mockedMap = Mockito.mock(MapVendeurDtoService.class);

		// vendeurService.addVendeur(vendeurDto);

		// Mockito.verify(mockedMap).updateToVendeurBean(Mockito.any(VendeurBean.class),
		// Mockito.any(VendeurDto.class));

	}
}
