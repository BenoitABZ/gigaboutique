package com.gigaboutique.gigaproductservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.VendeurBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class VendeurService {

	@Autowired
	VendeurDao vendeurDao;

	public void addVendeur(int id) throws TechnicalException {
		
		try {

		VendeurBean vendeur = vendeurDao.findById(id);

		if (vendeur == null) {
			
			vendeur = new VendeurBean();			
			vendeur.setId(id);

			vendeurDao.save(vendeur);
		}

	}catch(Exception e) {
		
		throw new TechnicalException("problème lors de l'ajout d'un vendeur");
	}
	}
}
