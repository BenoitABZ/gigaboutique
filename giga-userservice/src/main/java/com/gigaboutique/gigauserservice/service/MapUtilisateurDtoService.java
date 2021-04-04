
package com.gigaboutique.gigauserservice.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.model.UtilisateurProduitPanierBean;

@Service
@Transactional
public class MapUtilisateurDtoService {

	public UtilisateurDto convertToUtilisateurDto(UtilisateurBean utilisateur) throws UtilisateurException {

		UtilisateurDto utilisateurDto = null;

		try {

			utilisateurDto = new UtilisateurDto();
			utilisateurDto.setIdUtilisateur(utilisateur.getId());
			utilisateurDto.setNom(utilisateur.getNom());
			utilisateurDto.setPrenom(utilisateur.getPrenom());
			utilisateurDto.setMail(utilisateur.getMail());
			utilisateurDto.setRole(utilisateur.getRole().getRole());

			Set<Integer> produitsId = populateProduitPanierId(utilisateur);

			utilisateurDto.setProduits(produitsId);

		} catch (Exception e) {

			throw new UtilisateurException("problème convertToUtilisateurDto");

		}

		return utilisateurDto;

	}

	public UtilisateurBean convertToUtilisateurBean(UtilisateurDto utilisateurDto, ModelMapper modelMapper) {

		UtilisateurBean utilisateurBean = modelMapper.map(utilisateurDto, UtilisateurBean.class);

		return utilisateurBean;

	}

	public UtilisateurBean convertToUtilisateurBeanForRegistration(RegisterDto registerDto)
			throws UtilisateurException {

		UtilisateurBean utilisateurBean = null;

		try {

			utilisateurBean = new UtilisateurBean();
			utilisateurBean.setNom(registerDto.getNom());
			utilisateurBean.setPrenom(registerDto.getPrenom());
			utilisateurBean.setMail(registerDto.getMail());
			utilisateurBean.setMotDePasse(registerDto.getPassword());

		} catch (Exception e) {

			throw new UtilisateurException("problème convertToUtilisateurBeanForRegistration");

		}

		return utilisateurBean;
	}

	private static Set<Integer> populateProduitPanierId(UtilisateurBean utilisateur) {

		Set<Integer> setProduitPanier = new HashSet<>();

		try {
			setProduitPanier = ((Set<UtilisateurProduitPanierBean>) utilisateur
					.getUtilisateurProduits()).stream()
					.map(UtilisateurProduitPanierBean::getProduitPanier)
					.map(ProduitPanierBean::getId)
					.collect(Collectors.toSet());
		} catch (NullPointerException npe) {

			setProduitPanier = new HashSet<>();

			return setProduitPanier;
		}

		return setProduitPanier;

	}

}
