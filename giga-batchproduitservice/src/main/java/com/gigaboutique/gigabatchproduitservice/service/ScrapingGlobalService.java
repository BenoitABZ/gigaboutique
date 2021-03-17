package com.gigaboutique.gigabatchproduitservice.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchproduitservice.configuration.ProductConfiguration;
import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;
import com.gigaboutique.gigabatchproduitservice.dto.VendeurDto;
import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;
import com.gigaboutique.gigabatchproduitservice.proxy.ProduitProxy;
import com.gigaboutique.gigabatchproduitservice.proxy.VendeurProxy;


@Service
public class ScrapingGlobalService {
	
    @Autowired
    VendeurProxy vendeurProxy;
	
	@Autowired
	ProduitProxy produitProxy;

	@Autowired
	ScrapingUnitService scrapingUnitService;

	@Autowired
	ScrapingLinksService scrapinLinksService;

	@Autowired
	ProductConfiguration productConfiguration;
	private Document document;

	public List<ProduitDto> setProduits() throws BatchProduitException, InterruptedException {

		List<ProduitDto> produitsDto = new ArrayList<>();

		ProduitDto produitDto = null;

		int count = 0;
		
		for (String link : productConfiguration.getProduitsLink()) {
			
			int numberOfProduct = 0;
			
			link = decodeUrl(link);

			try {

				document = getDocument(link);
				
				}catch(BatchProduitException bpe) {
					
					Thread.sleep(5000);
					
					document = getDocument(link);
				}

			List<String> urls = scrapinLinksService.getProductLinks(count, link, document);

			for (String url : urls) {
				
				if(numberOfProduct == productConfiguration.getNombreProduits()) {
					
					break;
				}
				
				url = decodeUrl(url);
				
				try {

				document = getDocument(url);
				
				}catch(BatchProduitException bpe) {
					
					Thread.sleep(5000);
					
					document = getDocument(url);
				}
				
				produitDto = scrapingUnitService.getProduit(url, count, document);

				if (produitDto != null) {

					List<VendeurDto> vendeurs = vendeurProxy.getVendeurs();

					for (VendeurDto vendeurDto : vendeurs) {

						if (normalizeString(vendeurDto.getNom()).contains(normalizeString(productConfiguration.getVendeurNom()[count])) 
						 || normalizeString(productConfiguration.getVendeurNom()[count]).contains(normalizeString(vendeurDto.getNom())) 
						 || normalizeString(productConfiguration.getVendeurNom()[count]).equals(normalizeString(vendeurDto.getNom()))) {

							produitDto.setIdVendeur(vendeurDto.getId());
							
							boolean add = true;
							
							for(ProduitDto produitDtoToCompare : produitsDto) {
								
								if(produitDtoToCompare.getNom().equals(produitDto.getNom())){
									
									add = false;
								}
							}
							
							if(add = true) {

							produitsDto.add(produitDto);
							
							numberOfProduct ++;
							
							}
						}
					}
				}
			}

			count++;

		}
		return produitsDto;
	}

	public void addProduits() throws BatchProduitException, InterruptedException {

		List<ProduitDto> produitsDto = setProduits();

		produitProxy.setFalse();
		
		for (ProduitDto produitDto : produitsDto) {
			
			produitProxy.addProduit(produitDto);			
		}
		
		produitProxy.removeFalse();
	}
	
	private String normalizeString (String toFormat) {
		
		return Normalizer.normalize(toFormat, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").toLowerCase().trim();
				
	}
	
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
}
