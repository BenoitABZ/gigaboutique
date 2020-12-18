package com.gigaboutique.gigauserservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigauserservice.dao.DaoManager;
import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.model.ProduitPanierBean;
import com.gigaboutique.gigauserservice.model.ProduitPanierId;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;

@Service
public class MapUtilisateurDtoService {
	
	    @Autowired()
	    private DaoManager daoManager;
	    
	    UtilisateurDao utilisateurDao = daoManager.getUtilisateurDao();
	    
	    ProduitPanierDao produitPanierDao = daoManager.getProduitPanierDao();

	    public List<UtilisateurDto> getUtilisateurs(){
	        return ((List<UtilisateurBean>) utilisateurDao
	                .findAll())
	                .stream()
	                .map(this::convertToUtilisateurDto)
					        .collect(Collectors.toList());
	    }

	    private UtilisateurDto convertToUtilisateurDto(UtilisateurBean utilisateur) {
	    	UtilisateurDto utilisateurDto = new UtilisateurDto();
	    	utilisateurDto.setIdUtilisateur(utilisateur.getId());
	    	utilisateurDto.setNom(utilisateur.getNom());
	    	utilisateurDto.setPrenom(utilisateur.getPrenom());
	    	utilisateurDto.setMail(utilisateur.getMail());
	    	
	    	List<ProduitPanierId> produits = this.populateProduitPanierId(utilisateur);
	    	
	    	utilisateurDto.setProduits(produits);
	    	
	    	return utilisateurDto;
	    	
	}
	    
	    private List<ProduitPanierId> populateProduitPanierId(UtilisateurBean utilisateur){
	    	
	    	return ((List<ProduitPanierBean>) produitPanierDao
	    			.findByIdUtilisateur(utilisateur.getId()))
	                .stream()
	                .map(ProduitPanierBean::getPpId)
					        .collect(Collectors.toList());
	    	

	    	
	    }

}
