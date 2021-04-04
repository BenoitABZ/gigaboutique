package com.gigaboutique.gigauserservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
@Transactional
public class UtilisateurService {

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<UtilisateurDto> getUtilisateurs() {

		List<UtilisateurDto> utilisateursDto = new ArrayList<>();

		List<UtilisateurBean> utilisateursBean = utilisateurDao.findAll();

		for (UtilisateurBean utilisateurBean : utilisateursBean) {

			try {

				utilisateursDto.add(mapUtilisateurDtoService.convertToUtilisateurDto(utilisateurBean));

			} catch (UtilisateurException e) {

				return null;
			}

		}

		return utilisateursDto;

	}

	public UtilisateurDto getUtilisateur(String mail) throws UtilisateurException {

		UtilisateurBean utilisateur = utilisateurDao.findByMail(mail);

		if (utilisateur == null) {

			throw new UtilisateurException("adresse mail incorrect");

		}

		UtilisateurDto utilisateurDto = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateur);

		return utilisateurDto;

	}

	public UtilisateurDto registerUtilisateur(RegisterDto registerDto, RoleService rc) throws UtilisateurException {

		UtilisateurBean utilisateurVerify = null;

		UtilisateurBean utilisateur = null;

		UtilisateurDto utilisateurDto = null;

		try {

			utilisateur = mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto);

			utilisateurVerify = utilisateurDao.findByMail(utilisateur.getMail());

		} catch (NullPointerException npe) {

		}

		if (utilisateurVerify != null) {

			throw new UtilisateurException("cette adresse mail existe déjà");

		}

		if (!utilisateur.getMotDePasse().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,32}")) {

			throw new UtilisateurException("votre mot de passe doit comporter au moins 8 caractères dont 1 majuscule et 1 chiffre");
		}

		Set<ConstraintViolation<UtilisateurBean>> vViolations = getConstraintValidator().validate(utilisateur);
		if (!vViolations.isEmpty()) {

			for (ConstraintViolation<UtilisateurBean> vViolation : vViolations) {

				throw new UtilisateurException(vViolation.getMessage());

			}
		}

		utilisateur = rc.setRoleUtilisateur(utilisateur);

		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

		utilisateurDao.save(utilisateur);

		utilisateurDto = mapUtilisateurDtoService.convertToUtilisateurDto(utilisateur);

		return utilisateurDto;

	}

	private Validator getConstraintValidator() {
		Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
		ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
		Validator vValidator = vFactory.getValidator();
		return vValidator;
	}

}
