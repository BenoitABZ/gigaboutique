package com.gigaboutique.gigauserservice.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.RegisterDto;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.UtilisateurException;
import com.gigaboutique.gigauserservice.model.RoleBean;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class UtilisateurService {

	@Autowired
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private ModelMapper modelMapper;

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

	public UtilisateurDto registerUtilisateur(RegisterDto registerDto, RoleService rc) throws UtilisateurException {

		UtilisateurBean utilisateurVerify = null;

		UtilisateurBean utilisateur = null;

		UtilisateurDto utilisateurDto = null;

		try {

			utilisateur = mapUtilisateurDtoService.convertToUtilisateurBeanForRegistration(registerDto, modelMapper);

			utilisateurVerify = utilisateurDao.findByMail(utilisateur.getMail());

		} catch (NullPointerException npe) {

			System.out.println("ici");
		}

		if (utilisateurVerify != null) {

			throw new UtilisateurException("Cette adresse mail existe déjà!");

		}

		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

		Set<ConstraintViolation<UtilisateurBean>> vViolations = getConstraintValidator().validate(utilisateur);
		if (!vViolations.isEmpty()) {

			for (ConstraintViolation<UtilisateurBean> vViolation : vViolations) {

				throw new UtilisateurException(vViolation.getMessage());

			}
		}

		utilisateur = rc.setRoleUtilisateur(utilisateur);

		RoleBean role = utilisateur.getRole();

		System.out.println(role.getId());

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
