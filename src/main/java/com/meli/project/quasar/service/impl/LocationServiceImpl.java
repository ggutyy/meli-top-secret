package com.meli.project.quasar.service.impl;

import java.util.List;

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
	public LocationDTO getLocation(List<LocationDTO> actualsPositions, List<Double> emiterDistances) {
		
		try {
			double[][] positions = build2DArrayPosition(actualsPositions);
			double[] distances = emiterDistances.stream().mapToDouble(Double::doubleValue).toArray();
			TrilaterationFunction trilaterationFunction = new TrilaterationFunction(positions,distances);
			NonLinearLeastSquaresSolver nonLinearSqSolver = new NonLinearLeastSquaresSolver(trilaterationFunction,
					new LevenbergMarquardtOptimizer());
			double[] position = nonLinearSqSolver.solve().getPoint().toArray();
			return new LocationDTO(position);
		} catch (Exception excp) {
			String msg = "Failed to calculate location.";
			throw new LocationServiceException(msg,excp);
		}
	}
	
	public double[][] build2DArrayPosition(List<LocationDTO> positions){
		double[][] positionsArray = new double[positions.size()][1];
		for(int i=0; i<positions.size(); i++) {
			LocationDTO locationDTO = positions.get(i);
			double[] value = {locationDTO.getxPosition().doubleValue(), locationDTO.getyPosition().doubleValue()};
			positionsArray[i] = value;
		}
		return positionsArray;
	}

}
