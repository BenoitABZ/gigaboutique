package com.gigaboutique.gigauserservice;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.gigaboutique.gigauserservice.dao.ProduitPanierDao;
import com.gigaboutique.gigauserservice.dao.UtilisateurDao;
import com.gigaboutique.gigauserservice.dto.UtilisateurDto;
import com.gigaboutique.gigauserservice.exception.TechnicalException;
import com.gigaboutique.gigauserservice.model.UtilisateurBean;
import com.gigaboutique.gigauserservice.service.MapUtilisateurDtoService;
import com.gigaboutique.gigauserservice.service.ProduitPanierService;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProduitPanierServiceTests {

	@InjectMocks
	ProduitPanierService produitPanierService;

	@Mock
	MapUtilisateurDtoService mapUtilisateurDtoService;

	@Mock
	ModelMapper modelMapper;

	@Mock
	ProduitPanierDao produitPanierDao;

	@Mock
	UtilisateurDao utilisateurDao;

	UtilisateurBean utilisateurBean;

	UtilisateurDto utilisateurDto;

	@BeforeAll
	public void setUp() {

		utilisateurDto = new UtilisateurDto();

		Set<Integer> produitsPanierId = new HashSet<>();
		int number = 1;
		produitsPanierId.add(number);

		utilisateurDto.setProduits(produitsPanierId);

		utilisateurBean = new UtilisateurBean();

		utilisateurBean.setId(1);

	}

	@Test
	public void addProduitPanierTest1() throws TechnicalException {

		when(mapUtilisateurDtoService.convertToUtilisateurBean(utilisateurDto, modelMapper))
				.thenReturn(utilisateurBean);

		when(produitPanierDao.findById(utilisateurBean.getId())).thenReturn(Optional.empty());

		utilisateurDto = produitPanierService.addProduitPanier(utilisateurDto);

		verify(utilisateurDao, times(1)).save(utilisateurBean);
	}

	@Test
	public void addProduitPanierTest2() {

		when(mapUtilisateurDtoService.convertToUtilisateurBean(utilisateurDto, modelMapper))
				.thenThrow(NullPointerException.class);

		assertThrows(TechnicalException.class, () -> {
			produitPanierService.addProduitPanier(utilisateurDto);
		});

	}

}
