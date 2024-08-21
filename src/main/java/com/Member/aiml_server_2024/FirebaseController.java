package com.Member.aiml_server_2024;

import com.Member.aiml_server_2024.distance.Location;
import com.Member.aiml_server_2024.distance.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class FirebaseController {

    //    private final FirebaseService testservice;
    private final LocationService locationService;

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

}
