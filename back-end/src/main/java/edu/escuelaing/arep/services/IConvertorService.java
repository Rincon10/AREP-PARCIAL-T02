package edu.escuelaing.arep.services;

import com.google.gson.JsonObject;
/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project back-end
 */
public interface IConvertorService {
    /**
     * Method that return a default error in JsonFormat
     * @param error, String error to set to the Json
     * @return JsonObject, the JsonObject that represents the error
     */
    public JsonObject getError(String error);

    public JsonObject doubleToJSON( String operation ,double[] values );
}
