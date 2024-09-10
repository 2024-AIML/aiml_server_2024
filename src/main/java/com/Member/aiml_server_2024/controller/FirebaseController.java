package com.Member.aiml_server_2024.controller;

import com.Member.aiml_server_2024.distance.DistanceService;
import com.Member.aiml_server_2024.distance.Location;
import com.Member.aiml_server_2024.distance.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class FirebaseController {

    //    private final FirebaseService testservice;
    private final LocationService locationService;
    private final DistanceService distanceService;

//    @GetMapping("/get")
//    public ResponseEntity<List<FirebaseTest>> getUser() throws Exception {
//        List<FirebaseTest> list = testservice.getUsers();
//        return ResponseEntity.ok(list);
//    }

    @GetMapping("/get")
    public ResponseEntity<List<Location>> getLocations() throws ExecutionException, InterruptedException {
        List<Location> list = locationService.getLocations();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/distance")
    public double getDistance(@RequestParam String startLoc, @RequestParam String distLoc) throws ExecutionException, InterruptedException {
        double distace = distanceService.getDistance(startLoc, distLoc);
        return distace;
//        http://localhost:8080/test/distance?startLoc=Test1&distLoc=test 이렇게 Api를 호출하면 됨
    }
}
