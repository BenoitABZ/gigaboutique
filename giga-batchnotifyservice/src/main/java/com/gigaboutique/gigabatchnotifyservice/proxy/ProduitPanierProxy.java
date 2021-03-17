package com.gigaboutique.gigabatchnotifyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giga-userservice", url = "localhost:8001")
public interface ProduitPanierProxy {

	@GetMapping("/add")
	public void addProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur);

	@DeleteMapping("/remove")
	public void removeProduit(@RequestParam int idProduit, @RequestParam int idUtilisateur);

}
