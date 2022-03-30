package edu.escuelaing.arep.services.impl;

import edu.escuelaing.arep.model.IpPool;
import edu.escuelaing.arep.services.ILoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Iván Camilo Rincón Saavedra
 * @version 1.0 3/30/2022
 * @project proxy
 */
public class RoundRobin implements ILoadBalancer {
    private static Integer position = 0;

    @Override
    public String getServer() {
        //Getting All the servers
        Set<String> servers = IpPool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();

        serverList.addAll(servers);
        String target = null;

        synchronized (position) {
            if (position >= serverList.size()) position = 0;

            target = serverList.get(position);
            System.out.println(position);
            position++;
        }
        return target;
    }
}
