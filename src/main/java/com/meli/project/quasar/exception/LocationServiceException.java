package com.meli.project.quasar.exception;

public class LocationServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3432577456061474895L;
	
	public LocationServiceException(){
		super();
	}
	
	public LocationServiceException(String message){
		super(message);
	}
	
	public LocationServiceException(String message, Exception excp){
		super(message, excp);
	}
}
