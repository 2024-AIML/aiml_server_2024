package com.Member.aiml_server_2024.distance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DistanceServiceImplTest {

    @Autowired
    private DistanceServiceImpl distanceService;

//    @Test
//    public void savelocation() throws ExecutionException, InterruptedException {
//        Location location = new Location("111.11", "111.11", "test");
//        distanceService.saveLocation(location);
//    }

    @Test
    public void getLocation() throws ExecutionException, InterruptedException {
        Location result = distanceService.getLocation("test");
        System.out.println(result.getLatitude());
        System.out.println(result.getLongitude());
        System.out.println(result.getLocation());
    }

    @Test
    public void getDis() throws ExecutionException, InterruptedException {
        double re = distanceService.getDistance("Test1", "test");
        System.out.println(re);
    }



}