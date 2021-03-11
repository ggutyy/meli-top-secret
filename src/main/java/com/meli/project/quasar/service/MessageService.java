package com.meli.project.quasar.service;

import java.util.List;

public interface MessageService {
	/**
	 * Service to merge all message parts in an array
	 * 
	 * @param msg partitioned
	 * @return merged message
	 */
	String getMessage(List<List<String>> msg);
	
	String joinMessageWithComma(String[] message);
	
	String[] splitCommaMsg(String message);
}
