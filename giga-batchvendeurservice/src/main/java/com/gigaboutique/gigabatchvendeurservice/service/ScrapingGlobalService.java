package com.gigaboutique.gigabatchvendeurservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchvendeurservice.configuration.SellerConfiguration;
import com.gigaboutique.gigabatchvendeurservice.dto.VendeurDto;
import com.gigaboutique.gigabatchvendeurservice.exception.BatchVendeurException;
import com.gigaboutique.gigabatchvendeurservice.proxy.ProduitProxy;
import com.gigaboutique.gigabatchvendeurservice.proxy.VendeurProxy;

@Service
public class ScrapingGlobalService {

	@Autowired
	VendeurProxy vendeurProxy;

	@Autowired
	ProduitProxy produitProxy;

	@Autowired
	ScrapingUnitService scrapingUnitService;

	@Autowired
	SellerConfiguration sellerConfiguration;

	public List<VendeurDto> setVendeurs() throws BatchVendeurException {

		List<VendeurDto> vendeursDto = new ArrayList<>();

		VendeurDto vendeurDto = null;

		String pattern = sellerConfiguration.getPatternLinkToScrap();

		for (String seller : sellerConfiguration.getSellers()) {

			String url = pattern + seller;

			vendeurDto = scrapingUnitService.getVendeur(url);

			VendeurDto vendeurDtoWithId = vendeurProxy.addVendeur(vendeurDto);

			vendeursDto.add(vendeurDtoWithId);

			int id = vendeurDtoWithId.getId();

			produitProxy.addVendeur(id);

		}

		return vendeursDto;
	}

}
