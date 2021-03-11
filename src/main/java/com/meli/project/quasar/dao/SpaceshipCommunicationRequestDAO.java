package com.meli.project.quasar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meli.project.quasar.model.SpaceshipCommunicationRequestModel;

@Repository
public interface SpaceshipCommunicationRequestDAO extends CrudRepository<SpaceshipCommunicationRequestModel, Long> {

}
