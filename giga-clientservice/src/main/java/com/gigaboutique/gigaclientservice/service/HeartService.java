package com.gigaboutique.gigaclientservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigaclientservice.configuration.SecurityConstant;
import com.gigaboutique.gigaclientservice.dto.ProduitDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class HeartService {

	@Autowired
	ProduitService produitService;

	@Autowired
	SecurityConstant sc;

	public int setHeart(List<ProduitDto> produitsDto, String token) {

		int numberOfItems = 0;

		Claims claims = Jwts.parser().setSigningKey(sc.getSecret().getBytes()).parseClaimsJws(token.replace(sc.getTokenPrefix() + "", "")).getBody();

		int idUtilisateur = (int) claims.get("id");

		for (ProduitDto produitDto : produitsDto) {

			produitDto.setHeart(false);

			List<ProduitDto> produitsDtoFromUser = produitService.getProduitsPanier(token, idUtilisateur);

			if (produitsDtoFromUser != null) {

				numberOfItems = produitsDtoFromUser.size();

				for (ProduitDto produitDtoFromUser : produitsDtoFromUser) {

					if (produitDtoFromUser.getIdProduit() == produitDto.getIdProduit()) {

						produitDto.setHeart(true);
					}
				}
			}
		}

		return numberOfItems;
	}

}
