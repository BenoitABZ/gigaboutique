package com.gigaboutique.gigabatchproduitservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;
import com.gigaboutique.gigabatchproduitservice.proxy.ProduitProxy;
import com.gigaboutique.gigabatchproduitservice.service.ScrapingGlobalService;

@SpringBootTest
class GigaBatchproduitserviceApplicationTests {
	
	@Autowired
	ScrapingGlobalService scrapingGlobalService;
	
	@Autowired
    ProduitProxy produitProxy;

	@Test
	void contextLoads() {
	}

	@Test
	public void scrapingGlobalServiceAddProduitsTest() throws BatchProduitException, InterruptedException {

		scrapingGlobalService.addProduits();
		
		List<ProduitDto> produits = produitProxy.getProduits("0", "10");
		
		assertEquals(10, produits.size());
         
	}
/*
	@Test
	public void scrapingSizeServiceTest1() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.laboutiqueofficielle.com/achat-casquettes-de-baseball/new-era-casquette-9forty-neon-12109559-new-york-yankees-jaune-fluo-240910.html";

		int number = 0;

		ProduitDto produitDtoTest = scrapingSizeService.setSizes(produitDto, number, url);

		Map<String, Boolean> map = produitDtoTest.getTailles();

		for (Map.Entry<String, Boolean> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

	}

	@Test
	public void scrapingSizeServiceTest2() throws BatchProduitException {

		ProduitDto produitDto2 = new ProduitDto();

		String url2 = "https://www.gemo.fr/produit/baskets-homme-a-lacets-details-colores-et-semelle-contrastante/GEMO_696320#ref=30270770281&couleur=noir";
		int number2 = 1;

		ProduitDto produitDtoTest2 = scrapingSizeService.setSizes(produitDto2, number2, url2);

		Map<String, Boolean> map2 = produitDtoTest2.getTailles();

		for (Map.Entry<String, Boolean> entry : map2.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

	}

	@Test
	public void scrapingImageServiceTest1() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.laboutiqueofficielle.com/achat-polos-manches-courtes/tommy-hilfiger-polo-manches-courtes-core-tommy-4975-noir-242834.html";

		int number = 0;

		ProduitDto produitDtoTest = scrapingImageService.setImages(produitDto, number, url);

		List<String> images = produitDtoTest.getImages();

		for (String image : images) {
			System.out.println(image);
		}

	}

	@Test
	public void scrapingImageServiceTest2() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.gemo.fr/produit/sweat-homme-molletonne-imprime-retour-vers-le-futur/GEMO_707356#ref=40171500273&couleur=gris&k=gc1615489636888";

		int number = 1;

		ProduitDto produitDtoTest = scrapingImageService.setImages(produitDto, number, url);

		List<String> images = produitDtoTest.getImages();

		for (String image : images) {
			System.out.println(image);
		}

	}

	@Test
	public void scrapingCategorieServiceTest1() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.laboutiqueofficielle.com/achat-polos-manches-courtes/tommy-hilfiger-polo-manches-courtes-core-tommy-4975-noir-242834.html";

		int number = 0;

		ProduitDto produitDtoTest = scrapingCategorieService.setCategorie(produitDto, number, url);

		String categorie = produitDtoTest.getCategorie();

		System.out.println(categorie);

	}

	@Test
	public void scrapingCategorieServiceTest2() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.gemo.fr/produit/sweat-homme-molletonne-imprime-retour-vers-le-futur/GEMO_707356#ref=40171500273&couleur=gris&k=gc1615489636888";

		int number = 1;

		ProduitDto produitDtoTest = scrapingCategorieService.setCategorie(produitDto, number, url);

		String categorie = produitDtoTest.getCategorie();

		System.out.println(categorie);

	}
*/
}
