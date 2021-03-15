package com.gigaboutique.gigabatchproduitservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
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

	public ProduitDto setSizes(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		Map<String, Boolean> mapTailles = new HashMap<>();

		String section = productConfiguration.getProduitTailleSection()[number];
		Map<String, List<String>> mapSection = xmlParserService.parseXml(section);

		String tailleProduitDisponible = productConfiguration.getProduitTailleDisponible()[number];
		Map<String, List<String>> mapTailleProduitDisponible = xmlParserService.parseXml(tailleProduitDisponible);

		String tailleProduitIndisponible = productConfiguration.getProduitTailleIndisponible()[number];
		Map<String, List<String>> mapTailleProduitIndisponible = xmlParserService.parseXml(tailleProduitIndisponible);

		if (section.contains("section")) {

			Elements elements = scrapSection(mapSection, url, document);
			scrapElements(mapTailleProduitDisponible, mapTailles, elements, url, true, document);
			scrapElements(mapTailleProduitIndisponible, mapTailles, elements, url, false, document);

		}

		if (section.contains("null")) {

			Elements elementsProduitsDisponibles = scrapSection(mapTailleProduitDisponible, url, document);
			scrapElements(mapTailleProduitDisponible, mapTailles, elementsProduitsDisponibles, url, true, document);

			Elements elementsProduitsIndisponibles = scrapSection(mapTailleProduitIndisponible, url, document);
			scrapElements(mapTailleProduitIndisponible, mapTailles, elementsProduitsIndisponibles, url, false, document);
		}

		produitDto.setTailles(mapTailles);

		return produitDto;
	}

	private Map<String, Boolean> scrapElements(Map<String, List<String>> map, Map<String, Boolean> mapTailles, Elements elements, String url, Boolean bool, Document document) throws BatchProduitException {

		for (Element element : elements) {

			String result = null;
			try {
				result = genericService.getElementString(element, url, map, document);
			} catch (NullPointerException e) {

			}

			if (result != null && bool == false) {

				mapTailles.put(result, false);

			}

			if (result != null && bool == true) {

				mapTailles.put(result, true);

			}
			
		}

		return mapTailles;
	}

	private Elements scrapSection(Map<String, List<String>> map, String url, Document document) throws BatchProduitException {

		Elements elements = genericService.getElements(url, map, document);

		return elements;

	}
	
}
