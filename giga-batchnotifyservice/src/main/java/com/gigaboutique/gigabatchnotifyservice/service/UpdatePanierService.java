package com.gigaboutique.gigabatchnotifyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;
import com.gigaboutique.gigabatchnotifyservice.proxy.UtilisateurProxy;

@Service
public class UpdatePanierService {

	@Autowired
	UtilisateurProxy utilisateurProxy;

	public void update(int idProduit, int idUtilisateur) throws BatchNotifyException {
		try {

			utilisateurProxy.removeProduit(idProduit, idUtilisateur);

		} catch (Exception e) {

			throw new BatchNotifyException("problème lors de la mise à jour du panier utilisateur update" + e.getMessage());
		}

	}

}
