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

import com.meli.project.quasar.dao.CommunicationRequestDAO;
import com.meli.project.quasar.dao.SpaceshipCommunicationRequestDAO;
import com.meli.project.quasar.dto.CommunicationDTO;
import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SpaceshipComunicationDTO;
import com.meli.project.quasar.entities.Satellite;
import com.meli.project.quasar.exception.CommunicationServiceException;
import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;
import com.meli.project.quasar.service.CommunicationService;
import com.meli.project.quasar.service.LocationService;
import com.meli.project.quasar.service.MessageService;

@Service
public class CommunicationServiceImpl implements CommunicationService {

	private Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private LocationService locationService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private SpaceshipCommunicationRequestDAO spaceShipCommReqDAO;

	@Autowired
	private CommunicationRequestDAO communicationRequestDAO;

	@Override
	public SatelliteComunicationDTO getCommunication(SpaceshipComunicationDTO[] request) {
		List<LocationDTO> satellitesPositions = new ArrayList<>();
		List<Double> distances = new ArrayList<>();
		List<List<String>> msgs = new ArrayList<>();
		for (SpaceshipComunicationDTO spaceshipComm : request) {
			Satellite satellite = getSatellite(spaceshipComm.getName());
			if (Objects.nonNull(satellite)) {
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
			return applicationContext.getBean(satelliteName.toUpperCase().trim() + "_SATELLITE", Satellite.class);
		} catch (NoSuchBeanDefinitionException excp) {
			logger.error("No satellite founded", excp);
			return null;
		}
	}

	@Override
	public void saveCommunication(String satelliteName, CommunicationDTO commRequest) {
		if (Objects.isNull(getSatellite(satelliteName))) {
			throw new CommunicationServiceException("Satellite not found.");
		}
		SpaceshipCommunicationRequestModel spaceshipCommReqModel = communicationRequestDAO
				.findBySatelliteName(satelliteName);
		if (Objects.isNull(spaceshipCommReqModel)) {
			spaceshipCommReqModel = new SpaceshipCommunicationRequestModel();
		}
		spaceshipCommReqModel.setSatelliteName(satelliteName);
		spaceshipCommReqModel.setDistance(commRequest.getDistance());
		spaceshipCommReqModel.setIsNotLocated(Boolean.TRUE);
		spaceshipCommReqModel.setMessage(messageService.joinMessageWithComma(commRequest.getMessage()));
		spaceshipCommReqModel.setDistance(commRequest.getDistance());
		spaceShipCommReqDAO.save(spaceshipCommReqModel);
	}

	@Override
	public SatelliteComunicationDTO getPositionAndMessage() {
		List<SpaceshipCommunicationRequestModel> requests = getAllRequest();
		SpaceshipComunicationDTO[] finalRequest = requests.stream().map(this::mapModelToDTO)
				.toArray(a -> new SpaceshipComunicationDTO[requests.size()]);
		return getCommunication(finalRequest);
	}

	private List<SpaceshipCommunicationRequestModel> getAllRequest() {
		List<SpaceshipCommunicationRequestModel> requests = new ArrayList<>();
		spaceShipCommReqDAO.findAll().forEach(requests::add);
		return requests;
	}

	private SpaceshipComunicationDTO mapModelToDTO(SpaceshipCommunicationRequestModel modelRequest) {
		SpaceshipComunicationDTO commDTO = new SpaceshipComunicationDTO();
		commDTO.setName(modelRequest.getSatelliteName());
		commDTO.setDistance(modelRequest.getDistance());
		commDTO.setMessage(messageService.splitCommaMsg(modelRequest.getMessage()));
		return commDTO;
	}
}
