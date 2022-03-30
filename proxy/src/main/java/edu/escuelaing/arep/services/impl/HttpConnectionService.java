package edu.escuelaing.arep.services.impl;

import edu.escuelaing.arep.services.IHttpConnectionService;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project proxy
 */
public class HttpConnectionService implements IHttpConnectionService {
    private HttpURLConnection con;
    private URL url;
    private static final String USER_AGENT = "Mozilla/5.0";

    public HttpConnectionService() {
    }

    public HttpConnectionService(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String startConnection(String jsonInputString) throws IOException {
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        return getResponse();
    }


    @Override
    public String getResponse() throws IOException {
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("*****************************************************************************");
        System.out.println("GET Response Code :: " + responseCode + "on petition" + url.getPath());

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            System.out.println("starting GET petition on " + url.getPath());
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE from url" + url.getPath() + "\n");
        System.out.println("*****************************************************************************");
        return null;
    }
}
