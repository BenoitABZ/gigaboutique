package com.gigaboutique.gigauserservice.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class MapUtilisateurDtoService {

	public UtilisateurDto convertToUtilisateurDto(UtilisateurBean utilisateur) {

		UtilisateurDto utilisateurDto = new UtilisateurDto();

		utilisateurDto.setIdUtilisateur(utilisateur.getId());
		utilisateurDto.setNom(utilisateur.getNom());
		utilisateurDto.setPrenom(utilisateur.getPrenom());
		utilisateurDto.setMail(utilisateur.getMail());
		utilisateurDto.setRole(utilisateur.getRole().getRole());

		Set<Integer> produitsId = populateProduitPanierId(utilisateur);

		utilisateurDto.setProduits(produitsId);

		return utilisateurDto;

	}

	public UtilisateurBean convertToUtilisateurBean(UtilisateurDto utilisateurDto, ModelMapper modelMapper) {

		UtilisateurBean utilisateurBean = modelMapper.map(utilisateurDto, UtilisateurBean.class);

		return utilisateurBean;

	}

	public UtilisateurBean convertToUtilisateurBeanForRegistration(RegisterDto registerDto, ModelMapper modelMapper) {

		UtilisateurBean utilisateurBean = modelMapper.map(registerDto, UtilisateurBean.class);

		return utilisateurBean;
	}

	private static Set<Integer> populateProduitPanierId(UtilisateurBean utilisateur) {

		Set<Integer> setProduitPanier;
		try {
			setProduitPanier = ((Set<ProduitPanierBean>) utilisateur
					.getProduitsPanier())
					.stream()
					.map(ProduitPanierBean::getId)
					.collect(Collectors.toSet());
		} catch (NullPointerException npe) {

			setProduitPanier = new HashSet<>();

			return setProduitPanier;
		}

		return setProduitPanier;

	}

}
