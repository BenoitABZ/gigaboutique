package com.gigaboutique.gigauserservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TechnicalException() {
		super();
	}

	public TechnicalException(String arg0) {
		super(arg0);

	}

}
