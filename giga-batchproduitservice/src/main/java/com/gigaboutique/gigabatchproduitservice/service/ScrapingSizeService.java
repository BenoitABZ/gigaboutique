package com.gigaboutique.gigabatchproduitservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;
import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;

@Service
public class ScrapingSizeService {

	@Autowired
	ProductConfiguration productConfiguration;

	@Autowired
	ScrapingGenericService genericService;

	@Autowired
	ScrapingXmlParserService xmlParserService;

	public ProduitDto setSize(ProduitDto produitDto, int number, String url) throws BatchProduitException {

		Map<String, Boolean> mapTailles = new HashMap<>();

		String section = productConfiguration.getProduitTailleSection()[number];
		String tailleProduitDisponible = productConfiguration.getProduitTailleDisponible()[number];
		String tailleProduitIndisponible = productConfiguration.getProduitTailleIndisponible()[number];

		Map<String, List<String>> mapSection = xmlParserService.parseXml(section);

		if (section.contains("section")) {

			Elements elements = null;

			elements = genericService.getElements(url, mapSection);

			for (Element element : elements) {

				Map<String, List<String>> mapTailleProduitDisponible = xmlParserService
						.parseXml(tailleProduitDisponible);

				String result = null;
				try {
					result = genericService.getElementString(element, url, mapTailleProduitDisponible);

				} catch (NullPointerException e) {

				}

				if (result != null) {

					mapTailles.put(result, true);

				}
			}

			for (Element element : elements) {

				Map<String, List<String>> mapTailleProduitIndisponible = xmlParserService
						.parseXml(tailleProduitIndisponible);

				String result = null;
				try {
					result = genericService.getElementString(element, url, mapTailleProduitIndisponible);
				} catch (NullPointerException e) {

				}

				if (result != null) {

					mapTailles.put(result, false);

				}
			}

			produitDto.setTailles(mapTailles);
		}
		return produitDto;
	}

}
