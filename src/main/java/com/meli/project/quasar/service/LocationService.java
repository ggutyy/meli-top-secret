package com.meli.project.quasar.service;

import java.util.List;

import com.meli.project.quasar.dto.LocationDTO;

public interface LocationService {
	
	/**
	 * Service to calculate the final position using the distances between other positions
	 * 
	 * @param actualPositions
	 * @param emiterDistances
	 * @return calculated position
	 */
	LocationDTO getLocation(List<LocationDTO> actualsPositions, List<Double> emiterDistances);
	
}
