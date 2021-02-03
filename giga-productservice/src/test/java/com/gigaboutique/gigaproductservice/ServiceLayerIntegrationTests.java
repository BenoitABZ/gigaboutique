package com.gigaboutique.gigaproductservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.exception.ProduitException;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.ProduitBean;
import com.gigaboutique.gigaproductservice.model.VendeurBean;
import com.gigaboutique.gigaproductservice.service.ProduitService;

@SpringBootTest
@Transactional
@Rollback(true)
public class ServiceLayerIntegrationTests {
	
	@Autowired
	ProduitService produitService;
	
	@Autowired
	VendeurDao vendeurDao;
	
	@Autowired
	ProduitDao produitDao;
	
	@Test
	public void addProduitTest() throws ProduitException, TechnicalException {
		
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
		
		List<String> adressesWebImages = new ArrayList<>();
		adressesWebImages.add("adressetest");
		
		produitDto.setImages(adressesWebImages);
		
		Map<String, Boolean> mapTaillesDispos = new HashMap<>();
		mapTaillesDispos.put("44", true);
		
		produitDto.setTailles(mapTaillesDispos);
		
		produitService.addProduit(produitDto);
		
		ProduitBean produitBean = produitDao.findByNom(produitDto.getNom());
	
		assertTrue(produitBean.getPrix() == 85.50);
		
	}

}
