package com.gigaboutique.gigabatchnotifyservice.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchnotifyservice.configuration.MailConfiguration;
import com.gigaboutique.gigabatchnotifyservice.dto.ProduitDto;
import com.gigaboutique.gigabatchnotifyservice.dto.UtilisateurDto;
import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;
import com.gigaboutique.gigabatchnotifyservice.proxy.ProduitProxy;
import com.gigaboutique.gigabatchnotifyservice.proxy.UtilisateurProxy;

@Service
public class NotifyUsersService {

	@Autowired
	UtilisateurProxy utilisateurProxy;

	@Autowired
	ProduitProxy produitProxy;

	@Autowired
	UpdatePanierService updatePanierService;

	@Autowired
	MailSenderService mailSenderService;

	@Autowired
	MailConfiguration mailConfiguration;

	public boolean notifyUsers() throws BatchNotifyException {

		boolean bool = false;

		try {

			List<UtilisateurDto> utilisateursDto = utilisateurProxy.getUtilisateurs();

				for (UtilisateurDto utilisateurDto : utilisateursDto) {

					Set<Integer> idProduits = utilisateurDto.getProduits();

					for (Integer idProduit : idProduits) {

						ProduitDto produitDto = produitProxy.getProduit(idProduit);

						if (produitDto == null) {

							int idUtilisateur = utilisateurDto.getIdUtilisateur();

							updatePanierService.update(idProduit, idUtilisateur);

							String destinataire = utilisateurDto.getMail();
							String sujet = mailConfiguration.getSujet();
							String message = mailConfiguration.getMessage();

							mailSenderService.sendMessage(destinataire, sujet, message);

							bool = true;

						}

					}

				}
				
				return bool;
			
		} catch (NullPointerException npe) {
		
			return bool;
	
		} catch (Exception e) {

			throw new BatchNotifyException("problème lors de la notification aux utilisateurs notifyUsers" + e.getMessage());

		}

	}

}
