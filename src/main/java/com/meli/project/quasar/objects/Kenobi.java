package com.meli.project.quasar.objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.meli.project.quasar.dto.LocationDTO;

@Service("KENOBI_SATELLITE")
public class Kenobi extends Satellite {

	public Kenobi(@Value("${kenobi.x.position}") final String xPosition,
			@Value("${kenobi.y.position}") final String yPosition) {
		super(new LocationDTO(xPosition, yPosition), Kenobi.class.getName());
	}
}
