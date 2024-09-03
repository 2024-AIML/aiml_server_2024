package com.Member.aiml_server_2024.distance;

import java.util.concurrent.ExecutionException;

public interface DistanceService {

//    String saveLocation(Location location) throws ExecutionException, InterruptedException;
    Location getLocation(String location) throws ExecutionException, InterruptedException;

    double getDistance(String startLoc, String distLoc) throws ExecutionException, InterruptedException;
}
