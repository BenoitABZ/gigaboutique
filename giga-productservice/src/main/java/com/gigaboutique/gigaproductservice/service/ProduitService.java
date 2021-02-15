package com.gigaboutique.gigaproductservice.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigaproductservice.configuration.ProductConfiguration;
import com.gigaboutique.gigaproductservice.dao.ImageProduitDao;
import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.TailleProduitDao;
import com.gigaboutique.gigaproductservice.dto.CritereDto;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.exception.ProduitException;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.ProduitBean;

import javassist.NotFoundException;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProduitService {

	@Autowired
	private MapProduitDtoService mapProduitDtoService;

	@Autowired
	private ProductConfiguration productConfiguration;

	@Autowired
	private ProduitDao produitDao;

	@Autowired
	private TailleProduitDao tailleProduitDao;
	
	@Autowired
	private ImageProduitDao imageProduitDao;

	public List<ProduitDto> getProduits(Pageable paging) throws ProduitException, NotFoundException {

		List<ProduitDto> produitsDto = new ArrayList<>();

		Page<ProduitBean> page = produitDao.findAll(paging);

		for (ProduitBean produitBean : page.getContent()) {

			ProduitDto produitDto = mapProduitDtoService.convertToProduitDto(produitBean);

			produitsDto.add(produitDto);
		}

		return produitsDto;
	}

	public List<ProduitDto> getProduitsByCriteria(CritereDto critere, Pageable paging)
			throws ProduitException, NotFoundException {

		List<String> marques = critere.getMarques();
		List<String> categories = critere.getCategories();
		String genre = critere.getGenre();

		List<ProduitDto> produitsDto = new ArrayList<>();

		Page<ProduitBean> page = produitDao.findByCriteria(genre, categories, marques, paging);

		for (ProduitBean produitBean : page.getContent()) {

			ProduitDto produitDto = mapProduitDtoService.convertToProduitDto(produitBean);

			produitsDto.add(produitDto);

		}

		return produitsDto;

	}

	public ProduitDto getProduitByName(String nom) throws ProduitException, NotFoundException {

		ProduitBean produitBean = produitDao.findByNom(nom);

		if (produitBean == null) {

			throw new NotFoundException("le produit n'existe pas en base de données");
		}

		ProduitDto produitDto = mapProduitDtoService.convertToProduitDto(produitBean);

		return produitDto;
	}

	public void setMajFalse() throws TechnicalException {

		try {
			for (ProduitBean produit : produitDao.findAll()) {

				produit.setMaj(false);

				produitDao.save(produit);

			}
		} catch (Exception e) {

			throw new TechnicalException("problème lors de la mise à jour du produit");
		}
	}

	public void addProduit(ProduitDto produitDto) throws ProduitException, TechnicalException {

		try {

			ProduitBean produitBean = produitDao.findByNom(produitDto.getNom());

			if (produitBean != null) {

				mapProduitDtoService.updateToProduitBean(produitDto, produitBean);

			} else {

				this.normalizeCategorieProduitDto(produitDto);

				this.normalizeGenreProduitDto(produitDto);

				produitBean = mapProduitDtoService.convertToProduitBean(produitDto);

				Set<ConstraintViolation<ProduitBean>> vViolations = getConstraintValidator().validate(produitBean);
				if (!vViolations.isEmpty()) {

					for (ConstraintViolation<ProduitBean> vViolation : vViolations) {

						throw new ProduitException(vViolation.getMessage());
					}
				}

			}

		} catch (Exception e) {

			throw new TechnicalException("problème lors de l'ajout du produit" + e.getMessage());
		}

	}

	public void removeProduitIfFalse() throws TechnicalException {

		try {

			List<ProduitBean> produitsMajFalse = produitDao.findByMaj(false);

			for (ProduitBean produit : produitsMajFalse) {
			
				tailleProduitDao.deleteByRelatedProduit(produit.getIdProduit());
				imageProduitDao.deleteByRelatedProduit(produit.getIdProduit());
					
			}
			
			produitDao.deleteInBatch(produitsMajFalse);

			//produitDao.deleteByMaj();

		} catch (Exception e) {

			throw new TechnicalException("problème lors de la suppression des produits n'ayant pas été mis à jour");
		}
	}

	public List<String> getMarques() {

		return produitDao.findAllMarques();
	}

	private ProduitDto normalizeCategorieProduitDto(ProduitDto produitDto) throws ProduitException {

		try {

			Map<String, List<String>> mapCategorie = productConfiguration.getCategories();

			for (Map.Entry<String, List<String>> entry : mapCategorie.entrySet()) {

				String normalizeCategorie = entry.getKey();

				List<String> categories = entry.getValue();

				for (String categorie : categories) {

					if (produitDto.getCategorie().trim().toLowerCase().contains(categorie.trim().toLowerCase())
							|| produitDto.getNom().trim().toLowerCase().contains(categorie.trim().toLowerCase())) {

						produitDto.setCategorie(normalizeCategorie);
					}
				}
			}

		} catch (Exception e) {

			throw new ProduitException("problème lors de la configuration de la catégorie");
		}
		return produitDto;

	}

	private ProduitDto normalizeGenreProduitDto(ProduitDto produitDto) throws ProduitException {

		try {

			List<String> genres = productConfiguration.getGenres();

			for (String genre : genres) {

				if (produitDto.getGenre().trim().toLowerCase().contains(genre.trim().toLowerCase())) {

					produitDto.setGenre(genre);
				}
			}

		} catch (Exception e) {

			throw new ProduitException("problème lors de la configuration du genre");
		}

		return produitDto;
	}

	private Validator getConstraintValidator() {
		Configuration<?> vConfiguration = Validation.byDefaultProvider().configure();
		ValidatorFactory vFactory = vConfiguration.buildValidatorFactory();
		Validator vValidator = vFactory.getValidator();
		return vValidator;
	}

}
