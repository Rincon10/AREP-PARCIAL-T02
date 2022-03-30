package edu.escuelaing.arep;

import edu.escuelaing.arep.services.IHttpConnectionService;
import edu.escuelaing.arep.services.ILoadBalancer;
import edu.escuelaing.arep.services.impl.HttpConnectionService;
import edu.escuelaing.arep.services.impl.RoundRobin;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    private static String path = "/proxy";
    private static String helloPath = "/hello";
    private static String aSinpath = "/asin";
    private static String aTanpath = "/atan";
    private static final ILoadBalancer roundRobin = new RoundRobin();


    /**
     * Method that set the instances of the controllers of our API
     */
    protected static void setControllers() {
        get(aSinpath, (req, res) -> {
            res.type("application/json");
            res.status(HttpStatus.OK_200);

            String path = roundRobin.getServer() + aSinpath + "?value=" + req.queryParams("value");
            System.out.println("Petition sinA to " + path);
            System.out.println("--------------------------------------------------------------");
            IHttpConnectionService connectionService = new HttpConnectionService(path);
            return connectionService.startConnection();

        });
        get(aTanpath, (req, res) -> {
            res.type("application/json");
            res.status(HttpStatus.OK_200);

            String path = roundRobin.getServer() + aTanpath + "?value=" + req.queryParams("value");
            System.out.println("Petition tanA to " + path);
            System.out.println("--------------------------------------------------------------");
            IHttpConnectionService connectionService = new HttpConnectionService(path);
            return connectionService.startConnection();
        });
    }

    /**
     * Main method, that start our Spark application
     *
     * @param args
     */
    public static void main(String[] args) {
        //Setting the portNumber
        port(getPort());
        //staticFileLocation("/public");

        //After-filters are evaluated after each request, and can read the request and read/modify the response:
        // Allow CORS
        options("/*",
                (request, response) -> {
                    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        // This only if we wanna have the front in resources
//        get(defaultPath, (req, res) -> {
//            res.redirect("/index.html");
//            return "";
//        });

        get(helloPath, (req, res) -> "Hello, world from spark.");


        //Our API is gonna be on the base path, /api/v1
        path(path, () -> {
            //Setting the  Controllers of our API
            setControllers();

            //Using Exceptions
            exception(Exception.class, (exception, request, response) -> {
                response.status(HttpStatus.BAD_REQUEST_400);
            });
        });
    }

    /***
     * Method that returns the port number to use in our App
     * @return int, port number
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4566; //returns default port if heroku-port isn't se  (i.e. on localhost)
    }
}
