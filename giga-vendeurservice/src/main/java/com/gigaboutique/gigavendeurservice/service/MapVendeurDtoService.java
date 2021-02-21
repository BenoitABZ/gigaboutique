package com.gigaboutique.gigavendeurservice.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigavendeurservice.dao.CommentaireDao;
import com.gigaboutique.gigavendeurservice.dto.CommentaireDto;
import com.gigaboutique.gigavendeurservice.dto.VendeurDto;
import com.gigaboutique.gigavendeurservice.exception.VendeurException;
import com.gigaboutique.gigavendeurservice.model.CommentaireBean;
import com.gigaboutique.gigavendeurservice.model.VendeurBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class MapVendeurDtoService {

	@Autowired
	CommentaireDao commentaireDao;

	public VendeurDto convertToVendeurDto(VendeurBean vendeurBean) throws VendeurException {
		
		try {

		VendeurDto vendeurDto = new VendeurDto();
		vendeurDto.setId(vendeurBean.getId());
		vendeurDto.setNom(vendeurBean.getNom());
		vendeurDto.setLogo(vendeurBean.getLogo());
		vendeurDto.setNote(vendeurBean.getNote());
		vendeurDto.setNombreDeCommentaires(vendeurBean.getNombreDeCommentaires());

		List<CommentaireBean> commentairesBean = vendeurBean.getCommentaires();

		List<CommentaireDto> commentairesDto = new ArrayList<>();

		for (CommentaireBean commentaireBean : commentairesBean) {

			CommentaireDto commentaireDto = new CommentaireDto();
			commentaireDto.setAuteur(commentaireBean.getAuteur());
			commentaireDto.setDescription(commentaireBean.getDescription());
			commentaireDto.setNote(commentaireBean.getNote());
			
			DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			commentaireDto.setDateCommentaire(commentaireBean.getDateCommentaire().format(outputFormat));

			commentairesDto.add(commentaireDto);
		}

		vendeurDto.setCommentaires(commentairesDto);

		return vendeurDto;
		
		} catch (Exception e) {

			throw new VendeurException("problème convertToVendeurDto" + e.getMessage());
		}

	}

	public VendeurBean convertToVendeurBean(VendeurDto vendeurDto) throws VendeurException {

		VendeurBean vendeurBean = new VendeurBean();

		try {

			vendeurBean.setNom(vendeurDto.getNom());
			vendeurBean.setLogo(vendeurDto.getLogo());
			vendeurBean.setNote(vendeurDto.getNote());
			vendeurBean.setNombreDeCommentaires(vendeurDto.getNombreDeCommentaires());

			List<CommentaireDto> commentairesDto = vendeurDto.getCommentaires();

			List<CommentaireBean> commentairesBean = new ArrayList<>();

			for (CommentaireDto commentaireDto : commentairesDto) {

				CommentaireBean commentaireBean = new CommentaireBean();
				commentaireBean.setAuteur(commentaireDto.getAuteur());
				commentaireBean.setDescription(commentaireDto.getDescription());
				commentaireBean.setNote(commentaireDto.getNote());
				commentaireBean.setVendeur(vendeurBean);
				
				DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

				commentaireBean.setDateCommentaire(LocalDate.parse(commentaireDto.getDateCommentaire(), inputFormat));

				commentairesBean.add(commentaireBean);
			}

			vendeurBean.setCommentaires(commentairesBean);

			return vendeurBean;

		} catch (Exception e) {

			throw new VendeurException("problème convertToVendeurBean" + e.getMessage());
		}
	}

	public VendeurBean updateToVendeurBean(VendeurBean vendeurBean, VendeurDto vendeurDto) throws VendeurException {

		try {

			List<CommentaireBean> commentairesBeanToRemove = vendeurBean.getCommentaires();
			commentaireDao.deleteAll(commentairesBeanToRemove);
			
			vendeurBean.setNom(vendeurDto.getNom());
			vendeurBean.setLogo(vendeurDto.getLogo());
			vendeurBean.setNote(vendeurDto.getNote());
			vendeurBean.setNombreDeCommentaires(vendeurDto.getNombreDeCommentaires());

			vendeurBean.getCommentaires().clear();

			List<CommentaireDto> commentairesDto = vendeurDto.getCommentaires();

			List<CommentaireBean> commentairesBean = new ArrayList<>();

			for (CommentaireDto commentaireDto : commentairesDto) {

				CommentaireBean commentaireBean = new CommentaireBean();
				commentaireBean.setAuteur(commentaireDto.getAuteur());
				commentaireBean.setDescription(commentaireDto.getDescription());
				commentaireBean.setNote(commentaireDto.getNote());
				commentaireBean.setVendeur(vendeurBean);

				DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
				DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				commentaireBean.setDateCommentaire(LocalDate.parse(LocalDate.parse(commentaireDto.getDateCommentaire(), inputFormat).format(outputFormat), outputFormat));

				commentairesBean.add(commentaireBean);
			}

			vendeurBean.setCommentaires(commentairesBean);

			return vendeurBean;

		} catch (Exception e) {

			throw new VendeurException("problème updateToVendeurBean" + e.getMessage());
		}
	}
}
