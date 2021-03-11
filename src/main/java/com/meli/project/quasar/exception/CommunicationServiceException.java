package com.meli.project.quasar.exception;

public class CommunicationServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186609047970778748L;
	
	public CommunicationServiceException(){
		super();
	}
	
	public CommunicationServiceException(String message){
		super(message);
	}
	
	public CommunicationServiceException(String message, Exception excp){
		super(message, excp);
	}
}
