package com.meli.project.quasar.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.dto.LocationDTO;

@Service("SKYWALKER_SATELLITE")
public class Skywalker extends Satellite {

	public Skywalker(@Value("${skywalker.x.position}") final String xPosition,
			@Value("${skywalker.y.position}") final String yPosition) {
		super(new LocationDTO(xPosition, yPosition), Skywalker.class.getName());
	}
}
