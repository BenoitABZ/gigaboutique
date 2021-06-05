package com.gigaboutique.gigabatchvendeurservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gigaboutique.gigabatchvendeurservice.configuration.SellerConfiguration;
import com.gigaboutique.gigabatchvendeurservice.exception.BatchVendeurException;
import com.gigaboutique.gigabatchvendeurservice.service.ScrapingGlobalService;

@Component
public class Scheduler {

	@Autowired
	ScrapingGlobalService scrappingGlobalService;

	private static int counter = 0;

	//private static final int rate = 60000;
	
	//fixedRate = rate

	@Scheduled(cron = "* */5 * * * *")
	public void runBatchVendeur() throws BatchVendeurException {

		scrappingGlobalService.setVendeurs();

		setCounter(getCounter() + 1);

	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Scheduler.counter = counter;
	}

}
