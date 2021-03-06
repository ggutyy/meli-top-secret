package com.meli.project.quasar.service;

import com.meli.project.quasar.dto.CommunicationDTO;
import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SpaceshipComunicationDTO;

public interface CommunicationService {
	
	/**
	 * Service to get communication with a list of selected satellites
	 * 
	 * @param SpaceshipComunicationDTO[]
	 * @return SatelliteComunicationDTO
	 */
	SatelliteComunicationDTO getCommunication(SpaceshipComunicationDTO[] request);
	
	/**
	 * Service to save communication
	 * 
	 * @param satelliteName
	 * @param commRequest
	 */
	void saveCommunication(String satelliteName, CommunicationDTO commRequest);
	
	/**
	 * Service to get position and complete message
	 * 
	 * @return SatelliteComunicationDTO
	 */
	SatelliteComunicationDTO getPositionAndMessage();
}
