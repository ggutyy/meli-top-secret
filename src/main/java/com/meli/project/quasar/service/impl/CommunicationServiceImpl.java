package com.meli.project.quasar.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SpaceshipComunicationDTO;
import com.meli.project.quasar.entities.Satellite;
import com.meli.project.quasar.service.CommunicationService;
import com.meli.project.quasar.service.LocationService;
import com.meli.project.quasar.service.MessageService;

@Service
public class CommunicationServiceImpl implements CommunicationService{
	
	private Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private LocationService locationService;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public SatelliteComunicationDTO getCommunication(SpaceshipComunicationDTO[] request) {
		List<LocationDTO> satellitesPositions = new ArrayList<>();
		List<Double> distances = new ArrayList<>();
		List<List<String>> msgs = new ArrayList<>();
		for(SpaceshipComunicationDTO spaceshipComm : request) {
			Satellite satellite = getSatellite(spaceshipComm.getName());
			if(Objects.nonNull(satellite)) {
				satellitesPositions.add(satellite.getLocation());
				distances.add(spaceshipComm.getDistance());
				msgs.add(Arrays.asList(spaceshipComm.getMessage()));
			}
		}
		LocationDTO position = locationService.getLocation(satellitesPositions, distances);
		String message = messageService.getMessage(msgs); 
		return new SatelliteComunicationDTO(position, message);
	}

	
	public Satellite getSatellite(String satelliteName) {
		try {
			return applicationContext.getBean(satelliteName.toUpperCase().trim() + "_SATELLITE",Satellite.class);
		} catch (NoSuchBeanDefinitionException excp) {
			logger.error("No satellite founded", excp);
			return null;
		}
	}
	
}
