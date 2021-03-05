package com.gigaboutique.gigavendeurservice;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gigaboutique.gigavendeurservice.configuration.SecurityConstant;
import com.gigaboutique.gigavendeurservice.dao.VendeurDao;
import com.gigaboutique.gigavendeurservice.dto.CommentaireDto;
import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.model.CommentaireBean;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
@Transactional
@Rollback(true)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ControllerLayerIntegrationTests {

	@Autowired
	private SecurityConstant sc;

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	VendeurDao vendeurDao;

	private String token;
	
	private VendeurDto vendeur;

	@BeforeAll
	public void setUp() throws Exception {
		
		vendeur = new VendeurDto();
		vendeur.setNom("Zalando SE");
		vendeur.setLogo("https://s3-eu-west-1.amazonaws.com/tpd/screenshots/4f06b8b60000640005121cdc/198x149.png");
		vendeur.setNote("2,4");
		vendeur.setNombreDeCommentaires("243");

		List<CommentaireDto> commentaires = new ArrayList<>();

		CommentaireDto commentaire1 = new CommentaireDto();
		commentaire1.setAuteur("Julie Bgh");
		commentaire1.setDescription("Grande cliente de zalando depuis plusieurs années, je ne peux que regretter l'évolution du service client. Jusqu'à présent, dès que j'avais besoin d'un article, je commençais par regarder sur zalando, ayant déjà abandonné plusieurs sites marchands en raison d'un service client déplorable. Je suis maintenant en train de réfléchir à faire de même pour zalando. En effet, j'ai récemment commandé 2 pulls. Le traitement ayant pris plus de temps que prévu, je suis retournée voir le détail de ma commande et là, je constate qu'un des deux articles a été annulé, sans que j'en sois informé. Je trouve cette pratique particulièrement méprisante vis-à-vis du client. Surtout que l'article annulé étant alors à nouveau en stock. Pourquoi, dès lors, annulé un article, alors que la commande n'est pas encore expédiée et que celui-ci est à nouveau en stock? N'appréciant pas ces pratiques, je tente de trouver sur internet l'adresse mail du service client pour leur demander d'annuler l'intégralité de la commande (toujours pas expédiée). N'arrivant pas à trouver autre chose qu'un numéro de téléphone, je finis par me retourner sur le formulaire réclamation, seul moyen de contacter le service client par écrit (en tout cas facilement trouvable...). Le lendemain matin je reçois une confirmation par mail de l'annulation de la commande. Et le soir, la confirmation de son expédition. J'écris donc à nouveau au service client pour lui faire part de mon incompréhension et de mon agacement. J'ai eu certes une réponse très rapide, mais qui ne m'a absolument pas plus, ni sur le fonds ni sur la forme. Il s'avère que ce dysfonctionnement justifié par une erreur de ma part, puisque je n'ai pas utilisé le bon formulaire, et que le message de confirmation par mail est en fait généré automatiquement par un robot... qui répond donc n'importe quoi (c'est très rassurant et du grand service client !). Et donc en susbtance, je n'ai que ce que je mérite et que par ailleurs, je peux annuler moi-même mes commandes (au passage, je le sais et l'ai déjà fait par le passé, mais sur celle-ci il y avait un bug d'affichage sur l'appli lorsque je voulais annuler...). Bref, au bout de 30 lignes à charge contre la cliente, on me glisse un pauvre code promo de 10%. Ce n'est pas ça qui va me pousser à recommander de sitôt...");
		commentaire1.setNote("1");
		commentaire1.setDateCommentaire("2019-09-25T13:24:54");

		commentaires.add(commentaire1);

		CommentaireDto commentaire2 = new CommentaireDto();
		commentaire2.setAuteur("Doglione");
		commentaire2.setDescription("Parfait, envoi rapide, frais de retour gratuits, et remboursement rapide aussi. Je recommanderai sur ce site.");
		commentaire2.setNote("5");
		commentaire2.setDateCommentaire("2016-09-06T16:29:11");

		commentaires.add(commentaire2);
		
		CommentaireDto commentaire3 = new CommentaireDto();
		commentaire3.setAuteur("Elias");
		commentaire3.setDescription("Que ce soit la possibilité d'annulation de commande sous 24H avant la validation,la gamme de choix du pas cher au cher,de la qualité médiocre à très bonne,tout dépend du prix,ne vous attendez pas non plus à pas cher avec la qualité Armani,bref vous avez de tout,je n'ai pas encore testé le service retour,etant donné que je me fie aux avis ,je suis tjrs tombé sur le bon choix,bref un de mes sites préférés avec Spartoo,avec la gratuité et la rapidité d'envoi.");
		commentaire3.setNote("5");
		commentaire3.setDateCommentaire("2016-08-19T22:11:54");

		commentaires.add(commentaire3);

		vendeur.setCommentaires(commentaires);

		long expiration = Long.parseLong(sc.getExpiration());

		String secret = sc.getSecret();

		List<String> roles = new ArrayList<>();

		roles.add("USER");

		token = Jwts.builder().setSubject("test")
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.claim("roles", roles).compact();

	}
	
	@Test
	public void addVendeur() throws Exception {
		
		ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
		String vendeurJson = ow.writeValueAsString(vendeur);
		
		mvc.perform(post("/vendeur/add")
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.content(vendeurJson)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.id", notNullValue()))
		        .andExpect(jsonPath("$.commentaires", hasSize(3)))
		        .andExpect(status().isCreated());
				
	}
	
	@Test
	public void getVendeur() throws Exception {
		
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

       int id = vendeurBean.getId();
		
		mvc.perform(get("/vendeur/get/" + id)
				.header(sc.getHeader(), sc.getTokenPrefix() + "" + token)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.id", is(id)))
		        .andExpect(status().is2xxSuccessful());
				
	}

}
