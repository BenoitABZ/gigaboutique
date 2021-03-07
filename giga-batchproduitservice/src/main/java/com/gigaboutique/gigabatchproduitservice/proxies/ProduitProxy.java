package com.gigaboutique.gigabatchproduitservice.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gigaboutique.gigabatchproduitservice.dto.ProduitDto;

@FeignClient(name = "giga-productservice", url = "localhost:8002")
public interface ProduitProxy {

	@GetMapping("/produit/add")
	public void addProduit(@RequestBody ProduitDto produitDto);

	@PutMapping("/produit/setfalse")
	public void setFalse();

	@DeleteMapping("/removeiffalse")
	public void removeFalse();

}
