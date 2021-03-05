package com.gigaboutique.gigavendeurservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigavendeurservice.dao.VendeurDao;
import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.exception.TechnicalException;
import com.gigaboutique.gigavendeurservice.exception.VendeurException;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;

import javassist.NotFoundException;

@Service
@Transactional(rollbackFor = Exception.class)
public class VendeurService {

	@Autowired
	MapVendeurDtoService mapVendeurDtoService;

	@Autowired
	VendeurDao vendeurDao;

	public VendeurDto addVendeur(VendeurDto vendeurDto) throws VendeurException, TechnicalException {

		try {
			/*

			VendeurBean vendeurBeanToSave = null;

			VendeurBean vendeurBeanToUpdate = null;
			
			

			VendeurBean vendeurBean = vendeurDao.findByNom(vendeurDto.getNom());

			if (vendeurBean != null) {

				vendeurBeanToUpdate = mapVendeurDtoService.updateToVendeurBean(vendeurBean, vendeurDto);

				vendeurBeanToUpdate = vendeurDao.save(vendeurBeanToUpdate);

			} else {

				vendeurBeanToSave = mapVendeurDtoService.convertToVendeurBean(vendeurDto);

				vendeurBeanToSave = vendeurDao.save(vendeurBeanToSave);

			}

			if (vendeurBeanToSave != null) {

				vendeurDto.setId(vendeurBeanToSave.getId());

			} else {

				vendeurDto.setId(vendeurBeanToUpdate.getId());
			}

			return vendeurDto;
			
			*/
			
			VendeurBean vendeurBean = mapVendeurDtoService.convertToVendeurBean(vendeurDto);

			vendeurBean = vendeurDao.save(vendeurBean);
			
			vendeurDto.setId(vendeurBean.getId());
			
			return vendeurDto;

		} catch (Exception e) {

			throw new TechnicalException("problème addVendeur" + e.getMessage());
		}

	}

	public VendeurDto getVendeur(int id) throws VendeurException, TechnicalException, NotFoundException {

		try {

			VendeurBean vendeurBean = vendeurDao.findById(id);

			if (vendeurBean == null) {

				throw new NotFoundException("vendeur n°" + id + "introuvable en base de données");

			}

			VendeurDto vendeurDto = mapVendeurDtoService.convertToVendeurDto(vendeurBean);

			return vendeurDto;

		} catch (Exception e) {

			throw new TechnicalException("problème getVendeur" + e.getMessage());
		}

	}

}
