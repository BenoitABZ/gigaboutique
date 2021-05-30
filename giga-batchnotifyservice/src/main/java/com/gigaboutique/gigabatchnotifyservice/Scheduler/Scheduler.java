package com.gigaboutique.gigabatchnotifyservice.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;
import com.gigaboutique.gigabatchnotifyservice.service.NotifyUsersService;

@Component
public class Scheduler {

	@Autowired
	NotifyUsersService notifyUsersService;

	private static int counter = 0;

	//private static final int rate = 150000;
	
	//fixedRate = rate

	@Scheduled(cron = "0 0 0 * * *")
	public void runBatchVendeur() throws BatchNotifyException {

		boolean bool = notifyUsersService.notifyUsers();

		setCounter(getCounter() + 1);
		
		System.out.println(counter + "" + bool);

	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Scheduler.counter = counter;
	}

}
