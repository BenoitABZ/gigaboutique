package com.gigaboutique.gigabatchproduitservice;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;
import com.gigaboutique.gigabatchproduitservice.service.ScrapingSizeService;

@SpringBootTest
class GigaBatchproduitserviceApplicationTests {

	@Autowired
	ScrapingSizeService scrapingSizeService;

	@Test
	void contextLoads() {
	}

	@Test
	public void scrapingSizeServiceTest() throws BatchProduitException {

		ProduitDto produitDto = new ProduitDto();

		String url = "https://www.laboutiqueofficielle.com/achat-polos-manches-courtes/tommy-hilfiger-polo-manches-courtes-core-tommy-4975-noir-242834.html";

		int number = 0;

		ProduitDto produitDtoTest = scrapingSizeService.setSize(produitDto, number, url);

		Map<String, Boolean> map = produitDtoTest.getTailles();

		for (Map.Entry<String, Boolean> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}

	}

}
