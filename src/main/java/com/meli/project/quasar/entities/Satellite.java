package com.meli.project.quasar.entities;

import com.meli.project.quasar.dto.LocationDTO;

public abstract class Satellite {
	
	private String name;

	private LocationDTO location;
	
	protected Satellite(LocationDTO location, String name) {
		this.location = location;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public void setLocation(LocationDTO location) {
		this.location = location;
	}

}
