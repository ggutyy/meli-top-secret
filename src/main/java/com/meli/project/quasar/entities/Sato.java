package com.meli.project.quasar.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.dto.LocationDTO;

@Service("SATO_SATELLITE")
public class Sato extends Satellite {
	
	public Sato(@Value("${sato.x.position}") final String xPosition,
			@Value("${sato.y.position}") final String yPosition) {
		super(new LocationDTO(xPosition, yPosition), Sato.class.getName());
	}
}
