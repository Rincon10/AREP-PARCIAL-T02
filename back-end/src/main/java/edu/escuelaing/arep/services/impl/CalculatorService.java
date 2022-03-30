package edu.escuelaing.arep.services.impl;

import edu.escuelaing.arep.services.ICalculatorService;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project back-end
 */
public class CalculatorService implements ICalculatorService {
    @Override
    public double aSin(double angle) {
        return Math.asin(angle);
    }

    @Override
    public double aTan(double angle) {
        return Math.atan(angle);
    }
}
