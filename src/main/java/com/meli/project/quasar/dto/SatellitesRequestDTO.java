package com.meli.project.quasar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SatellitesRequestDTO {
	
	@JsonProperty("satellites")
	private SpaceshipComunicationDTO[] satellitesCommnucations;

	public SpaceshipComunicationDTO[] getSatellitesCommnucations() {
		return satellitesCommnucations;
	}

	public void setSatellitesCommnucations(SpaceshipComunicationDTO[] satellitesCommnucations) {
		this.satellitesCommnucations = satellitesCommnucations;
	}
	
}
