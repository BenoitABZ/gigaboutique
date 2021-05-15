package com.gigaboutique.gigaclientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigaclientservice.dto.ProduitDto;
import com.gigaboutique.gigaclientservice.dto.VendeurDto;
import com.gigaboutique.gigaclientservice.proxy.GatewayProxy;

@Service
public class VendeurService {
	
	@Autowired
	GatewayProxy gatewayProxy;
	
	public VendeurDto getVendeur(String token, int idProduit) {
		
		ProduitDto produitDto = gatewayProxy.getProduitById(token, idProduit);
		
		int idVendeur = produitDto.getIdVendeur();
		
		VendeurDto vendeurDto = gatewayProxy.getVendeur(token, idVendeur);
		
		return vendeurDto;
	}

}
