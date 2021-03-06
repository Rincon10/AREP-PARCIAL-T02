package edu.escuelaing.arep;

import edu.escuelaing.arep.services.ICalculatorService;
import edu.escuelaing.arep.services.IConvertorService;
import edu.escuelaing.arep.services.impl.CalculatorService;
import edu.escuelaing.arep.services.impl.Convertorservice;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {
    private static String aSinpath = "/asin";
    private static String aTanpath = "/atan";
    private static String helloPath = "/hello";
    private static IConvertorService convertorService = new Convertorservice();
    private static ICalculatorService calculatorService = new CalculatorService();


    /**
     * Method that set the instances of the controllers of our API
     */
    protected static void setControllers() {
        get(aSinpath, (req, res) -> {
            res.type("application/json");
            double input = new Double(req.queryParams("value"));

            double output = calculatorService.aSin(input);
            return convertorService.doubleToJSON(aSinpath, new double[]{input, output});

        });
        get(aTanpath, (req, res) -> {
            res.type("application/json");
            double input = new Double(req.queryParams("value"));

            double output = calculatorService.aTan(input);
            return convertorService.doubleToJSON(aTanpath, new double[]{input, output});

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
        path("/api/v1", () -> {
            //Setting the  Controllers of our API
            setControllers();

            //Using Exceptions
            exception(Exception.class, (exception, request, response) -> {
                response.status(HttpStatus.BAD_REQUEST_400);
                response.body(convertorService.getError(exception.getMessage()).toString());
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
        return 4567; //returns default port if heroku-port isn't se  (i.e. on localhost)
    }
}
