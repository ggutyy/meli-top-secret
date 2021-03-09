package com.meli.project.quasar.objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.service.LocationService;

public abstract class Satellite {
	
	protected String name;

	protected LocationDTO location;

	@Autowired
	protected LocationService locationService;
	
	protected LocationDTO getLocation(Double distance) {
		return locationService.getLocation(location, distance);
	}

	protected String getMessage(String[] receivedMessage) {
		return null;
	}

	protected Satellite(LocationDTO location, String name) {
		this.location = location;
		this.name = name;
	}

}
