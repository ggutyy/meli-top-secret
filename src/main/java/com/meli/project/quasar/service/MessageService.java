package com.meli.project.quasar.service;

public interface MessageService {
	/**
	 * Service to merge all message parts in an array
	 * 
	 * @param msg partitioned
	 * @return merged message
	 */
	String getMessage(String[] msg);
}
