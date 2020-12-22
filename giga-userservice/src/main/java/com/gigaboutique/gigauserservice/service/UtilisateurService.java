package com.gigaboutique.gigauserservice.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class UtilisateurService {
	
	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private Validator validator;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<UtilisateurDto> getUtilisateurs() {
		return ((List<UtilisateurBean>) utilisateurDao.findAll()).stream()
				.map(mapUtilisateurDtoService::convertToUtilisateurDto).collect(Collectors.toList());
	}

	public UtilisateurDto getUtilisateur(String mail) throws UtilisateurException {

		UtilisateurBean utilisateur = utilisateurDao.findByMail(mail);

		if (utilisateur == null) {

			throw new UtilisateurException("Adresse mail incorrect!");

		}

		UtilisateurDto utilisateurDto = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateur);

		return utilisateurDto;

	}

	public UtilisateurDto registerUtilisateur(UtilisateurBean utilisateur, RoleService rc) throws UtilisateurException {

		UtilisateurBean utilisateurVerify = utilisateurDao.findByMail(utilisateur.getMail());

		if (utilisateurVerify != null) {

			throw new UtilisateurException("Cette adresse mail existe déjà!");

		}

		Set<ConstraintViolation<UtilisateurBean>> vViolations = validator.validate(utilisateur);
		if (!vViolations.isEmpty()) {

			for (ConstraintViolation<UtilisateurBean> vViolation : vViolations) {

				throw new UtilisateurException(vViolation.getMessage());

			}
		}
		
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
		
		utilisateur = rc.setRoleUtilisateur(utilisateur);
		
		utilisateurDao.save(utilisateur);

		UtilisateurDto utilisateurDto = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateur);
		
		return utilisateurDto;

	}

}
