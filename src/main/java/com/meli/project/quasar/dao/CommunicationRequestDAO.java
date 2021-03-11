package com.meli.project.quasar.dao;

import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;

public interface CommunicationRequestDAO {
	/**
	 * Find a communication request by satellite name
	 * 
	 * @param satelliteName
	 * @return SpaceshipCommunicationRequestModel
	 */
	SpaceshipCommunicationRequestModel findBySatelliteName(String satelliteName);
}
