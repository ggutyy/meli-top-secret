package com.meli.project.quasar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "spaceship_communication_request" )
public class SpaceshipCommunicationRequestModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9137896920237981524L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "satellite_name")
	private String satelliteName;

	@Column(name = "distance")
	private Double distance;
	
	@Column(name = "message")
	private String message;

	@Column(name = "is_not_located")
	private Boolean isNotLocated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSatelliteName() {
		return satelliteName;
	}

	public void setSatelliteName(String satelliteName) {
		this.satelliteName = satelliteName;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsNotLocated() {
		return isNotLocated;
	}

	public void setIsNotLocated(Boolean isNotLocated) {
		this.isNotLocated = isNotLocated;
	}
	
}
