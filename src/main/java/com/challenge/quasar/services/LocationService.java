package com.challenge.quasar.services;

import com.challenge.quasar.entities.Location;
import com.challenge.quasar.entities.Satellite;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private Environment environment;

    public Location getLocation(List<Satellite> satellites) {
        int satellitesQuantity = satellites.size();
        double[][] positions = new double[satellitesQuantity][];
        double[] distances = new double[satellitesQuantity];
        this._fillSatellitesPositionsAndDistances(satellites, positions, distances);
        double[] result = this._prepareAndSolveEquationSystem(positions, distances);
        return new Location(result);
    }

    private void _fillSatellitesPositionsAndDistances(List<Satellite> satellites, double[][] positions, double[] distances) {
        int i = 0;
        for (Satellite s: satellites) {
            String[] locationProperty = environment.getProperty("satellites."+s.getName()+".location").split(",");
            Location location = null;
            if (locationProperty != null) {
                location = new Location(locationProperty);
            }
            double[] satellitePosition = location.asArray();
            positions[i] = satellitePosition;
            distances[i] = s.getDistance();
            i++;
        }
    }

    /**
     * Prepare an equation system who is satisfied by a pair of (x,y) at the intersection of the 3 circles described
     * with a center in each position and a radius equals to the distance.
     * @param positions pair of each circle center
     * @param distances radius of each circle
     * @return Triangulation intersection point.
     */
    private double[] _prepareAndSolveEquationSystem(double[][] positions, double[] distances) {
        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(positions, distances),
                new LevenbergMarquardtOptimizer()
        );
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        return optimum.getPoint().toArray();
    }
}
