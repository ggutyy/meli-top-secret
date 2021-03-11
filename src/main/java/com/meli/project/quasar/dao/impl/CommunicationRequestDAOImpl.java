package com.meli.project.quasar.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.meli.project.quasar.dao.CommunicationRequestDAO;
import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;

@Repository
public class CommunicationRequestDAOImpl implements CommunicationRequestDAO {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public SpaceshipCommunicationRequestModel findBySatelliteName(String satelliteName) {
		try{
			TypedQuery<SpaceshipCommunicationRequestModel> query = entityManager.createQuery(
				"SELECT commRequest FROM SpaceshipCommunicationRequestModel commRequest" + 
						" WHERE commRequest.isNotLocated = :isNotLocated" + 
							" AND commRequest.satelliteName = :satelliteName", SpaceshipCommunicationRequestModel.class);
			query.setParameter("isNotLocated", Boolean.TRUE);
			query.setParameter("satelliteName", satelliteName);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
