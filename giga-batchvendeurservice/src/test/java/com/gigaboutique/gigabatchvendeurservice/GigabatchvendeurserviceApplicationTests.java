package com.gigaboutique.gigabatchvendeurservice;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gigaboutique.gigabatchvendeurservice.configuration.SellerConfiguration;
import com.gigaboutique.gigabatchvendeurservice.dto.VendeurDto;
import com.gigaboutique.gigabatchvendeurservice.service.ScrapingService;

@SpringBootTest
class GigabatchvendeurserviceApplicationTests {
	
	@Autowired
	ScrapingService scrapingService;
	
	@Autowired
	SellerConfiguration sellerConfiguration;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void getVendeur() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		VendeurDto vendeurDto = scrapingService.getVendeur(sellerConfiguration.getPatternLinkToScrap() + sellerConfiguration.getSellers().get(0));
		
		System.out.println(vendeurDto.getNom());
		
	
		
		System.out.println(vendeurDto.getLogo());
		System.out.println(vendeurDto.getNote());
		System.out.println(vendeurDto.getNombreDeCommentaires());
		System.out.println(vendeurDto.getCommentaires().size());
		System.out.println(vendeurDto.getCommentaires().get(0).getAuteur());
		System.out.println(vendeurDto.getCommentaires().get(0).getDateCommentaire());
		System.out.println(vendeurDto.getCommentaires().get(0).getDescription());
		System.out.println(vendeurDto.getCommentaires().get(0).getNote());
		System.out.println(vendeurDto.getCommentaires().get(0).getAuteur());
		
	
	}

}
