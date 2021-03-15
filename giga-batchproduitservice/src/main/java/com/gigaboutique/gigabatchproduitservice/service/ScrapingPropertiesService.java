package com.gigaboutique.gigabatchproduitservice.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;
import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;

@Service
public class ScrapingPropertiesService {

	@Autowired
	ProductConfiguration productConfiguration;

	@Autowired
	ScrapingGenericService genericService;

	@Autowired
	ScrapingXmlParserService xmlParserService;

	public ProduitDto setCategorie(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String categorieLocation = productConfiguration.getProduitCategorie()[number];
		Map<String, List<String>> mapCatagorie = xmlParserService.parseXml(categorieLocation);

		Element element = null;

		String result = null;
		try {
			result = genericService.getElementString(element, url, mapCatagorie, document);
		} catch (NullPointerException e) {

		}

		if (result != null) {

			produitDto.setCategorie(result);
		}

		return produitDto;
	}

	public ProduitDto setMarque(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String marqueLocation = productConfiguration.getProduitMarque()[number];

		if (marqueLocation.contains("string")) {

			Map<String, List<String>> mapMarque = xmlParserService.parseXml(marqueLocation);

			Element element = null;

			String result = null;
			try {
				result = genericService.getElementString(element, url, mapMarque, document);
			} catch (NullPointerException e) {

			}

			if (result != null) {

				produitDto.setMarque(result);
			}
		} else {

			produitDto.setMarque(marqueLocation);
		}

		return produitDto;
	}

	public ProduitDto setNom(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String nomLocation = productConfiguration.getProduitNom()[number];
		Map<String, List<String>> mapNom = xmlParserService.parseXml(nomLocation);

		Element element = null;

		String result = null;
		try {
			result = genericService.getElementString(element, url, mapNom, document);
		} catch (NullPointerException e) {

		}

		if (result != null) {

			produitDto.setNom(result);
		}

		return produitDto;
	}

	public ProduitDto setGenre(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String genreLocation = productConfiguration.getProduitGenre()[number];
		Map<String, List<String>> mapGenre = xmlParserService.parseXml(genreLocation);

		Element element = null;

		String result = null;
		try {
			result = genericService.getElementString(element, url, mapGenre, document);
		} catch (NullPointerException e) {

		}
			
		if (result != null) {

			produitDto.setGenre(result);
		}

		return produitDto;
	}

	public ProduitDto setOldPrice(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String priceOldLocation = productConfiguration.getProduitOldPrix()[number];
		Map<String, List<String>> mapOld = xmlParserService.parseXml(priceOldLocation);

		Element element = null;

		String result = null;
		try {
			result = genericService.getElementString(element, url, mapOld, document);
		} catch (NullPointerException e) {

		}

		if (result != null) {

			produitDto.setPrix(extractDoubleString(result));
		}

		return produitDto;
	}

	public ProduitDto setNewPrice(ProduitDto produitDto, int number, String url, Document document) throws BatchProduitException {

		String priceNewLocation = productConfiguration.getProduitNewPrix()[number];
		Map<String, List<String>> mapNew = xmlParserService.parseXml(priceNewLocation);

		Element element = null;

		String result = null;
		try {
			result = genericService.getElementString(element, url, mapNew, document);
		} catch (NullPointerException e) {

		}

		if (result != null) {

			produitDto.setNewPrix(extractDoubleString(result));
		}

		return produitDto;
	}

	public ProduitDto setPromotion(ProduitDto produitDto, int number, String url) throws BatchProduitException {

		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);

		String prixOldString = produitDto.getPrix().replace(",", ".");

		double prixOldDouble = Double.parseDouble(prixOldString);

		df.format(prixOldDouble);

		String prixNewString = produitDto.getNewPrix().replace(",", ".");

		double prixNewDouble = Double.parseDouble(prixNewString);

		df.format(prixNewDouble);

		int promotion = (int) (((prixOldDouble - prixNewDouble) / prixOldDouble) * 100);

		produitDto.setPromotion(String.valueOf(promotion));

		return produitDto;
	}
	
	private String extractDoubleString(String doubleString) {
		
		String pos[] = doubleString.split(" ");
		
		return pos[0].replace("€", ".");
	}
	
}
