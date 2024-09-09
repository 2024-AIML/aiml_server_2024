package com.Member.aiml_server_2024.distance;

import com.google.cloud.firestore.GeoPoint;

import java.util.concurrent.ExecutionException;

public interface DistanceService {

//    String saveLocation(Location location) throws ExecutionException, InterruptedException;
    Location getLocation(String location) throws ExecutionException, InterruptedException;

    double getDistance(String startLoc, String distLoc) throws ExecutionException, InterruptedException;

    double getDistanceByGeoPoint(GeoPoint userLoc, GeoPoint shelterLoc) throws ExecutionException, InterruptedException;

    void updateShelterCount(String userLoc, String shelterLoc) throws ExecutionException, InterruptedException;
}
