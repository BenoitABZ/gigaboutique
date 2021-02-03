package com.gigaboutique.gigaproductservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigaproductservice.dao.CategorieDao;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.CategorieBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategorieService {

	@Autowired
	CategorieDao categorieDao;

	public List<String> getCategories() throws TechnicalException {

		List<String> categoriesDispos = new ArrayList<>();

		try {

			List<CategorieBean> categories = categorieDao.findAll();

			for (CategorieBean categorie : categories) {

				if (!categorie.getProduits().isEmpty()) {

					categoriesDispos.add(categorie.getCategorie());
				}
			}

		} catch (Exception e) {

			throw new TechnicalException("problème lors de l'ajout d'un vendeur");
		}

		return categoriesDispos;
	}

}
