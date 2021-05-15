package com.gigaboutique.gigaclientservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gigaboutique.gigaclientservice.dto.CritereDto;
import com.gigaboutique.gigaclientservice.dto.ProduitDto;
import com.gigaboutique.gigaclientservice.dto.UtilisateurDto;
import com.gigaboutique.gigaclientservice.proxy.GatewayProxy;

@Service
public class ProduitService {

	@Autowired
	GatewayProxy gatewayProxy;

	public String addProduitPanier(String token, int idProduitPanier, int idUtilisateur) {

		String message = null;

		try {

			gatewayProxy.addProduit(token, idProduitPanier, idUtilisateur);

			message = "article ajouté avec succés à votre panier";

		} catch (ResponseStatusException rse) {

			message = rse.getMessage();
		}

		return message;
	}

	public String removeProduitPanier(String token, int idProduitPanier, int idUtilisateur) {

		String message = null;

		try {

			gatewayProxy.removeProduit(token, idProduitPanier, idUtilisateur);

			message = "article supprimé avec succés de votre panier";

		} catch (ResponseStatusException rse) {

			message = rse.getMessage();
		}

		return message;
	}

	public List<ProduitDto> getProduitsPanier(String token, int idUtilisateur) {

		List<ProduitDto> produitsDto = new ArrayList<>();

		try {

			UtilisateurDto utilisateurDto = gatewayProxy.getProduitsPanier(token, idUtilisateur);

			Set<Integer> produitsId = utilisateurDto.getProduits();

			for (Integer produitId : produitsId) {

				ProduitDto produitDto = gatewayProxy.getProduitById(token, produitId);

				produitsDto.add(produitDto);
			}

		} catch (ResponseStatusException rse) {

			return null;
		}

		return produitsDto;
	}

	public List<ProduitDto> getProduits(String token, String page, String size) {

		List<ProduitDto> produitsDto = new ArrayList<>();

		try {

			produitsDto = gatewayProxy.getProduits(token, page, size);

		} catch (ResponseStatusException rse) {

			return null;
		}

		return produitsDto;
	}

	public List<ProduitDto> getProduitsByCriteria(String token, CritereDto critereDto) {

		List<ProduitDto> produitsDto = new ArrayList<>();

		try {

			produitsDto = gatewayProxy.getProduitsCriteria(token, critereDto);

		} catch (ResponseStatusException rse) {

			return null;
		}

		return produitsDto;
	}
	
	public ProduitDto getProduit(String token, int idProduit) {

		ProduitDto produitDto = null;

		try {

			produitDto = gatewayProxy.getProduitById(token, idProduit);

		} catch (ResponseStatusException rse) {

			return null;
		}

		return produitDto;
	}

	public List<String> getMarques(String token) {

		List<String> marques = new ArrayList<>();

		try {

			marques = gatewayProxy.getMarques(token);

		} catch (ResponseStatusException rse) {

			return null;
		}

		return marques;
	}

	public List<String> getCategories(String token) {

		List<String> categories = new ArrayList<>();

		try {

			categories = gatewayProxy.getCategories(token);

		} catch (ResponseStatusException rse) {

			return null;
		}

		return categories;
	}

}
