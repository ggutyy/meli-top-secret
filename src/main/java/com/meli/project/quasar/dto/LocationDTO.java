package com.meli.project.quasar.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {
	@JsonProperty("x")
	private Double xPosition;
	@JsonProperty("y")
	private Double yPosition;
	
	public LocationDTO(double[] position) {
		this.xPosition = Double.valueOf(position[0]);
		this.yPosition = Double.valueOf(position[1]);
	}
	
	public LocationDTO(String xPosition, String yPosition) {
		this.xPosition = Double.valueOf(xPosition);
		this.yPosition = Double.valueOf(yPosition);
	}

	public Double getxPosition() {
		return xPosition;
	}

	public void setxPosition(Double xPosition) {
		this.xPosition = xPosition;
	}

	public Double getyPosition() {
		return yPosition;
	}

	public void setyPosition(Double yPosition) {
		this.yPosition = yPosition;
	}
	
	@JsonIgnore
	public Boolean isInvalidPosition() {
		return Objects.nonNull(xPosition) && Objects.nonNull(yPosition) ? Boolean.FALSE:Boolean.TRUE;
	}
	
}
