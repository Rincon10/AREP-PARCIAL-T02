package edu.escuelaing.arep.services.impl;

import com.google.gson.JsonObject;
import edu.escuelaing.arep.services.IConvertorService;
import org.eclipse.jetty.http.HttpStatus;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project back-end
 */
public class Convertorservice implements IConvertorService {
    @Override
    public JsonObject getError(String error) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", "-1");
        jsonObject.addProperty("status_code", HttpStatus.BAD_REQUEST_400);
        jsonObject.addProperty("description", error);
        return jsonObject;
    }

    @Override
    public JsonObject doubleToJSON(String operation, double[] values) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("operation", operation);
        jsonObject.addProperty("input", values[0]);
        jsonObject.addProperty("output", values[1]);
        return jsonObject;
    }
}
