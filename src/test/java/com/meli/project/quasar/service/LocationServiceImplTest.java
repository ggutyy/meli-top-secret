package com.meli.project.quasar.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.exception.LocationServiceException;
import com.meli.project.quasar.service.impl.LocationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {
	
	@InjectMocks
	private LocationServiceImpl locationServiceImpl;
	
	@Test
	public void givenActualsPositionsEmiterDistancesWhenthenGetLocationOk() {
		//Given
		LocationDTO locationDTO = new LocationDTO("100", "100");
		List<LocationDTO> actualsPositions = new ArrayList<>();
		actualsPositions.add(locationDTO);
		actualsPositions.add(locationDTO);
		List<Double> emiterDistances = Arrays.asList(Double.valueOf(100),Double.valueOf(-50));
		
		//When
		//Then
		LocationDTO result = locationServiceImpl.getLocation(actualsPositions, emiterDistances);
		//Asserts
		Assert.assertNotNull(result);
	}
	
	@Test
	public void givenActualsPositionsEmiterDistancesWhenthenGetLocationThrowsLocationServiceException() {
		//Given
		LocationDTO locationDTO = new LocationDTO("100", "100");
		List<LocationDTO> actualsPositions = new ArrayList<>();
		actualsPositions.add(locationDTO);
		List<Double> emiterDistances = Arrays.asList(Double.valueOf(100),Double.valueOf(-50));
		LocationServiceException excp = null;
		//When
		//Then
		try {			
			locationServiceImpl.getLocation(actualsPositions, emiterDistances);
		}catch (LocationServiceException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
	}
}
