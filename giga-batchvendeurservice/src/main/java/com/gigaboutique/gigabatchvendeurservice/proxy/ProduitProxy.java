package com.gigaboutique.gigabatchvendeurservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giga-productservice", url = "localhost:8002")
public interface ProduitProxy {
	
	@GetMapping("/vendeur/add/{idvendeur}")
	public void addVendeur(@PathVariable("idvendeur") int idVendeur);

}
