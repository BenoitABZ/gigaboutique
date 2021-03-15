package com.gigaboutique.gigabatchproduitservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;
import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;

@Service
public class ScrapingUnitService {

	@Autowired
	ProductConfiguration productConfiguration;

	@Autowired
	ScrapingLinksService scrapingLinksService;

	@Autowired
	ScrapingSizeService scrapingSizeService;

	@Autowired
	ScrapingPropertiesService scrapingPropertiesService;
	
	//private Document document;

	public ProduitDto getProduit(String url, int number, Document document) throws BatchProduitException {
/*		
		url = decodeUrl(url);

		document = getDocument(url);
*/
		ProduitDto produitDto = new ProduitDto();

		try {

			produitDto.setAdresseWeb(url);
			scrapingPropertiesService.setCategorie(produitDto, number, url, document);
			scrapingPropertiesService.setGenre(produitDto, number, url, document);
			scrapingPropertiesService.setMarque(produitDto, number, url, document);
			scrapingPropertiesService.setNom(produitDto, number, url, document);
			scrapingPropertiesService.setOldPrice(produitDto, number, url, document);
			scrapingPropertiesService.setNewPrice(produitDto, number, url, document);
			scrapingPropertiesService.setPromotion(produitDto, number, url);

			scrapingLinksService.setImages(produitDto, number, url, document);

			scrapingSizeService.setSizes(produitDto, number, url, document);

			if (produitDto.getCategorie() == null 
					|| produitDto.getGenre() == null 
					|| produitDto.getMarque() == null
					|| produitDto.getNom() == null 
					|| produitDto.getNewPrix() == null 
					|| produitDto.getPrix() == null
					|| produitDto.getPromotion() == null 
					|| produitDto.getImages() == null
					|| produitDto.getImages().isEmpty() 
					|| produitDto.getTailles() == null
					|| produitDto.getTailles().isEmpty()) {

				return null;
			}

		} catch (Exception e) {

			return null;
		}

		return produitDto;

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
