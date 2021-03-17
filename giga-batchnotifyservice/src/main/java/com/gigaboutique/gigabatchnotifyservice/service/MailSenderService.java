package com.gigaboutique.gigabatchnotifyservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gigaboutique.gigabatchnotifyservice.exception.BatchNotifyException;

@Service
public class MailSenderService {

	@Autowired
	public JavaMailSender mailSender;

	public void sendMessage(String destinataire, String sujet, String message) throws BatchNotifyException {

		try {

			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(destinataire);
			mail.setSubject(sujet);
			mail.setText(message);
			mailSender.send(mail);

		} catch (MailException me) {

			throw new BatchNotifyException("problème pendant sendMessage" + me.getMessage());
		}

	}

}
