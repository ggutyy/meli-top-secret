package com.meli.project.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.project.quasar.dto.SatelliteComunicationDTO;
import com.meli.project.quasar.dto.SatellitesRequestDTO;
import com.meli.project.quasar.exception.LocationServiceException;
import com.meli.project.quasar.exception.MessageServiceException;
import com.meli.project.quasar.service.CommunicationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RebelComunicationController {
	
	@Autowired
	private CommunicationService communicationService;
	
	@PostMapping(path = "/topSecret", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Stablish communication with spaceship")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Returns the position and the messages emited by spaceship"))
	public ResponseEntity<SatelliteComunicationDTO> topSecret(@RequestBody SatellitesRequestDTO request) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(communicationService.getCommunication(request.getSatellitesCommnucations()));	
		} catch (MessageServiceException msgServExcp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgServExcp.getMessage(),msgServExcp);
		} catch (LocationServiceException locServExcp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, locServExcp.getMessage(),locServExcp);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
