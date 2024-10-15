package com.Member.aiml_server_2024.navigation;

public interface GeocodingService {
    String getGeocode(double latitude, double longitude);

    String getAddressFromGeocode(double latitude, double longitude);
}
