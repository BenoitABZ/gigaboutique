package com.gigaboutique.gigavendeurservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class VendeurException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendeurException() {
		super();

	}

	public VendeurException(String arg0) {
		super(arg0);

	}

}
