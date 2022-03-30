package edu.escuelaing.arep.services;

import java.io.IOException;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project proxy
 */
public interface IHttpConnectionService {
    /**
     * Method that return the JsonObject of our petition
     *
     * @return String, response of the petition
     * @throws IOException
     */
    public String getResponse() throws IOException;

    public String startConnection(String jsonInputString) throws IOException;
}
