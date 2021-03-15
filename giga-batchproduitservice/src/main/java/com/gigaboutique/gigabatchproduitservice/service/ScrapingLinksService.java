package com.gigaboutique.gigabatchproduitservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;
import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;

@Service
public class ScrapingLinksService {

	@Autowired
	ProductConfiguration productConfiguration;

	@Autowired
	ScrapingGenericService genericService;

	@Autowired
	ScrapingXmlParserService xmlParserService;
	
	//private Document document;

	public ProduitDto setImages(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		List<String> images = new ArrayList<>();

		String section = productConfiguration.getImageSection()[number];
		Map<String, List<String>> mapSection = xmlParserService.parseXml(section);

		String imageLocation = productConfiguration.getProduitImage()[number];
		Map<String, List<String>> mapImages = xmlParserService.parseXml(imageLocation);

		if (section.contains("section")) {

			Elements elements = scrapSection(mapSection, url, document);
			scrapElements(mapImages, images, elements, url, document);

			produitDto.setImages(images);

		}

		return produitDto;

	}

	public List<String> getProductLinks(int number, String url, Document document) throws BatchProduitException {
/*	
		url = decodeUrl(url);

		document = getDocument(url);
*/
		List<String> productLinks = new ArrayList<>();

		String section = productConfiguration.getProduitSection()[number];
		Map<String, List<String>> mapSection = xmlParserService.parseXml(section);

		String productLocation = productConfiguration.getProduitLinks()[number];
		Map<String, List<String>> mapProducts = xmlParserService.parseXml(productLocation);

		if (section.contains("section")) {

			Elements elements = scrapSection(mapSection, url, document);
			scrapElements(mapProducts, productLinks, elements, url, document);

		}

		return productLinks;

	}

	private List<String> scrapElements(Map<String, List<String>> map, List<String> images, Elements elements, String url, Document document) throws BatchProduitException {

		for (Element element : elements) {

			String result = null;
			try {
				result = genericService.getElementString(element, url, map, document);
			} catch (NullPointerException e) {

			}

			if (result != null) {

				images.add(result);
			}
		}

		return images;
	}

	private Elements scrapSection(Map<String, List<String>> map, String url, Document document) throws BatchProduitException {

		Elements elements = genericService.getElements(url, map, document);

		return elements;

	}
/*	
	private Document getDocument(String url) throws BatchProduitException {
		try {

			document = Jsoup.connect(url).get();

		} catch (IOException e) {

			throw new BatchProduitException("problème lors de l'extraction de la page html getDocument");
		}
		return document;
	}

	private static String decodeUrl(String url) throws BatchProduitException {
		try {

			url = URLDecoder.decode(url, "UTF-8");

		} catch (UnsupportedEncodingException e) {

			throw new BatchProduitException("problème encoding scheme decodeUrl");
		}
		return url;
	}
*/
}
