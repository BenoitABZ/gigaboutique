package com.gigaboutique.gigabatchproduitservice.service;

import java.util.ArrayList;
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
public class ScrapingImageService {
	
	@Autowired
	ProductConfiguration productConfiguration;

	@Autowired
	ScrapingGenericService genericService;

	@Autowired
	ScrapingXmlParserService xmlParserService;

	public ProduitDto setImages(ProduitDto produitDto, int number, String url) throws BatchProduitException {
		
		List<String> images = new ArrayList<>();
		
		String section = productConfiguration.getImageSection()[number];
		Map<String, List<String>> mapSection = xmlParserService.parseXml(section);
		
		String imageLocation = productConfiguration.getProduitImage()[number];
		Map<String, List<String>> mapImages = xmlParserService.parseXml(imageLocation);
		
		if (section.contains("section")) {

			Elements elements = scrapSection(mapSection, url);
			scrapElements(mapImages, images, elements, url);
			
			produitDto.setImages(images);

		}
		
		return produitDto;
	
	}
	
	private List<String> scrapElements(Map<String, List<String>> map, List<String> images, Elements elements, String url) throws BatchProduitException {

		for (Element element : elements) {

			String result = null;
			try {
				result = genericService.getElementString(element, url, map);
			} catch (NullPointerException e) {

			}


			if (result != null) {

				images.add(result);		
		}
		}
		
		return images;
	}

	
	private Elements scrapSection(Map<String, List<String>> map, String url) throws BatchProduitException {

		Elements elements = genericService.getElements(url, map);

		return elements;

	}

}
