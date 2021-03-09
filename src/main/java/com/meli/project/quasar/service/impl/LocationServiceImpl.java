package com.meli.project.quasar.service.impl;

import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.project.quasar.dto.LocationDTO;
import com.meli.project.quasar.exception.LocationServiceException;
import com.meli.project.quasar.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {

	@Override
	public LocationDTO getLocation(LocationDTO actualPosition, Double distance) {
		validateArguments(actualPosition, distance);
		TrilaterationFunction trilaterationFunction = new TrilaterationFunction(actualPosition.getPositionArray(),
				new double[] { distance.doubleValue() });
		NonLinearLeastSquaresSolver nonLinearSqSolver = new NonLinearLeastSquaresSolver(trilaterationFunction,
				new LevenbergMarquardtOptimizer());
		double[] position = nonLinearSqSolver.solve().getPoint().toArray();
		return new LocationDTO(position);
	}

	private void validateArguments(LocationDTO actualPosition, Double distance) {
		if (Objects.isNull(actualPosition) || Objects.isNull(distance)) {
			throw new LocationServiceException("Arguments can not be null.");
		}
		if (BooleanUtils.isTrue(actualPosition.isInvalidPosition())) {
			throw new LocationServiceException("Invalid Location");
		}
	}

}
