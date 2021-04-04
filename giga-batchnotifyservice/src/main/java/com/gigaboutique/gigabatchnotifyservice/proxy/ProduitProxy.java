package com.gigaboutique.gigabatchnotifyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gigaboutique.gigabatchnotifyservice.dto.ProduitDto;

@FeignClient(name = "giga-productservice", url = "localhost:8002")
public interface ProduitProxy {

	@GetMapping("/produit/getid/{id}")
	public ProduitDto getProduit(@PathVariable("id") int id);

}
