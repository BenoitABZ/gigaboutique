package com.gigaboutique.gigabatchproduitservice.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;

@FeignClient(name = "giga-productservice", url = "localhost:8002")
public interface ProduitProxy {

	@GetMapping("/produit/add")
	public void addProduit(@RequestBody ProduitDto produitDto);
	
	@GetMapping("/produit/getall")
	public List<ProduitDto> getProduits(@RequestParam String page, @RequestParam String size);

	@PutMapping("/produit/setfalse")
	public void setFalse();

	@DeleteMapping("/produit/removeiffalse")
	public void removeFalse();

}
