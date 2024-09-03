package com.Member.aiml_server_2024.distance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class LocationDaoTest {

    @Autowired
    private LocationServiceImpl locationService;

    @Test
    public void getDistance() throws ExecutionException, InterruptedException {
        List<Location> loc = locationService.getLocations();
        ResponseEntity<List<Location>> tmp = ResponseEntity.ok(loc);

        System.out.println("1212");
        System.out.println(tmp);
    }
}