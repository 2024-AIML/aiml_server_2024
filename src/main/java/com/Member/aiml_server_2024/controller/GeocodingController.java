package com.Member.aiml_server_2024.controller;


import com.Member.aiml_server_2024.navigation.GeocodingService;
import com.Member.aiml_server_2024.navigation.UserLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/geocoding")
public class GeocodingController {

    private final GeocodingService geocodingService;

    @PostMapping("/userlocation")
    public ResponseEntity<String> saveUserLocation(@RequestBody UserLocation userLocation) throws ExecutionException, InterruptedException {
//        geocodingService.savaLocation(userLocation);
//        return ResponseEntity.ok("Location saved successfully");
        // 위도와 경도를 기반으로 위치 이름 가져오기
        String locationName = geocodingService.getAddressFromGeocode(Double.parseDouble(userLocation.getLatitude()), Double.parseDouble(userLocation.getLongitude()));

        // 위치 이름을 UserLocation 객체에 설정
        userLocation.setLocationName(locationName);

        // 위치 저장
        geocodingService.savaLocation(userLocation);
        return ResponseEntity.ok("Location saved successfully");
    }

    @GetMapping("/{userId}/{shelterName}")
    public String getLocation(@PathVariable String userId, @PathVariable String shelterName) throws Exception {
        return geocodingService.getLocations(userId, shelterName);
    }
}
