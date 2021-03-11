package com.meli.project.quasar.controller;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.meli.project.quasar.dto.CommunicationDTO;
import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SatellitesRequestDTO;
import com.meli.project.quasar.exception.CommunicationServiceException;
import com.meli.project.quasar.exception.LocationServiceException;
import com.meli.project.quasar.exception.MessageServiceException;
import com.meli.project.quasar.service.CommunicationService;

@RunWith(MockitoJUnitRunner.class)
public class RebelCommunicationControllerTest {
	
	@InjectMocks
	private RebelComunicationController rebelCommunicationController;
	
	@Mock
	private CommunicationService communicationService;
	
	@Test
	public void givenSatellitesRequestDTOWhenGetCommunicationThenDotopsecretOk() {
		//Given
		SatellitesRequestDTO request = new SatellitesRequestDTO();
		double[] positions = {100,-100};
		LocationDTO locationDTO = new LocationDTO(positions);
		SatelliteComunicationDTO response = new SatelliteComunicationDTO(locationDTO, "Este es un mensaje secreto");
		ResponseEntity<SatelliteComunicationDTO> finalResponse = null;
		//When
		Mockito.when(communicationService.getCommunication(ArgumentMatchers.any())).thenReturn(response);
		//Then
		finalResponse = rebelCommunicationController.topsecret(request);
		//Asserts
		Assert.assertNotNull(finalResponse);
		Assert.assertEquals(HttpStatus.OK, finalResponse.getStatusCode());
	}
	
	@Test
	public void givenSatellitesRequestDTOWhenGetCommunicationThenDotopsecretThrowsMessageServiceException() {
		//Given
		SatellitesRequestDTO request = new SatellitesRequestDTO();
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getCommunication(ArgumentMatchers.any())).thenThrow(new MessageServiceException("MessageServiceError"));
		//Then
		try {
			rebelCommunicationController.topsecret(request);
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)) {			
			Assert.assertEquals(HttpStatus.NOT_FOUND, excp.getStatus());
		}
	}
	
	@Test
	public void givenSatellitesRequestDTOWhenGetCommunicationThenDotopsecretThrowsLocationServiceException() {
		//Given
		SatellitesRequestDTO request = new SatellitesRequestDTO();
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getCommunication(ArgumentMatchers.any())).thenThrow(new LocationServiceException("LocationServiceError"));
		//Then
		try {
			rebelCommunicationController.topsecret(request);
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.NOT_FOUND, excp.getStatus());
		}
	}
	
	@Test
	public void givenSatellitesRequestDTOWhenGetCommunicationThenDotopsecretThrowsException() {
		//Given
		SatellitesRequestDTO request = new SatellitesRequestDTO();
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getCommunication(ArgumentMatchers.any())).thenThrow(new CommunicationServiceException());
		//Then
		try {
			rebelCommunicationController.topsecret(request);
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, excp.getStatus());
		}
	}
	
	@Test
	public void givensatelliteNameAndCommunicationDTOWhenThenDoTopsecretSplitOk() {
		//Given
		String satelliteName = "kenobi";
		CommunicationDTO request = new CommunicationDTO();
		request.setDistance(Double.valueOf(100));
		request.setMessage(new String[]{"este","es","el"});
		ResponseEntity finalResponse = null;
		//When
		//Then
		finalResponse = rebelCommunicationController.topsecretSplit(satelliteName, request);
		//Asserts
		Assert.assertNotNull(finalResponse);
		Assert.assertEquals(HttpStatus.OK, finalResponse.getStatusCode());
	}
	
	@Test
	public void givensatelliteNameAndCommunicationDTOWhenThenDoTopsecretSplitThrowsException() {
		//Given
		String satelliteName = "kenobi";
		CommunicationDTO request = new CommunicationDTO();
		request.setDistance(Double.valueOf(100));
		request.setMessage(new String[]{"este","es","el"});
		ResponseStatusException excp =  null;
		//When
		Mockito.doThrow(new CommunicationServiceException()).when(communicationService).saveCommunication(ArgumentMatchers.anyString(), ArgumentMatchers.any());
		//Then
		try {			
			rebelCommunicationController.topsecretSplit(satelliteName, request);
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, excp.getStatus());
		}
	}
	
	@Test
	public void givenWhenGetCommunicationThenDotopsecretSplitGETOk() {
		//Given
		double[] positions = {100,-100};
		LocationDTO locationDTO = new LocationDTO(positions);
		SatelliteComunicationDTO response = new SatelliteComunicationDTO(locationDTO, "Este es un mensaje secreto");
		ResponseEntity<SatelliteComunicationDTO> finalResponse = null;
		//When
		Mockito.when(communicationService.getPositionAndMessage()).thenReturn(response);
		//Then
		finalResponse = rebelCommunicationController.topsecretSplit();
		//Asserts
		Assert.assertNotNull(finalResponse);
		Assert.assertEquals(HttpStatus.OK, finalResponse.getStatusCode());
	}
	
	@Test
	public void givenWhenGetPositionAndMessageThenDotopsecretSplitGETThrowsMessageServiceException() {
		//Given
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getPositionAndMessage()).thenThrow(new MessageServiceException("MessageServiceError"));
		//Then
		try{
			rebelCommunicationController.topsecretSplit();
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.NOT_FOUND, excp.getStatus());
		}
	}
	
	@Test
	public void givenWhenGetPositionAndMessageThenDotopsecretSplitGETThrowsLocationServiceException() {
		//Given
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getPositionAndMessage()).thenThrow(new LocationServiceException("MessageServiceError"));
		//Then
		try{
			rebelCommunicationController.topsecretSplit();
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.NOT_FOUND, excp.getStatus());
		}
	}
	
	@Test
	public void givenWhenGetPositionAndMessageThenDotopsecretSplitGETThrowsException() {
		//Given
		ResponseStatusException excp =  null;
		//When
		Mockito.when(communicationService.getPositionAndMessage()).thenThrow(new CommunicationServiceException("MessageServiceError"));
		//Then
		try{
			rebelCommunicationController.topsecretSplit();
		} catch (ResponseStatusException e) {
			excp = e;
		}
		//Asserts
		Assert.assertNotNull(excp);
		if(Objects.nonNull(excp)){
			Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, excp.getStatus());
		}
	}
}
