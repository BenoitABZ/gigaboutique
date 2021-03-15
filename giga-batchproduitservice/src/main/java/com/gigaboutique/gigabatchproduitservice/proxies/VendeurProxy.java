package com.gigaboutique.gigabatchproduitservice.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gigaboutique.gigabatchproduitservice.dto.VendeurDto;

@FeignClient(name = "giga-vendeurservice", url = "localhost:8003")
public interface VendeurProxy {

	@GetMapping(value = "/vendeur/get/{idVendeur}")
	public VendeurDto getVendeur(@PathVariable("idVendeur") int idVendeur);
	
	@GetMapping(value = "/vendeur/getall")
	public List<VendeurDto> getVendeurs();

}
