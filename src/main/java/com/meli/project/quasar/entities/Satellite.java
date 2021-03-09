package com.meli.project.quasar.entities;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.service.LocationService;
import com.meli.project.quasar.service.MessageService;

public abstract class Satellite {
	
	protected String name;

	protected LocationDTO location;
	
	protected Satellite(LocationDTO location, String name) {
		this.location = location;
		this.name = name;
	}

	@Autowired
	protected LocationService locationService;
	
	@Autowired
	protected MessageService messageService;
	
	protected LocationDTO getLocation(Double distance) {
		return locationService.getLocation(location, distance);
	}

	protected String getMessage(String[] receivedMessage) {
		return messageService.getMessage(receivedMessage);
	}

}
