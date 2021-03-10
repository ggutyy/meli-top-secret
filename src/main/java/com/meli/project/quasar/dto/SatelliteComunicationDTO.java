package com.meli.project.quasar.dto;

public class SatelliteComunicationDTO {
	
	private LocationDTO position;
	
	private String message;
	
	public SatelliteComunicationDTO(LocationDTO position, String message) {
		this.position = position;
		this.message = message;
	}
	
	public LocationDTO getPosition() {
		return position;
	}

	public void setPosition(LocationDTO position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
