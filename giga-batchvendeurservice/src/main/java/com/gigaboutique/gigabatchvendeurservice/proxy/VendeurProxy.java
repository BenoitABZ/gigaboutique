package com.gigaboutique.gigabatchvendeurservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gigaboutique.gigabatchvendeurservice.dto.VendeurDto;

@FeignClient(name = "giga-vendeurservice", url = "localhost:8003")
public interface VendeurProxy {

	@PostMapping(value = "/vendeur/add")
	public VendeurDto addVendeur(@RequestBody VendeurDto vendeurDto);

	@GetMapping(value = "/vendeur/get/{idVendeur}")
	public VendeurDto getVendeur(@PathVariable("idVendeur") int idVendeur);

}
