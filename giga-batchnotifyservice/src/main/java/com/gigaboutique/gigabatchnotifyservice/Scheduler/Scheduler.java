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

	private static final int rate = 150000;

	@Scheduled(fixedRate = rate)
	public void runBatchVendeur() throws BatchNotifyException {

		notifyUsersService.notifyUsers();

		setCounter(getCounter() + 1);

	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Scheduler.counter = counter;
	}

}
