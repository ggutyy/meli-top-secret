package com.meli.project.quasar.exception;

public class MessageServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2716198817643700698L;

	public MessageServiceException(String message){
		super(message);
	}
	
	public MessageServiceException(String message, Exception excp){
		super(message, excp);
	}
}
