package com.gigaboutique.gigaproductservice.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gigaboutique.gigaproductservice.dao.CategorieDao;
import com.gigaboutique.gigaproductservice.dao.GenreDao;
import com.gigaboutique.gigaproductservice.dao.ImageProduitDao;
import com.gigaboutique.gigaproductservice.dao.ProduitDao;
import com.gigaboutique.gigaproductservice.dao.TailleDao;
import com.gigaboutique.gigaproductservice.dao.TailleProduitDao;
import com.gigaboutique.gigaproductservice.dao.VendeurDao;
import com.gigaboutique.gigaproductservice.dto.ProduitDto;
import com.gigaboutique.gigaproductservice.exception.ProduitException;
import com.gigaboutique.gigaproductservice.exception.TechnicalException;
import com.gigaboutique.gigaproductservice.model.CategorieBean;
import com.gigaboutique.gigaproductservice.model.GenreBean;
import com.gigaboutique.gigaproductservice.model.ImageProduitBean;
import com.gigaboutique.gigaproductservice.model.ProduitBean;
import com.gigaboutique.gigaproductservice.model.TailleBean;
import com.gigaboutique.gigaproductservice.model.TailleProduit;
import com.gigaboutique.gigaproductservice.model.VendeurBean;

import javassist.NotFoundException;

@Service
@Transactional(rollbackFor = Exception.class)
public class MapProduitDtoService {

	@Autowired
	CategorieDao categorieDao;

	@Autowired
	GenreDao genreDao;

	@Autowired
	VendeurDao vendeurDao;

	@Autowired
	TailleProduitDao tailleProduitDao;

	@Autowired
	TailleDao tailleDao;

	@Autowired
	ProduitDao produitDao;

	@Autowired
	ImageProduitDao imageProduitDao;

	public ProduitDto convertToProduitDto(ProduitBean produit) throws ProduitException, NotFoundException {

		ProduitDto produitDto = null;

		try {

			produitDto = new ProduitDto();

			produitDto.setIdVendeur(produit.getVendeur().getId());
			produitDto.setIdProduit(produit.getIdProduit());
			produitDto.setGenre(produit.getGenre().getGenre());
			produitDto.setMarque(produit.getMarque());
			produitDto.setCategorie(produit.getCategorie().getCategorie());
			produitDto.setNom(produit.getNom());
			produitDto.setAdresseWeb(produit.getAdresseWeb());

			normalizeFormatProduitBean(produitDto, produit);

			Map<String, Boolean> map = new HashMap<String, Boolean>();

			for (TailleProduit tailleProduit : produit.getTaillesProduits()) {

				map.put(tailleProduit.getTaille().getTaille(), tailleProduit.isDisponibilite());

			}

			produitDto.setTailles(map);

			List<String> adressesWebImage = new ArrayList<>();

			for (ImageProduitBean image : produit.getImages()) {

				adressesWebImage.add(image.getAdresseWeb());

			}

			produitDto.setImages(adressesWebImage);

		} catch (NullPointerException npe) {

			throw new NotFoundException("produit(s) inexistant en base de données");

		} catch (Exception e) {

			throw new ProduitException("problème lors de la conversion du bean produit en produitDto" + e.getMessage());
		}

		return produitDto;
	}

	public void updateToProduitBean(ProduitDto produit, ProduitBean produitBean) throws TechnicalException {

		try {

			produitBean.setMaj(true);

			normalizeFormatProduitDto(produit, produitBean);

			produitDao.save(produitBean);

			Map<String, Boolean> mapTaillesProduits = produit.getTailles();

			List<TailleProduit> taillesProduits = tailleProduitDao.findAll();

			for (Map.Entry<String, Boolean> entry : mapTaillesProduits.entrySet()) {

				TailleBean tailleBean = tailleDao.findByTaille(entry.getKey());

				for (TailleProduit tailleProduit : taillesProduits) {

					if ((tailleProduit.getProduit().getIdProduit() == produitBean.getIdProduit())
							&& (tailleProduit.getTaille().getIdTaille() == tailleBean.getIdTaille())) {

						int indexProduit = tailleProduit.getProduit().getTaillesProduits().indexOf(tailleProduit);

						tailleProduit.getProduit().getTaillesProduits().remove(indexProduit);

						tailleProduit.setDisponibilite(entry.getValue());

						tailleProduit.getProduit().getTaillesProduits().add(indexProduit, tailleProduit);

						int indexTaille = tailleProduit.getTaille().getTaillesProduits().indexOf(tailleProduit);

						tailleProduit.getTaille().getTaillesProduits().remove(indexTaille);

						tailleProduit.setDisponibilite(entry.getValue());

						tailleProduit.getTaille().getTaillesProduits().add(indexTaille, tailleProduit);

						tailleProduitDao.save(tailleProduit);
					}

				}

			}

		} catch (Exception e) {

			throw new TechnicalException("problème lors de la persistence des beans produit ou TailleProduit");

		}
	}

	public ProduitBean convertToProduitBean(ProduitDto produit) throws ProduitException, TechnicalException {

		ProduitBean produitBean = null;

		try {

			produitBean = new ProduitBean();
			produitBean.setNom(produit.getNom());
			produitBean.setMarque(produit.getMarque().toUpperCase());
			produitBean.setAdresseWeb(produit.getAdresseWeb());
			produitBean.setMaj(true);

			normalizeFormatProduitDto(produit, produitBean);

			CategorieBean categorie = categorieDao.findByCategorie(produit.getCategorie());

			if (categorie != null) {

				produitBean.setCategorie(categorie);

			} else {

				CategorieBean categorieToSave = new CategorieBean();
				categorieToSave.setCategorie(produit.getCategorie());

				categorieDao.save(categorieToSave);

				produitBean.setCategorie(categorieToSave);
			}

			GenreBean genre = genreDao.findByGenre(produit.getGenre());

			if (genre != null) {

				produitBean.setGenre(genre);

			} else {

				GenreBean genreToSave = new GenreBean();
				genreToSave.setGenre(produit.getGenre());

				genreDao.save(genreToSave);

				produitBean.setGenre(genreToSave);
			}

			VendeurBean vendeur = vendeurDao.findById(produit.getIdVendeur());
			produitBean.setVendeur(vendeur);

			produitDao.save(produitBean);

			List<String> adressesWebImage = produit.getImages();

			for (String adresse : adressesWebImage) {

				ImageProduitBean image = new ImageProduitBean();
				image.setAdresseWeb(adresse);
				image.setProduit(produitBean);

				imageProduitDao.save(image);
				
				produitBean.getImages().add(image);

			}
			
			produitDao.save(produitBean);

			vendeur.getProduits().add(produitBean);

			vendeurDao.save(vendeur);

			Map<String, Boolean> mapTaillesProduits = produit.getTailles();

			for (Map.Entry<String, Boolean> entry : mapTaillesProduits.entrySet()) {

				TailleBean taille = new TailleBean();
				taille.setTaille(entry.getKey());

				tailleDao.save(taille);

				TailleProduit tailleProduit = new TailleProduit(entry.getValue(), produitBean, taille);

				produitBean.getTaillesProduits().add(tailleProduit);
				taille.getTaillesProduits().add(tailleProduit);

				tailleProduitDao.save(tailleProduit);

			}

		} catch (Exception e) {

			throw new TechnicalException("problème lors de la persistence des beans (hors produit)" + e.getMessage());

		}

		return produitBean;
	}

	private void normalizeFormatProduitDto(ProduitDto produitDto, ProduitBean produitBean) throws ProduitException {

		try {

			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);

			String prixString = produitDto.getPrix().replace(",", ".");

			double prixDouble = Double.parseDouble(prixString);

			df.format(prixDouble);

			produitBean.setPrix(prixDouble);

			String promotionString = produitDto.getPromotion();

			String numberOnly = promotionString.replaceAll("[^0-9]", "");

			produitBean.setPromotion(Integer.parseInt(numberOnly));

		} catch (Exception e) {

			throw new ProduitException("problème lors de la conversion des string en integer et double");
		}
	}

	private void normalizeFormatProduitBean(ProduitDto produitDto, ProduitBean produitBean) throws ProduitException {

		try {

			double prixDouble = produitBean.getPrix();

			String prixString = String.format("%.2f", prixDouble);

			produitDto.setPrix(prixString);

			produitDto.setPromotion(String.valueOf(produitBean.getPromotion()));

		} catch (Exception e) {

			throw new ProduitException("problème lors de la conversion des integer et double en string");
		}
	}
}
