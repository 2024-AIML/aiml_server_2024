package com.Member.aiml_server_2024.navigation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface GeocodingService {
    String getGeocode(double latitude, double longitude);

    String getAddressFromGeocode(double latitude, double longitude);

    void savaLocation(UserLocation userLocation) throws ExecutionException, InterruptedException;

    String getLocations(String userId, String shelterName) throws ExecutionException, InterruptedException;
}
