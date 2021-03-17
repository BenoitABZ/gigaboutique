package com.gigaboutique.gigabatchnotifyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;
import com.gigaboutique.gigabatchnotifyservice.proxy.ProduitPanierProxy;

@Service
public class UpdatePanierService {

	@Autowired
	ProduitPanierProxy produitPanierProxy;

	public void update(int idProduit, int idUtilisateur) throws BatchNotifyException {
		try {

			produitPanierProxy.removeProduit(idProduit, idUtilisateur);

		} catch (Exception e) {

			throw new BatchNotifyException("problème lors de la mise à jour du panier utilisateur update" + e.getMessage());
		}

	}

}
