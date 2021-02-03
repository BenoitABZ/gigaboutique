package com.gigaboutique.gigauserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UtilisateurException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilisateurException() {
		super();

	}

	public UtilisateurException(String arg0) {
		super(arg0);

	}

}
