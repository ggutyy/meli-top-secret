package com.meli.project.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.meli.project.quasar.dto.CommunicationDTO;
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
	
	@PostMapping(path = "/topsecret", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Stablish communication with spaceship")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Returns the position and the messages emited by spaceship"))
	public ResponseEntity<SatelliteComunicationDTO> topsecret(@RequestBody SatellitesRequestDTO request) {
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
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/topsecret_split/{satellite_name}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Stablish communication with spaceship")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Save a communication to calculate before"))
	public ResponseEntity topsecretSplit(@PathVariable("satellite_name") String satelliteName, @RequestBody CommunicationDTO request) {
		try {
			communicationService.saveCommunication(satelliteName, request);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/topsecret_split/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get position and complete message between de spaceship and satellites")
	@ApiResponses(value = @ApiResponse(code = 200, message = "Save a communication to calculate before"))
	public ResponseEntity<SatelliteComunicationDTO> topsecretSplit() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(communicationService.getPositionAndMessage());	
		} catch (MessageServiceException msgServExcp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msgServExcp.getMessage(),msgServExcp);
		} catch (LocationServiceException locServExcp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, locServExcp.getMessage(),locServExcp);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
