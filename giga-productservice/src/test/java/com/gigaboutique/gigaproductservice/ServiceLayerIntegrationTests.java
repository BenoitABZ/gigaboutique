package com.gigaboutique.gigaproductservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.gigaboutique.gigaproductservice.dao.CategorieDao;
import com.gigaboutique.gigaproductservice.dao.GenreDao;
import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.dto.CritereDto;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.exception.ProduitException;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.CategorieBean;
import com.gigaboutique.gigaproductservice.model.GenreBean;
import com.gigaboutique.gigaproductservice.model.ProduitBean;
import com.gigaboutique.gigaproductservice.model.TailleProduitBean;
import com.gigaboutique.gigaproductservice.model.VendeurBean;
import com.gigaboutique.gigaproductservice.service.CategorieService;
import com.gigaboutique.gigaproductservice.service.MapProduitDtoService;
import com.gigaboutique.gigaproductservice.service.ProduitService;

import javassist.NotFoundException;

@SpringBootTest
@Transactional
@Rollback(true)
public class ServiceLayerIntegrationTests {
/*
	@Autowired
	ProduitService produitService;

	@Autowired
	MapProduitDtoService mapProduitDtoService;

	@Autowired
	CategorieService categorieService;

	@Autowired
	VendeurDao vendeurDao;

	@Autowired
	ProduitDao produitDao;

	@Autowired
	CategorieDao categorieDao;

	@Autowired
	GenreDao genreDao;

	@Test
	public void mapDtoProduitServiceTest_convertToProduitBean_Test() throws ProduitException, TechnicalException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setId(1);
		vendeurDao.save(vendeurBean);

		ProduitDto produitDto = new ProduitDto();
		produitDto.setCategorie("pantalon");
		produitDto.setGenre("homme");
		produitDto.setIdVendeur(vendeurBean.getId());
		produitDto.setMarque("tommy");
		produitDto.setNom("pantalon tommy 31");
		produitDto.setPrix("85,50");
		produitDto.setPromotion("50");
		produitDto.setNewPrix(null);

		List<String> adressesWebImages = new ArrayList<>();
		adressesWebImages.add("adressetest");

		produitDto.setImages(adressesWebImages);

		Map<String, Boolean> mapTaillesDispos = new HashMap<>();
		mapTaillesDispos.put("44", true);

		produitDto.setTailles(mapTaillesDispos);

		ProduitBean produitBean = mapProduitDtoService.convertToProduitBean(produitDto);

		assertNotNull(produitBean);

		assertEquals("TOMMY", produitBean.getMarque());

		CategorieBean categorieBean = categorieDao.findById(produitBean.getCategorie().getIdCategorie()).get();

		assertEquals("pantalon", categorieBean.getCategorie());

		GenreBean genreBean = genreDao.findById(produitBean.getGenre().getIdGenre()).get();

		assertEquals("homme", genreBean.getGenre());

		String taille = produitBean.getTaillesProduits().get(0).getTaille().getTaille();

		assertEquals("44", taille);

	}

	@Test
	public void addProduit_add_Test() throws ProduitException, TechnicalException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setId(1);
		vendeurDao.save(vendeurBean);

		ProduitDto produitDto = new ProduitDto();
		produitDto.setCategorie("mom");
		produitDto.setGenre("homme");
		produitDto.setIdVendeur(vendeurBean.getId());
		produitDto.setMarque("tommy");
		produitDto.setNom("pantalon tommy 31");
		produitDto.setPrix("85,50");
		produitDto.setPromotion("50");
		produitDto.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages = new ArrayList<>();
		adressesWebImages.add("adressetest");

		produitDto.setImages(adressesWebImages);

		Map<String, Boolean> mapTaillesDispos = new HashMap<>();
		mapTaillesDispos.put("44", true);

		produitDto.setTailles(mapTaillesDispos);

		produitService.addProduit(produitDto);

		ProduitBean produitBean = produitDao.findByNom(produitDto.getNom());

		assertTrue(produitBean.getPrix() == 85.50);
		assertTrue(produitBean.getPromotion() == 50);
		assertEquals("Pantalons/Jeans", produitBean.getCategorie().getCategorie());
		assertEquals("Homme", produitBean.getGenre().getGenre());

	}

	@Test
	public void addProduit_update_Test() throws ProduitException, TechnicalException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setId(1);
		vendeurDao.save(vendeurBean);

		ProduitDto produitDto1 = new ProduitDto();
		produitDto1.setCategorie("mom");
		produitDto1.setGenre("homme");
		produitDto1.setIdVendeur(vendeurBean.getId());
		produitDto1.setMarque("tommy");
		produitDto1.setNom("pantalon tommy 31");
		produitDto1.setPrix("85,50");
		produitDto1.setPromotion("50");
		produitDto1.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages1 = new ArrayList<>();
		adressesWebImages1.add("adressetest");

		produitDto1.setImages(adressesWebImages1);

		Map<String, Boolean> mapTaillesDispos1 = new HashMap<>();
		mapTaillesDispos1.put("44", true);

		produitDto1.setTailles(mapTaillesDispos1);

		produitService.addProduit(produitDto1);

		ProduitBean produitBean1 = produitDao.findByNom(produitDto1.getNom());

		ProduitDto produitDto2 = new ProduitDto();
		produitDto2.setCategorie("mom");
		produitDto2.setGenre("homme");
		produitDto2.setIdVendeur(vendeurBean.getId());
		produitDto2.setMarque("tommy");
		produitDto2.setNom("pantalon tommy 31");
		produitDto2.setPrix("85");
		produitDto2.setPromotion("30");
		produitDto2.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages2 = new ArrayList<>();
		adressesWebImages2.add("adressetest");

		produitDto2.setImages(adressesWebImages2);

		Map<String, Boolean> mapTaillesDispos2 = new HashMap<>();
		mapTaillesDispos2.put("44", false);

		produitDto2.setTailles(mapTaillesDispos2);

		produitService.addProduit(produitDto2);

		ProduitBean produitBean2 = produitDao.findByNom(produitDto2.getNom());

		assertEquals(produitBean1.getIdProduit(), produitBean2.getIdProduit());

		List<TailleProduitBean> taillesProduits = produitBean2.getTaillesProduits();

		TailleProduitBean tailleProduitBean = taillesProduits.get(0);

		assertTrue(tailleProduitBean.isDisponibilite() == false);
		assertTrue(!produitBean2.getImages().isEmpty());

	}

	@Test
	public void getProduitsByCriteria() throws ProduitException, NotFoundException, TechnicalException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setId(1);
		vendeurDao.save(vendeurBean);

		ProduitDto produitDto1 = new ProduitDto();
		produitDto1.setCategorie("jean");
		produitDto1.setGenre("femme");
		produitDto1.setIdVendeur(vendeurBean.getId());
		produitDto1.setMarque("tommy");
		produitDto1.setNom("pantalon tommy 31");
		produitDto1.setPrix("82");
		produitDto1.setPromotion("20");
		produitDto1.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages1 = new ArrayList<>();
		adressesWebImages1.add("adressetest");

		produitDto1.setImages(adressesWebImages1);

		Map<String, Boolean> mapTaillesDispos1 = new HashMap<>();
		mapTaillesDispos1.put("44", true);

		produitDto1.setTailles(mapTaillesDispos1);

		produitService.addProduit(produitDto1);

		ProduitDto produitDto2 = new ProduitDto();
		produitDto2.setCategorie("pantalon");
		produitDto2.setGenre("homme");
		produitDto2.setIdVendeur(vendeurBean.getId());
		produitDto2.setMarque("tommy");
		produitDto2.setNom("pantalon tommy 32");
		produitDto2.setPrix("83");
		produitDto2.setPromotion("30");
		produitDto2.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages2 = new ArrayList<>();
		adressesWebImages2.add("adressetest");

		produitDto2.setImages(adressesWebImages2);

		Map<String, Boolean> mapTaillesDispos2 = new HashMap<>();
		mapTaillesDispos2.put("44", false);
		mapTaillesDispos2.put("45", true);

		produitDto2.setTailles(mapTaillesDispos2);

		produitService.addProduit(produitDto2);
		
		CritereDto critereDto = new CritereDto();

		critereDto.setGenre("Homme");
		critereDto.setMarque("tommy");
		critereDto.setPage(0);
		critereDto.setSize(2);

		int page = critereDto.getPage();
		int size = critereDto.getSize();

		Pageable paging = PageRequest.of(page, size);

		List<ProduitDto> produits = produitService.getProduitsByCriteria(critereDto, paging);

		assertTrue(produits.size() == 1);
		assertEquals("pantalon tommy 32", produits.get(0).getNom());
		assertEquals("58,10", produits.get(0).getNewPrix());

	}

	@Test
	public void getCategories() throws ProduitException, NotFoundException, TechnicalException {

		VendeurBean vendeurBean = new VendeurBean();
		vendeurBean.setId(1);
		vendeurDao.save(vendeurBean);

		ProduitDto produitDto1 = new ProduitDto();
		produitDto1.setCategorie("jean");
		produitDto1.setGenre("homme");
		produitDto1.setIdVendeur(vendeurBean.getId());
		produitDto1.setMarque("tommy");
		produitDto1.setNom("pantalon tommy 31");
		produitDto1.setPrix("84");
		produitDto1.setPromotion("50");
		produitDto1.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages1 = new ArrayList<>();
		adressesWebImages1.add("adressetest");

		produitDto1.setImages(adressesWebImages1);

		Map<String, Boolean> mapTaillesDispos1 = new HashMap<>();
		mapTaillesDispos1.put("44", true);

		produitDto1.setTailles(mapTaillesDispos1);

		produitService.addProduit(produitDto1);

		ProduitDto produitDto2 = new ProduitDto();
		produitDto2.setCategorie("pulls/sweats");
		produitDto2.setGenre("homme");
		produitDto2.setIdVendeur(vendeurBean.getId());
		produitDto2.setMarque("nike");
		produitDto2.setNom("pullover femme nike");
		produitDto2.setPrix("120.5");
		produitDto2.setPromotion("PROMO 60");
		produitDto2.setAdresseWeb("adresseWebTest");

		List<String> adressesWebImages2 = new ArrayList<>();
		adressesWebImages2.add("adressetest");

		produitDto2.setImages(adressesWebImages2);

		Map<String, Boolean> mapTaillesDispos2 = new HashMap<>();
		mapTaillesDispos2.put("XS", false);
		mapTaillesDispos2.put("S", true);

		produitDto2.setTailles(mapTaillesDispos2);

		produitService.addProduit(produitDto2);

		ProduitBean produitBean2 = produitDao.findByNom(produitDto2.getNom());

		assertEquals("Pulls/Sweats", produitBean2.getCategorie().getCategorie());
		assertEquals(60, produitBean2.getPromotion());
		assertEquals(120.50, produitBean2.getPrix());

		List<String> categories = categorieService.getCategories();

		assertTrue(categories.size() == 2);

	}
*/
}
