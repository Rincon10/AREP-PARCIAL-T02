package edu.escuelaing.arep.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project proxy
 */
public class IpPool {
    public static Map<String, Integer> ipMap = new ConcurrentHashMap<>();
    private static String aSinpath = "/asin";
    private static String aTanpath = "/atan";
    static {
        int servers = 2;
        for (int i = 1; i <= servers; i++) {
            ipMap.put("http://ec2-54-159-196-129.compute-1.amazonaws.com:3500"+i+"/api/v1",10);
        }
    }
}