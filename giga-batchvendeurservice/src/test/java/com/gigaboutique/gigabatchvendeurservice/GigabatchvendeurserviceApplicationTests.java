package com.gigaboutique.gigabatchvendeurservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gigaboutique.gigabatchvendeurservice.configuration.SellerConfiguration;
import com.gigaboutique.gigabatchvendeurservice.dto.VendeurDto;
import com.gigaboutique.gigabatchvendeurservice.exception.BatchVendeurException;
import com.gigaboutique.gigabatchvendeurservice.proxy.VendeurProxy;
import com.gigaboutique.gigabatchvendeurservice.scheduler.Scheduler;
import com.gigaboutique.gigabatchvendeurservice.service.ScrapingGlobalService;
import com.gigaboutique.gigabatchvendeurservice.service.ScrapingUnitService;

@SpringBootTest
class GigabatchvendeurserviceApplicationTests {

	@Autowired
	ScrapingUnitService scrapingUnitService;

	@Autowired
	ScrapingGlobalService scrapingGlobalService;

	@Autowired
	SellerConfiguration sellerConfiguration;

	@Autowired
	VendeurProxy vendeurProxy;

	@Autowired
	Scheduler scheduler;

	@Test
	void contextLoads() {
	}

	@Test
	public void getVendeur() throws BatchVendeurException {

		VendeurDto vendeurDto = scrapingUnitService.getVendeur(sellerConfiguration.getPatternLinkToScrap() + sellerConfiguration.getSellers().get(0));

		assertNotNull(vendeurDto);

		assertEquals(3, vendeurDto.getCommentaires().size());

	}

	@Test
	public void setVendeursTest() throws BatchVendeurException {

		List<VendeurDto> vendeursDto = scrapingGlobalService.setVendeurs();

		for (VendeurDto vendeurDto : vendeursDto) {

			VendeurDto vendeurDtoTest = vendeurProxy.getVendeur(vendeurDto.getId());

			assertTrue(vendeurDtoTest.getId() > 0);
		}

	}

	@Test
	public void scheduledTest() throws InterruptedException {

		Thread.sleep(62000);

		assertEquals(2, Scheduler.getCounter());

	}

}
