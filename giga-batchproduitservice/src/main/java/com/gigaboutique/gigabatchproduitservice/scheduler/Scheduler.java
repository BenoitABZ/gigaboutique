package com.gigaboutique.gigabatchproduitservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gigaboutique.gigabatchproduitservice.exception.BatchProduitException;
import com.gigaboutique.gigabatchproduitservice.service.ScrapingGlobalService;

@Component
public class Scheduler {
/*
	@Autowired
	ScrapingGlobalService scrappingGlobalService;
*/
	private static int counter = 0;

	private static final int rate = 60000;

	@Scheduled(fixedRate = rate)
	public void runBatchVendeur() throws BatchProduitException {
/*
		scrappingGlobalService.setVendeurs();

		setCounter(getCounter() + 1);
*/
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Scheduler.counter = counter;
	}

}
