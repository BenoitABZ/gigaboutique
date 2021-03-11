package com.gigaboutique.gigabatchproduitservice.service;

import org.springframework.stereotype.Service;

@Service
public class ScrapingGlobalService {
/*
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
*/
}
