package com.gigaboutique.gigauserservice.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class MapUtilisateurDtoService {

	@Autowired
	private ModelMapper modelMapper;

	public UtilisateurDto convertToUtilisateurDto(UtilisateurBean utilisateur) {

		UtilisateurDto utilisateurDto = new UtilisateurDto();

		utilisateurDto.setIdUtilisateur(utilisateur.getId());
		utilisateurDto.setNom(utilisateur.getNom());
		utilisateurDto.setPrenom(utilisateur.getPrenom());
		utilisateurDto.setMail(utilisateur.getMail());
		utilisateurDto.setRole(utilisateur.getRole());

		Set<Integer> produitsId = populateProduitPanierId(utilisateur);

		utilisateurDto.setProduits(produitsId);

		return utilisateurDto;

	}

	public UtilisateurBean convertToUtilisateurBean(UtilisateurDto utilisateurDto) {

		UtilisateurBean utilisateurBean = modelMapper.map(utilisateurDto, UtilisateurBean.class);

		return utilisateurBean;

	}

	private static Set<Integer> populateProduitPanierId(UtilisateurBean utilisateur) {

		return ((Set<ProduitPanierBean>) utilisateur.getProduitsPanier()).stream().map(ProduitPanierBean::getId)
				.collect(Collectors.toSet());

	}

}
