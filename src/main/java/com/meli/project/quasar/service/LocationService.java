package com.meli.project.quasar.service;

import com.meli.project.quasar.dto.LocationDTO;

public interface LocationService {
	
	/**
	 * Service to calculate the final position of another position using the distance
	 * 
	 * @param actualPosition
	 * @param distance
	 * @return calculated position
	 */
	LocationDTO getLocation(LocationDTO actualPosition, Double distance);
	
}
