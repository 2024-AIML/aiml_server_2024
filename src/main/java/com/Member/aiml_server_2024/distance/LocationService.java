package com.Member.aiml_server_2024.distance;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LocationService {

    List<Location> getLocations() throws ExecutionException, InterruptedException;
}
