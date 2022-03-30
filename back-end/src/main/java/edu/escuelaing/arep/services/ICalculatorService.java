package edu.escuelaing.arep.services;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project back-end
 */
public interface ICalculatorService {

    /**
     * Method that return a aSin of an specific angle
     *
     * @param angle, the angle
     * @return double, that represent the value of aSin(Angle)
     */
    public double aSin(double angle);

    /**
     * Method that return a aTan of an specific angle
     *
     * @param angle, the angle
     * @return double, that represent the value of aTan(Angle)
     */
    public double aTan(double angle);
}
