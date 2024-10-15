package com.Member.aiml_server_2024.distance;

import com.Member.aiml_server_2024.distance.Location;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LocationService {

    List<Location> getLocations() throws ExecutionException, InterruptedException;

}
