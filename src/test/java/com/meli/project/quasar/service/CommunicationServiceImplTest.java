package com.meli.project.quasar.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import com.meli.project.quasar.dao.CommunicationRequestDAO;
import com.meli.project.quasar.dao.SpaceshipCommunicationRequestDAO;
import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SpaceshipComunicationDTO;
import com.meli.project.quasar.entities.Kenobi;
import com.meli.project.quasar.entities.Satellite;
import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;
import com.meli.project.quasar.service.impl.CommunicationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationServiceImplTest {
	
	@InjectMocks
	private CommunicationServiceImpl communicationServiceImpl;
	
	@Mock
	private ApplicationContext applicationContext;

	@Mock
	private LocationService locationService;

	@Mock
	private MessageService messageService;

	@Mock
	private SpaceshipCommunicationRequestDAO spaceShipCommReqDAO;

	@Mock
	private CommunicationRequestDAO communicationRequestDAO;
	
	@Test
	public void givenSpaceshipComunicationDTOWhenMockedServicesthenGetCommunicationOk() {
		//Given
		SpaceshipComunicationDTO satComm = new SpaceshipComunicationDTO();
		satComm.setDistance(Double.valueOf(100));
		satComm.setMessage(new String[] {"Hola","mundo"});
		satComm.setName("kenobi");
		SpaceshipComunicationDTO satComm2 = satComm;
		satComm2.setName("Skywalker");
		satComm.setMessage(new String[] {""," ","soy","un","mensaje"});
		SpaceshipComunicationDTO[] request= new SpaceshipComunicationDTO[] {satComm,satComm2};
		Kenobi kenobiSat = new Kenobi("100", "150");
		LocationDTO position = new LocationDTO(new double[] {-50,-50});
		//When
		Mockito.when(applicationContext.getBean(ArgumentMatchers.anyString(),ArgumentMatchers.eq(Satellite.class))).thenReturn(kenobiSat);
		Mockito.when(locationService.getLocation(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(position);
		Mockito.when(messageService.getMessage(ArgumentMatchers.any())).thenReturn("Hola soy un mensaje");
		//Then
		SatelliteComunicationDTO result = communicationServiceImpl.getCommunication(request);
		//Asserts
		Assert.assertNotNull(result);
	}
	
	@Test
	public void givenSpaceshipComunicationDTOWhenMockedServicesthenGetCommunicationWithOneNullSatellite() {
		//Given
		SpaceshipComunicationDTO satComm = new SpaceshipComunicationDTO();
		satComm.setDistance(Double.valueOf(100));
		satComm.setMessage(new String[] {"Hola","mundo"});
		satComm.setName("carlos");
		SpaceshipComunicationDTO satComm2 = satComm;
		satComm2.setName("carlos");
		satComm.setMessage(new String[] {""," ","soy","un","mensaje"});
		SpaceshipComunicationDTO[] request= new SpaceshipComunicationDTO[] {satComm,satComm2};
		LocationDTO position = new LocationDTO(new double[] {-50,-50});
		//When
		Mockito.when(applicationContext.getBean(ArgumentMatchers.anyString(),ArgumentMatchers.eq(Satellite.class))).thenThrow(new NoSuchBeanDefinitionException("No bean"));
		Mockito.when(locationService.getLocation(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(position);
		Mockito.when(messageService.getMessage(ArgumentMatchers.any())).thenReturn("Hola soy un mensaje");
		//Then
		SatelliteComunicationDTO result = communicationServiceImpl.getCommunication(request);
		//Asserts
		Assert.assertNotNull(result);
	}
	
	@Test
	public void givenWhenSpaceShipCommReqDAOfindAllThenGetPositionAndMessage() {
		//Given
		SpaceshipCommunicationRequestModel modelRequest = new SpaceshipCommunicationRequestModel();
		modelRequest.setDistance(Double.valueOf(100));
		modelRequest.setId(Long.parseLong("1"));
		modelRequest.setIsNotLocated(Boolean.TRUE);
		modelRequest.setMessage(", hola,este ,es, ,un,mensaje");
		modelRequest.setSatelliteName("kenobi");
		Iterable<SpaceshipCommunicationRequestModel> listRequest = Arrays.asList(modelRequest);
		//When
		Mockito.when(spaceShipCommReqDAO.findAll()).thenReturn(listRequest);
		//Then
		SatelliteComunicationDTO result = communicationServiceImpl.getPositionAndMessage();
		//Asserts
		Assert.assertNotNull(result);
	}
}
