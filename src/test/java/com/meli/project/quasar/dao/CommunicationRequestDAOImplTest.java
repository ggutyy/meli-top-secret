package com.meli.project.quasar.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.meli.project.quasar.dao.impl.CommunicationRequestDAOImpl;
import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;

@RunWith(MockitoJUnitRunner.class)
public class CommunicationRequestDAOImplTest {
	
	@InjectMocks
	private CommunicationRequestDAOImpl commDaoImpl;
	
	@Mock
	private EntityManager entityManager;
	
	@Mock
	private TypedQuery<Object> query;
	
	@Test
	public void givenSatelliteNameWhenEntityManagerAndQueryThenFindBySatelliteNameOk() {
		//Given
		String satelliteName = "kenobi";
		SpaceshipCommunicationRequestModel result = new SpaceshipCommunicationRequestModel();
		//When
		Mockito.when(entityManager.createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(result);
		//Then
		result = commDaoImpl.findBySatelliteName(satelliteName);
		
		Assert.assertNotNull(result);
	}
	
	@Test
	public void givenSatelliteNameWhenEntityManagerAndQueryThenFindBySatelliteNameThrowsNoResultException() {
		//Given
		String satelliteName = "kenobi";
		SpaceshipCommunicationRequestModel result;
		//When
		Mockito.when(entityManager.createQuery(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(new NoResultException());
		//Then
		result = commDaoImpl.findBySatelliteName(satelliteName);
		
		Assert.assertNull(result);
	}
}
